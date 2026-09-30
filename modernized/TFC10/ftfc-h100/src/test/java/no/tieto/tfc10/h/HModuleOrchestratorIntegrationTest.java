package no.tieto.tfc10.h;

import no.tieto.tfc10.k.KModuleValidator;
import no.tieto.tfc10.l.LModuleLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** RULE-001: H invokes K then real L loader on success. */
class HModuleOrchestratorIntegrationTest {

    @Test
    void rule001_endToEndHappyPathWithLModuleLoader() {
        var k = new KModuleValidator(req ->
                no.tieto.tfc10.l050.InitialCheckService.F115L050BackendPort.BackendResult.ok(""));
        var l = new LModuleLoader(
                req -> LModuleLoader.SysCodeRelResult.ok(1),
                () -> LModuleLoader.TimestampResult.ok());
        var h = new HModuleOrchestrator(k, new LModuleAdapter(l));
        var result = h.execute(new HModuleOrchestrator.HRequest("TFC10"));
        assertTrue(result.success());
    }

    @Test
    void lPreparationFailureSurfacesAtH() {
        var k = new KModuleValidator(req ->
                no.tieto.tfc10.l050.InitialCheckService.F115L050BackendPort.BackendResult.ok(""));
        var l = new LModuleLoader(
                req -> LModuleLoader.SysCodeRelResult.fail("ISR0"),
                () -> LModuleLoader.TimestampResult.ok());
        var h = new HModuleOrchestrator(k, new LModuleAdapter(l));
        var result = h.execute(new HModuleOrchestrator.HRequest("TFC10"));
        assertFalse(result.success());
    }
}
