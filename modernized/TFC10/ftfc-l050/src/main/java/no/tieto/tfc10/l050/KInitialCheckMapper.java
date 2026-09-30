package no.tieto.tfc10.l050;

/** Maps FTFCK100 K request fields into R115L050 layout for E100-Check-Status. */
public final class KInitialCheckMapper {

    private KInitialCheckMapper() {}

    public static InitialCheckCommand fromKFields(
            String function,
            String medium,
            String financialInstitutionNo,
            String contractType,
            int contractNo,
            String operationType,
            String lastUpdateTimestamp,
            boolean checkForUpdate) {
        return new InitialCheckCommand(
                function,
                medium,
                financialInstitutionNo,
                contractType,
                contractNo,
                operationType,
                lastUpdateTimestamp,
                checkForUpdate);
    }
}
