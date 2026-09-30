package no.tieto.tfc10.l020;

/**
 * Port of F115L020 contract-number generation (Phase 3). F115ISC0 access is behind {@link SysCodePort}.
 */
public final class ContractNumberGenerator {

    private final SysCodePort sysCodePort;

    public ContractNumberGenerator(SysCodePort sysCodePort) {
        this.sysCodePort = sysCodePort;
    }

    /** RULE-006: contract number from range after sys-code reads (F115L020 / R115L020). */
    public GenerateResult generate(GenerateRequest request) {
        if (request == null
                || request.financialInstitutionNo() == null
                || request.financialInstitutionNo().isBlank()
                || request.contractType() == null
                || request.contractType().isBlank()) {
            return GenerateResult.error("AE", "Missing contract key");
        }
        var typeRead = sysCodePort.readContractTypeMapping(request);
        if (typeRead.error()) {
            return GenerateResult.error("AE", typeRead.message());
        }
        var rangeRead = sysCodePort.readContractRange(request, typeRead.rangeType());
        if (rangeRead.error()) {
            return GenerateResult.error("AE", rangeRead.message());
        }
        ContractRangeState state = rangeRead.state();
        ContractRangeRules.incrementCounter(request.regNo(), state);
        var validation = ContractRangeRules.validateRange(request.regNo(), request.contractType(), state);
        if (validation.error()) {
            return GenerateResult.error(validation.paragraph(), validation.message());
        }
        var update = sysCodePort.updateContractRange(request, state);
        if (update.error()) {
            return GenerateResult.error("E301", update.message());
        }
        int contractNo = ContractRangeRules.contractNumberFor(request.regNo(), state);
        return GenerateResult.ok(contractNo);
    }

    public record GenerateRequest(String financialInstitutionNo, int regNo, String contractType) {}

    public record GenerateResult(boolean success, String statusCode, String message, int contractNo) {
        public static GenerateResult ok(int contractNo) {
            return new GenerateResult(true, "OK", "", contractNo);
        }

        public static GenerateResult error(String code, String message) {
            return new GenerateResult(false, code, message, 0);
        }
    }

    public interface SysCodePort {
        TypeMappingResult readContractTypeMapping(GenerateRequest request);

        RangeReadResult readContractRange(GenerateRequest request, String rangeType);

        OperationResult updateContractRange(GenerateRequest request, ContractRangeState state);
    }

    public record TypeMappingResult(boolean error, String rangeType, String message) {
        public static TypeMappingResult ok(String rangeType) {
            return new TypeMappingResult(false, rangeType, "");
        }

        public static TypeMappingResult fail(String message) {
            return new TypeMappingResult(true, "", message);
        }
    }

    public record RangeReadResult(boolean error, ContractRangeState state, String message) {
        public static RangeReadResult ok(ContractRangeState state) {
            return new RangeReadResult(false, state, "");
        }

        public static RangeReadResult fail(String message) {
            return new RangeReadResult(true, null, message);
        }
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
