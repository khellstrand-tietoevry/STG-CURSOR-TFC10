package no.tieto.tfc10.h;

import no.tieto.tfc10.l.LModuleLoader;

/** Bridges H orchestrator to FTFCL100 loader (Phase 2). */
public final class LModuleAdapter implements HModuleOrchestrator.LModulePort {

    private final LModuleLoader loader;

    public LModuleAdapter(LModuleLoader loader) {
        this.loader = loader;
    }

    @Override
    public HModuleOrchestrator.LResult load(HModuleOrchestrator.HRequest request) {
        var result = loader.load(new LModuleLoader.LRequest("0000000", request.functionCode()));
        if (result.success()) {
            return HModuleOrchestrator.LResult.ok();
        }
        return HModuleOrchestrator.LResult.error(result.statusCode(), result.message());
    }
}
