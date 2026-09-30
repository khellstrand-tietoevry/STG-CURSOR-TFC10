package no.tieto.tfc10.l020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContractNumberGeneratorTest {

    private static ContractNumberGenerator generatorWithRange(int rangeCur, int rangeEnd, int r2Cur, int r2End) {
        return new ContractNumberGenerator(new ContractNumberGenerator.SysCodePort() {
            @Override
            public ContractNumberGenerator.TypeMappingResult readContractTypeMapping(
                    ContractNumberGenerator.GenerateRequest request) {
                return ContractNumberGenerator.TypeMappingResult.ok("RANGE-A");
            }

            @Override
            public ContractNumberGenerator.RangeReadResult readContractRange(
                    ContractNumberGenerator.GenerateRequest request, String rangeType) {
                return ContractNumberGenerator.RangeReadResult.ok(
                        new ContractRangeState(rangeCur, rangeEnd, r2Cur, r2End));
            }

            @Override
            public ContractNumberGenerator.OperationResult updateContractRange(
                    ContractNumberGenerator.GenerateRequest request, ContractRangeState state) {
                return ContractNumberGenerator.OperationResult.ok();
            }
        });
    }

    @Test
    void rule006_assignsPrimaryRangeForDefaultRegNo() {
        var gen = generatorWithRange(100, 999_999, 0, 0);
        var result = gen.generate(new ContractNumberGenerator.GenerateRequest("123456789012345678", 1234, "01"));
        assertTrue(result.success());
        assertEquals(101, result.contractNo());
    }

    @Test
    void rule006_usesSecondaryRangeForMidtNorgeRegNo() {
        var gen = generatorWithRange(100, 999_999, 200, 999_999);
        var result = gen.generate(new ContractNumberGenerator.GenerateRequest("123456789012345678", 4400, "01"));
        assertTrue(result.success());
        assertEquals(201, result.contractNo());
    }

    @Test
    void rejectsWhenPrimaryRangeExhausted() {
        var gen = generatorWithRange(500, 500, 0, 0);
        var result = gen.generate(new ContractNumberGenerator.GenerateRequest("123456789012345678", 1000, "01"));
        assertFalse(result.success());
        assertEquals("E401", result.statusCode());
        assertTrue(result.message().contains("01"));
    }

    @Test
    void rejectsMissingContractType() {
        var gen = generatorWithRange(1, 100, 1, 100);
        var result = gen.generate(new ContractNumberGenerator.GenerateRequest("123456789012345678", 1000, " "));
        assertFalse(result.success());
        assertEquals("AE", result.statusCode());
    }
}
