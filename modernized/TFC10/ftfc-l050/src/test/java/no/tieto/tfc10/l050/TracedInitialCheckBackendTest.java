package no.tieto.tfc10.l050;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TracedInitialCheckBackendTest {

    private static InitialCheckCommand baseCommand() {
        return new InitialCheckCommand(
                "TFC10", "01", "123456789012345678", "01", 1000101, "CREATE", "2024-06-15-12.00.00.000000", true, 0);
    }

    @Test
    void rule006_rejectsInvalidStatusOperationRelation() {
        var deps = stubDeps(true, false, "OPEN", "OPEN-PROP", true);
        var backend = new TracedInitialCheckBackend(deps);
        var result = backend.execute(baseCommand());
        assertTrue(result.error());
        assertEquals("F102", result.statusCode());
    }

    @Test
    void rule007_blocksPermanentStatusWhenMainContractInProgress() {
        var deps = stubDeps(false, true, "OPEN", "PERMANENT-PERM-LOCK", true);
        var backend = new TracedInitialCheckBackend(deps);
        var result = backend.execute(baseCommand());
        assertTrue(result.error());
        assertEquals("G001", result.statusCode());
    }

    @Test
    void rule007_allowsTfd05WhenPermanentAndInProgress() {
        var cmd = new InitialCheckCommand(
                "TFD05", "01", "123456789012345678", "01", 1, "CREATE", "ts", true, 0);
        var deps = stubDeps(false, true, "OPEN", "PERM", true);
        var result = new TracedInitialCheckBackend(deps).execute(cmd);
        assertFalse(result.error());
    }

    @Test
    void rule010_paSeqNoUsesPartAmountStatus() {
        var cmd = new InitialCheckCommand(
                "TFC10", "01", "123456789012345678", "01", 1, "CREATE", "ts", true, 5);
        var deps = new InitialCheckDependencies() {
            @Override
            public MainContractSnapshot readMainContract(InitialCheckCommand command, String tableVersion) {
                return new MainContractSnapshot("IGNORED", false);
            }

            @Override
            public String readPartAmountStatus(InitialCheckCommand command, String tableVersion) {
                return "PART-STATUS";
            }

            @Override
            public boolean validateStatusOperationRelation(InitialCheckCommand command, String status) {
                return "PART-STATUS".equals(status);
            }

            @Override
            public String readStatusProperty(String status) {
                return "PROP-" + status;
            }
        };
        var result = new TracedInitialCheckBackend(deps).execute(cmd);
        assertFalse(result.error());
        assertEquals("PROP-PART-STATUS", result.currentStatusProperty());
    }

    @Test
    void rule017_skipsPropertyChecksForReadShow() {
        var cmd = new InitialCheckCommand(
                "TFR01", "01", "123456789012345678", "01", 1, "SHOW", "ts", true, 0);
        var deps = stubDeps(false, true, "OPEN", "PERM", false);
        var result = new TracedInitialCheckBackend(deps).execute(cmd);
        assertFalse(result.error());
    }

    private static InitialCheckDependencies stubDeps(
            boolean invalidRelation,
            boolean inProgress,
            String status,
            String property,
            boolean validateCalled) {
        return new InitialCheckDependencies() {
            @Override
            public MainContractSnapshot readMainContract(InitialCheckCommand command, String tableVersion) {
                if ("WORK".equals(tableVersion)) {
                    return new MainContractSnapshot(status, inProgress);
                }
                return new MainContractSnapshot(status, inProgress);
            }

            @Override
            public String readPartAmountStatus(InitialCheckCommand command, String tableVersion) {
                return status;
            }

            @Override
            public boolean validateStatusOperationRelation(InitialCheckCommand command, String status) {
                if (!validateCalled) {
                    fail("validateStatusOperationRelation should not run for READ/SHOW");
                }
                return !invalidRelation;
            }

            @Override
            public String readStatusProperty(String status) {
                return property;
            }
        };
    }
}
