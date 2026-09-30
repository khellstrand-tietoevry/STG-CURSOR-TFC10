package no.tieto.tfc10.l050;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class InitialCheckServiceTest {

    private static InitialCheckCommand sampleCommand() {
        return new InitialCheckCommand(
                "TFC10", "01", "123456789012345678", "01", 1000101, "CREATE", "2024-06-15-12.00.00.000000", true);
    }

    @Test
    void rule008_invokesBackendWithMappedCommand() {
        AtomicReference<InitialCheckCommand> captured = new AtomicReference<>();
        var service = new InitialCheckService(cmd -> {
            captured.set(cmd);
            return InitialCheckService.F115L050BackendPort.BackendResult.ok("STATUS-OK");
        });
        var result = service.run(sampleCommand());
        assertFalse(result.error());
        assertEquals("TFC10", captured.get().function());
        assertEquals("123456789012345678", captured.get().financialInstitutionNo());
        assertTrue(captured.get().checkForUpdate());
    }

    @Test
    void rule008_propagatesBackendError() {
        var service = new InitialCheckService(
                cmd -> InitialCheckService.F115L050BackendPort.BackendResult.error("FM", "Status conflict"));
        var result = service.run(sampleCommand());
        assertFalse(result.success());
        assertEquals("FM", result.statusCode());
    }

    @Test
    void rule005_rejectsBlankFunction() {
        var service = InitialCheckService.withTracedBackend(stubHappyDeps());
        var bad = new InitialCheckCommand(" ", "01", "123456789012345678", "01", 1, "CREATE", "ts", true);
        var result = service.run(bad);
        assertTrue(result.error());
        assertEquals("C102", result.statusCode());
        assertEquals("TF-SY-FUNC-AND-MEDIUM", result.message());
    }

    @Test
    void rule005_rejectsBlankMedium() {
        var service = InitialCheckService.withTracedBackend(stubHappyDeps());
        var bad = new InitialCheckCommand("TFC10", " ", "123456789012345678", "01", 1, "CREATE", "ts", true);
        var result = service.run(bad);
        assertEquals("C102", result.statusCode());
    }

    @Test
    void rejectsMissingOperationType() {
        var service = new InitialCheckService(
                cmd -> InitialCheckService.F115L050BackendPort.BackendResult.ok(""));
        var bad = new InitialCheckCommand("TFC10", "01", "123456789012345678", "01", 1, " ", "ts", true);
        var result = service.run(bad);
        assertFalse(result.success());
        assertEquals("AE", result.statusCode());
    }

    @Test
    void tracedBackendHappyPath() {
        var service = InitialCheckService.withTracedBackend(stubHappyDeps());
        var result = service.run(sampleCommand());
        assertTrue(result.success());
        assertEquals("STATUS-PROP", result.currentStatusProperty());
    }

    private static InitialCheckDependencies stubHappyDeps() {
        return new InitialCheckDependencies() {
            @Override
            public MainContractSnapshot readMainContract(InitialCheckCommand command, String tableVersion) {
                return new MainContractSnapshot("OPEN", false);
            }

            @Override
            public String readPartAmountStatus(InitialCheckCommand command, String tableVersion) {
                return "OPEN";
            }

            @Override
            public boolean validateStatusOperationRelation(InitialCheckCommand command, String status) {
                return true;
            }

            @Override
            public String readStatusProperty(String status) {
                return "STATUS-PROP";
            }
        };
    }
}
