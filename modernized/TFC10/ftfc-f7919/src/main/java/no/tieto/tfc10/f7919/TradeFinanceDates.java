package no.tieto.tfc10.f7919;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Shared yyyyMMdd parsing for F7919 helpers. */
final class TradeFinanceDates {

    private static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.BASIC_ISO_DATE;

    private TradeFinanceDates() {}

    static LocalDate parseYyyyMmDd(int yyyyMmDd) {
        try {
            return LocalDate.parse(String.format("%08d", yyyyMmDd), YYYYMMDD);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Invalid date: " + yyyyMmDd);
        }
    }

    static int toYyyyMmDd(LocalDate date) {
        return Integer.parseInt(date.format(YYYYMMDD));
    }
}
