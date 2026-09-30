package no.tieto.tfc10.f7919;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Port of F7919070 machine timestamp (Phase 4). F7919071/F7919072 behind {@link Clock}.
 */
public final class MachineTimestampReader {

    private final Clock clock;

    public MachineTimestampReader(Clock clock) {
        this.clock = clock;
    }

    /** RULE-009: machine date/time for L-module preparations (F7919070). */
    public TimestampResult readTimestamp() {
        LocalDateTime now = LocalDateTime.now(clock);
        int machineDate = Integer.parseInt(now.format(DateTimeFormatter.BASIC_ISO_DATE));
        int machineTime = now.getHour() * 10000 + now.getMinute() * 100 + now.getSecond();
        long compact = Long.parseLong(now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        return TimestampResult.ok(machineDate, machineTime, compact);
    }

    public record TimestampResult(boolean success, String statusCode, String message, int machineDate, int machineTime, long compactTimestamp) {
        public static TimestampResult ok(int machineDate, int machineTime, long compactTimestamp) {
            return new TimestampResult(true, "OK", "", machineDate, machineTime, compactTimestamp);
        }

        public static TimestampResult error(String code, String message) {
            return new TimestampResult(false, code, message, 0, 0, 0L);
        }
    }
}
