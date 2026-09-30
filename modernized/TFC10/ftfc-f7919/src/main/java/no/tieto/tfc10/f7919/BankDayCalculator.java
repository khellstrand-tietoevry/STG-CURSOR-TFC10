package no.tieto.tfc10.f7919;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Port of F7919090 bank-day / holiday logic (Phase 4 skeleton). Holiday tables behind {@link HolidayCalendarPort}.
 */
public final class BankDayCalculator {

    private final HolidayCalendarPort holidayCalendarPort;

    public BankDayCalculator(HolidayCalendarPort holidayCalendarPort) {
        this.holidayCalendarPort = holidayCalendarPort;
    }

    /** RULE-010: bank-day flag and days to next bank day (F7919090). */
    public BankDayResult evaluate(BankDayRequest request) {
        if (request == null) {
            return BankDayResult.error("AE", "Missing request");
        }
        try {
            LocalDate date = TradeFinanceDates.parseYyyyMmDd(request.inputDateYyyyMmDd());
            boolean bankDay = isBankDay(date);
            int weekday = date.getDayOfWeek().getValue();
            int daysToNext = 0;
            if (!bankDay) {
                LocalDate cursor = date;
                while (!isBankDay(cursor = cursor.plusDays(1))) {
                    daysToNext++;
                }
                daysToNext++;
            }
            return BankDayResult.ok(bankDay, weekday, daysToNext);
        } catch (IllegalArgumentException ex) {
            return BankDayResult.error("AE", ex.getMessage());
        }
    }

    private boolean isBankDay(LocalDate date) {
        DayOfWeek dow = date.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) {
            return false;
        }
        return !holidayCalendarPort.isPublicHoliday(date);
    }

    public record BankDayRequest(int inputDateYyyyMmDd) {}

    public record BankDayResult(
            boolean success,
            String statusCode,
            String message,
            boolean bankDay,
            int weekday,
            int daysToNextBankDay) {
        public static BankDayResult ok(boolean bankDay, int weekday, int daysToNextBankDay) {
            return new BankDayResult(true, "OK", "", bankDay, weekday, daysToNextBankDay);
        }

        public static BankDayResult error(String code, String message) {
            return new BankDayResult(false, code, message, false, 0, 0);
        }
    }

    public interface HolidayCalendarPort {
        boolean isPublicHoliday(LocalDate date);
    }
}
