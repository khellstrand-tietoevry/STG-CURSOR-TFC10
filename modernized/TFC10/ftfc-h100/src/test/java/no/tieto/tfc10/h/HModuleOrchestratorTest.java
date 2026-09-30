package no.tieto.tfc10.h;

import no.tieto.tfc10.k.KModuleValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HModuleOrchestratorTest {

    @Test
    void rule001_skipsLWhenKFails() {
        var k = new KModuleValidator(req ->
                no.tieto.tfc10.l050.InitialCheckService.F115L050BackendPort.BackendResult.error("AS", "fail"));
        var h = new HModuleOrchestrator(k, req -> HModuleOrchestrator.LResult.ok());
        var result = h.execute(new HModuleOrchestrator.HRequest("TFC10"));
        assertFalse(result.success());
        assertEquals("AS", result.statusCode());
    }

    @Test
    void rule001_callsLWhenKOk() {
        var k = new KModuleValidator(req ->
                no.tieto.tfc10.l050.InitialCheckService.F115L050BackendPort.BackendResult.ok(""));
        var h = new HModuleOrchestrator(k, req -> HModuleOrchestrator.LResult.ok());
        var result = h.execute(new HModuleOrchestrator.HRequest("TFC10"));
        assertTrue(result.success());
    }
}
