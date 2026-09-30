package no.tieto.tfc10.l090;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class ShadowChargeCleanupTest {

    private static ShadowChargeCleanup.PartChargePort partPort(
            Function<ShadowChargeCleanup.CleanupRequest, Iterator<String>> it) {
        return new ShadowChargeCleanup.PartChargePort() {
            @Override
            public Iterator<String> iterateWorkPartCharges(ShadowChargeCleanup.CleanupRequest request) {
                return it.apply(request);
            }

            @Override
            public ShadowChargeCleanup.OperationResult deletePartCharge(
                    ShadowChargeCleanup.CleanupRequest request, String shadowId) {
                return ShadowChargeCleanup.OperationResult.ok();
            }
        };
    }

    private static ShadowChargeCleanup.MainChargePort mainPort(
            Function<ShadowChargeCleanup.CleanupRequest, Iterator<String>> it) {
        return new ShadowChargeCleanup.MainChargePort() {
            @Override
            public Iterator<String> iterateWorkMainCharges(ShadowChargeCleanup.CleanupRequest request) {
                return it.apply(request);
            }

            @Override
            public ShadowChargeCleanup.OperationResult deleteMainCharge(
                    ShadowChargeCleanup.CleanupRequest request, String shadowId) {
                return ShadowChargeCleanup.OperationResult.ok();
            }
        };
    }

    @Test
    void rule007_deletesAllPartAndMainShadowsInWork() {
        var partIds = List.of("PC-1", "PC-2");
        var mainIds = List.of("CH-1");
        var cleanup = new ShadowChargeCleanup(
                partPort(req -> partIds.iterator()),
                mainPort(req -> mainIds.iterator()));
        var result = cleanup.deleteShadowCharges(
                new ShadowChargeCleanup.CleanupRequest("123456789012345678", "01", 1000101));
        assertTrue(result.success());
        assertEquals(2, result.partChargesDeleted());
        assertEquals(1, result.mainChargesDeleted());
    }

    @Test
    void rule090_stopsOnPartChargeDeleteError() {
        var cleanup = new ShadowChargeCleanup(
                new ShadowChargeCleanup.PartChargePort() {
                    @Override
                    public Iterator<String> iterateWorkPartCharges(ShadowChargeCleanup.CleanupRequest request) {
                        return List.of("PC-1").iterator();
                    }

                    @Override
                    public ShadowChargeCleanup.OperationResult deletePartCharge(
                            ShadowChargeCleanup.CleanupRequest request, String shadowId) {
                        return ShadowChargeCleanup.OperationResult.fail("F115IPC0 error");
                    }
                },
                mainPort(req -> List.<String>of().iterator()));
        var result = cleanup.deleteShadowCharges(
                new ShadowChargeCleanup.CleanupRequest("123456789012345678", "01", 1000101));
        assertFalse(result.success());
    }

    @Test
    void rejectsInvalidContractNo() {
        var cleanup = new ShadowChargeCleanup(
                partPort(req -> List.<String>of().iterator()),
                mainPort(req -> List.<String>of().iterator()));
        var result = cleanup.deleteShadowCharges(
                new ShadowChargeCleanup.CleanupRequest("123456789012345678", "01", 0));
        assertFalse(result.success());
        assertEquals("AE", result.statusCode());
    }

    @Test
    void handlesEmptyShadowLists() {
        AtomicInteger partCalls = new AtomicInteger();
        var cleanup = new ShadowChargeCleanup(
                partPort(req -> {
                    partCalls.incrementAndGet();
                    return List.<String>of().iterator();
                }),
                mainPort(req -> List.<String>of().iterator()));
        var result = cleanup.deleteShadowCharges(
                new ShadowChargeCleanup.CleanupRequest("123456789012345678", "01", 42));
        assertTrue(result.success());
        assertEquals(0, result.partChargesDeleted());
        assertEquals(1, partCalls.get());
    }
}
