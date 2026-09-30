package no.tieto.tfc10.f7919;

import java.time.LocalDate;

/**
 * Port of F7919010 date arithmetic (Phase 4 skeleton). F7919060 360-day calendar behind {@link FinancialCalendarPort}.
 */
public final class DatePlusDaysCalculator {

    private final FinancialCalendarPort financialCalendarPort;

    public DatePlusDaysCalculator(FinancialCalendarPort financialCalendarPort) {
        this.financialCalendarPort = financialCalendarPort;
    }

    /** RULE-008: gregorian date plus day offset (F7919010 / R7919010). */
    public DateResult addDays(DateRequest request) {
        if (request == null) {
            return DateResult.error("AE", "Missing request");
        }
        try {
            LocalDate base = TradeFinanceDates.parseYyyyMmDd(request.inputDateYyyyMmDd());
            LocalDate result;
            if (request.daysPerYear() == 360) {
                result = financialCalendarPort.addDays360(base, request.addDays());
            } else {
                result = base.plusDays(request.addDays())
                        .plusMonths(request.addMonths())
                        .plusYears(request.addYears());
            }
            return DateResult.ok(TradeFinanceDates.toYyyyMmDd(result));
        } catch (IllegalArgumentException ex) {
            return DateResult.error("AE", ex.getMessage());
        } catch (RuntimeException ex) {
            return DateResult.error("AE", ex.getMessage());
        }
    }

    public record DateRequest(
            int inputDateYyyyMmDd,
            int addDays,
            int addMonths,
            int addYears,
            int daysPerYear) {}

    public record DateResult(boolean success, String statusCode, String message, int outputDateYyyyMmDd) {
        public static DateResult ok(int outputDate) {
            return new DateResult(true, "OK", "", outputDate);
        }

        public static DateResult error(String code, String message) {
            return new DateResult(false, code, message, 0);
        }
    }

    public interface FinancialCalendarPort {
        /** F7919060-style 30/360 day addition when days-per-year is 360. */
        LocalDate addDays360(LocalDate base, int days);
    }
}
