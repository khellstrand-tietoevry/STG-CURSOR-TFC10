package no.tieto.tfc10.l020;

/**
 * Pure logic from F115L020 E200/E400 (contract range increment, validation, assignment).
 */
final class ContractRangeRules {

    private ContractRangeRules() {}

    static void incrementCounter(int regNo, ContractRangeState state) {
        if (usesSecondarySeriesForIncrement(regNo)) {
            state.setRange2Current(state.getRange2Current() + 1);
        } else {
            state.setRangeCurrent(state.getRangeCurrent() + 1);
        }
    }

    static RangeValidation validateRange(int regNo, String contractType, ContractRangeState state) {
        if (state.getRangeCurrent() >= state.getRangeEnd()) {
            return RangeValidation.error(
                    "E401",
                    rangeMessage(contractType, state.getRangeCurrent()));
        }
        if ((regNo == 8900 || regNo == 8902) && state.getRange2Current() >= state.getRange2End()) {
            return RangeValidation.error(
                    "E402",
                    rangeMessage(contractType, state.getRange2Current()));
        }
        if (isMidtNorgeRegNo(regNo) && state.getRange2Current() >= state.getRange2End()) {
            return RangeValidation.error(
                    "E404",
                    rangeMessage(contractType, state.getRange2Current()));
        }
        return RangeValidation.ok();
    }

    static int contractNumberFor(int regNo, ContractRangeState state) {
        if (regNo == 8900 || regNo == 8902 || isMidtNorgeRegNo(regNo)) {
            return state.getRange2Current();
        }
        return state.getRangeCurrent();
    }

    private static boolean usesSecondarySeriesForIncrement(int regNo) {
        return isMidtNorgeRegNo(regNo) || regNo == 8900 || regNo == 8902;
    }

    private static boolean isMidtNorgeRegNo(int regNo) {
        return regNo == 4202 || regNo == 4200 || regNo == 4210 || regNo == 4400;
    }

    private static String rangeMessage(String contractType, int current) {
        return "Contract gen. out of range -" + contractType + "- current value : " + current;
    }

    record RangeValidation(boolean error, String paragraph, String message) {
        static RangeValidation ok() {
            return new RangeValidation(false, "", "");
        }

        static RangeValidation error(String paragraph, String message) {
            return new RangeValidation(true, paragraph, message);
        }
    }
}
