package no.tieto.tfc10.f7919;

/**
 * Port of F7919270 timestamp format conversion (Phase 4 skeleton).
 */
public final class TimestampFormatConverter {

    /** RULE-011: compose DB2-style timestamp from date and time fields (F7919270). */
    public ConvertResult convert(ConvertRequest request) {
        if (request == null || request.inputDateYyyyMmDd() <= 0) {
            return ConvertResult.error("AE", "Missing input date");
        }
        int date = request.inputDateYyyyMmDd();
        int time = request.inputTimeHhmmss();
        int hundredths = request.inputHundredths();
        String yyyy = String.format("%04d", date / 10000);
        String mm = String.format("%02d", (date / 100) % 100);
        String dd = String.format("%02d", date % 100);
        int hh = time / 10000;
        int mi = (time / 100) % 100;
        int ss = time % 100;
        String db2 = String.format(
                "%s-%s-%s-%02d.%02d.%02d.%06d",
                yyyy, mm, dd, hh, mi, ss, hundredths * 10000);
        long compact = Long.parseLong(String.format("%08d%06d%02d", date, time, hundredths));
        return ConvertResult.ok(db2, compact);
    }

    public record ConvertRequest(int inputDateYyyyMmDd, int inputTimeHhmmss, int inputHundredths) {}

    public record ConvertResult(boolean success, String statusCode, String message, String db2Timestamp, long compactTimestamp) {
        public static ConvertResult ok(String db2Timestamp, long compactTimestamp) {
            return new ConvertResult(true, "OK", "", db2Timestamp, compactTimestamp);
        }

        public static ConvertResult error(String code, String message) {
            return new ConvertResult(false, code, message, "", 0L);
        }
    }
}
