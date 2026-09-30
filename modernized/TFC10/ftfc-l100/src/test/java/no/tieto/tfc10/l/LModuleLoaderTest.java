package no.tieto.tfc10.l;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Characterization tests for FTFCL100 preparation path (D100/D200). */
class LModuleLoaderTest {

    private static LModuleLoader happyLoader() {
        return new LModuleLoader(
                req -> LModuleLoader.SysCodeRelResult.ok(1),
                () -> LModuleLoader.TimestampResult.ok());
    }

    @Test
    void rule001_loadSucceedsWhenPreparationsOk() { // RULE-001 L leg
        var result = happyLoader().load(new LModuleLoader.LRequest("1234567", "01"));
        assertTrue(result.success());
    }

    @Test
    void rejectsMissingOperationType() {
        var result = happyLoader().load(new LModuleLoader.LRequest("1234567", " "));
        assertFalse(result.success());
        assertEquals("AE", result.statusCode());
    }

    @Test
    void d102PathWhenSysCodeRelErrors() {
        var loader = new LModuleLoader(
                req -> LModuleLoader.SysCodeRelResult.fail("F115ISR0 error"),
                () -> LModuleLoader.TimestampResult.ok());
        var result = loader.load(new LModuleLoader.LRequest("1234567", "01"));
        assertFalse(result.success());
        assertEquals("AE", result.statusCode());
    }

    @Test
    void rejectsNonUniqueStatusMapping() {
        var loader = new LModuleLoader(
                req -> LModuleLoader.SysCodeRelResult.ok(2),
                () -> LModuleLoader.TimestampResult.ok());
        var result = loader.load(new LModuleLoader.LRequest("1234567", "01"));
        assertFalse(result.success());
        assertTrue(result.message().contains("NOT-UNIQUE"));
    }

    @Test
    void failsWhenTimestampReadFails() {
        var loader = new LModuleLoader(
                req -> LModuleLoader.SysCodeRelResult.ok(1),
                () -> LModuleLoader.TimestampResult.fail("F7919070 unavailable"));
        var result = loader.load(new LModuleLoader.LRequest("1234567", "01"));
        assertFalse(result.success());
    }
}
