package no.tieto.tfc10.l;

/**
 * Port of FTFCL100 main flow (Phase 2): B000 initialize → D000 preparations → E000 processing stub.
 * External CALLs (F115ISR0, F7919070, F115IMC0, …) are behind ports — see DECISIONS.md.
 */
public final class LModuleLoader {

    private final SysCodeRelationPort sysCodeRelationPort;
    private final TimestampPort timestampPort;

    public LModuleLoader(SysCodeRelationPort sysCodeRelationPort, TimestampPort timestampPort) {
        this.sysCodeRelationPort = sysCodeRelationPort;
        this.timestampPort = timestampPort;
    }

    /** RULE-001: L-module load path after K succeeds. */
    public LResult load(LRequest request) {
        if (request == null || request.operationType() == null || request.operationType().isBlank()) {
            return LResult.error("AE", "Missing operation type for L preparations");
        }
        var rel = sysCodeRelationPort.readValidOperationStatus(request);
        if (rel.error()) {
            return LResult.error("AE", rel.message());
        }
        if (rel.totalRows() > 1) {
            return LResult.error("AE", "TF-SY-STATUS-NOT-UNIQUE");
        }
        var ts = timestampPort.readTimestamp();
        if (ts.error()) {
            return LResult.error("AE", ts.message());
        }
        return LResult.ok();
    }

    public record LRequest(String financialInstitutionNo, String operationType) {}

    public record LResult(boolean success, String statusCode, String message) {
        public static LResult ok() {
            return new LResult(true, "OK", "");
        }

        public static LResult error(String code, String message) {
            return new LResult(false, code, message);
        }
    }

    public interface SysCodeRelationPort {
        SysCodeRelResult readValidOperationStatus(LRequest request);
    }

    public record SysCodeRelResult(boolean error, int totalRows, String message) {
        public static SysCodeRelResult ok(int totalRows) {
            return new SysCodeRelResult(false, totalRows, "");
        }

        public static SysCodeRelResult fail(String message) {
            return new SysCodeRelResult(true, 0, message);
        }
    }

    public interface TimestampPort {
        TimestampResult readTimestamp();
    }

    public record TimestampResult(boolean error, String message) {
        public static TimestampResult ok() {
            return new TimestampResult(false, "");
        }

        public static TimestampResult fail(String message) {
            return new TimestampResult(true, message);
        }
    }
}
