package no.tieto.tfc10.k;

import no.tieto.tfc10.l050.InitialCheckService;
import no.tieto.tfc10.l050.KInitialCheckMapper;

/**
 * Port of FTFCK100 validation orchestration. E100 initial-check via {@link InitialCheckService} (F115L050).
 */
public final class KModuleValidator {

    private final InitialCheckService initialCheckService;

    public KModuleValidator(InitialCheckService initialCheckService) {
        this.initialCheckService = initialCheckService;
    }

    public KModuleValidator(InitialCheckService.F115L050BackendPort backendPort) {
        this(new InitialCheckService(backendPort));
    }

    public KResult validate(KRequest request) {
        if (request == null || request.function() == null || request.function().isBlank()) {
            return KResult.error("AE", "Missing function code");
        }
        var command = KInitialCheckMapper.fromKFields(
                request.function(),
                request.medium(),
                request.financialInstitutionNo(),
                request.contractType(),
                request.contractNo(),
                request.operationType(),
                request.lastUpdateTimestamp(),
                request.checkForUpdate());
        InitialCheckService.InitialCheckResult check = initialCheckService.run(command);
        if (check.error()) {
            return KResult.error(check.statusCode(), check.message());
        }
        return KResult.ok();
    }

    public record KRequest(
            String function,
            String medium,
            String financialInstitutionNo,
            String contractType,
            int contractNo,
            String operationType,
            String lastUpdateTimestamp,
            boolean checkForUpdate) {

        /** Defaults aligned with FTFCK100 E100 happy-path characterization tests. */
        public static KRequest forFunction(String function) {
            return new KRequest(
                    function, "01", "123456789012345678", "01", 1000101, "CREATE", "2024-06-15-12.00.00.000000", true);
        }
    }

    public record KResult(boolean success, String statusCode, String message) {
        static KResult ok() {
            return new KResult(true, "OK", "");
        }

        static KResult error(String code, String message) {
            return new KResult(false, code, message);
        }
    }
}
