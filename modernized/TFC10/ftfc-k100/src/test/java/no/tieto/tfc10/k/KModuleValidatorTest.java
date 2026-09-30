package no.tieto.tfc10.k;

import no.tieto.tfc10.l050.InitialCheckCommand;
import no.tieto.tfc10.l050.InitialCheckService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class KModuleValidatorTest {

    @Test
    void rejectsBlankFunctionCode() {
        var validator = new KModuleValidator(
                cmd -> InitialCheckService.F115L050BackendPort.BackendResult.ok(""));
        var result = validator.validate(KModuleValidator.KRequest.forFunction("  "));
        assertFalse(result.success());
        assertEquals("AE", result.statusCode());
    }

    @Test
    void rule009_propagatesInitialCheckError() {
        var validator = new KModuleValidator(
                cmd -> InitialCheckService.F115L050BackendPort.BackendResult.error("AS", "Initial check failed"));
        var result = validator.validate(KModuleValidator.KRequest.forFunction("TFC10"));
        assertFalse(result.success());
        assertEquals("AS", result.statusCode());
    }

    @Test
    void rule008_mapsKRequestOntoInitialCheckCommand() {
        AtomicReference<InitialCheckCommand> captured = new AtomicReference<>();
        var validator = new KModuleValidator(cmd -> {
            captured.set(cmd);
            return InitialCheckService.F115L050BackendPort.BackendResult.ok("");
        });
        validator.validate(KModuleValidator.KRequest.forFunction("TFC10"));
        assertEquals("TFC10", captured.get().function());
        assertEquals("CREATE", captured.get().operationType());
        assertTrue(captured.get().checkForUpdate());
    }

    @Test
    void acceptsHappyPath() {
        var validator = new KModuleValidator(
                cmd -> InitialCheckService.F115L050BackendPort.BackendResult.ok("OK"));
        var result = validator.validate(KModuleValidator.KRequest.forFunction("TFC10"));
        assertTrue(result.success());
    }
}
