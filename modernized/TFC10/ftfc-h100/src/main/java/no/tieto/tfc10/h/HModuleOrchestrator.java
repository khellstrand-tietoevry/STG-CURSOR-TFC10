package no.tieto.tfc10.h;

import no.tieto.tfc10.k.KModuleValidator;

/**
 * Port of FTFCH100 control flow (Phase 1): K then L on success.
 */
public final class HModuleOrchestrator {

    private final KModuleValidator kModule;
    private final LModulePort lModule;

    public HModuleOrchestrator(KModuleValidator kModule, LModulePort lModule) {
        this.kModule = kModule;
        this.lModule = lModule;
    }

    public HResult execute(HRequest request) {
        var kResult = kModule.validate(KModuleValidator.KRequest.forFunction(request.functionCode()));
        if (!kResult.success()) {
            return HResult.error(kResult.statusCode(), kResult.message());
        }
        var lResult = lModule.load(request);
        if (!lResult.success()) {
            return HResult.error(lResult.statusCode(), lResult.message());
        }
        return HResult.ok();
    }

    public interface LModulePort {
        LResult load(HRequest request);
    }

    public record HRequest(String functionCode) {}

    public record LResult(boolean success, String statusCode, String message) {
        static LResult ok() {
            return new LResult(true, "OK", "");
        }

        static LResult error(String code, String message) {
            return new LResult(false, code, message);
        }
    }

    public record HResult(boolean success, String statusCode, String message) {
        static HResult ok() {
            return new HResult(true, "OK", "");
        }

        static HResult error(String code, String message) {
            return new HResult(false, code, message);
        }
    }
}
