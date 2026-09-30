package no.tieto.tfc10.l090;

import java.util.Iterator;

/**
 * Port of F115L090 shadow charge deletion (C000/E810–E950). F115IPC0 / F115ICH0 behind ports.
 * RULE-007: purge in-work part- then main-charge shadows for the contract key.
 */
public final class ShadowChargeCleanup {

    private final PartChargePort partChargePort;
    private final MainChargePort mainChargePort;

    public ShadowChargeCleanup(PartChargePort partChargePort, MainChargePort mainChargePort) {
        this.partChargePort = partChargePort;
        this.mainChargePort = mainChargePort;
    }

    /** Deletes all in-work part-charges then main-charges for the contract key. */
    public CleanupResult deleteShadowCharges(CleanupRequest request) {
        if (request == null
                || request.financialInstitutionNo() == null
                || request.financialInstitutionNo().isBlank()
                || request.contractType() == null
                || request.contractType().isBlank()
                || request.contractNo() <= 0) {
            return CleanupResult.error("AE", "Missing charge key");
        }
        int partDeleted = purgePartCharges(request);
        if (partDeleted < 0) {
            return CleanupResult.error("AE", "Part-charge delete failed");
        }
        int mainDeleted = purgeMainCharges(request);
        if (mainDeleted < 0) {
            return CleanupResult.error("AE", "Main-charge delete failed");
        }
        return CleanupResult.ok(partDeleted, mainDeleted);
    }

    private int purgePartCharges(CleanupRequest request) {
        int count = 0;
        Iterator<String> shadows = partChargePort.iterateWorkPartCharges(request);
        while (shadows.hasNext()) {
            var del = partChargePort.deletePartCharge(request, shadows.next());
            if (del.error()) {
                return -1;
            }
            count++;
        }
        return count;
    }

    private int purgeMainCharges(CleanupRequest request) {
        int count = 0;
        Iterator<String> shadows = mainChargePort.iterateWorkMainCharges(request);
        while (shadows.hasNext()) {
            var del = mainChargePort.deleteMainCharge(request, shadows.next());
            if (del.error()) {
                return -1;
            }
            count++;
        }
        return count;
    }

    public record CleanupRequest(String financialInstitutionNo, String contractType, int contractNo) {}

    public record CleanupResult(boolean success, String statusCode, String message, int partChargesDeleted, int mainChargesDeleted) {
        public static CleanupResult ok(int part, int main) {
            return new CleanupResult(true, "OK", "", part, main);
        }

        public static CleanupResult error(String code, String message) {
            return new CleanupResult(false, code, message, 0, 0);
        }
    }

    public interface PartChargePort {
        Iterator<String> iterateWorkPartCharges(CleanupRequest request);

        OperationResult deletePartCharge(CleanupRequest request, String shadowId);
    }

    public interface MainChargePort {
        Iterator<String> iterateWorkMainCharges(CleanupRequest request);

        OperationResult deleteMainCharge(CleanupRequest request, String shadowId);
    }

    public record OperationResult(boolean error, String message) {
        public static OperationResult ok() {
            return new OperationResult(false, "");
        }

        public static OperationResult fail(String message) {
            return new OperationResult(true, message);
        }
    }
}
