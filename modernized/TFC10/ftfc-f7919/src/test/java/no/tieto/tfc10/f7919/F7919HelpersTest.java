package no.tieto.tfc10.f7919;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class F7919HelpersTest {

    @Test
    void rule008_addsDaysOnGregorianCalendar() {
        var calc = new DatePlusDaysCalculator((base, days) -> base.plusDays(days));
        var result = calc.addDays(new DatePlusDaysCalculator.DateRequest(20240115, 10, 0, 0, 365));
        assertTrue(result.success());
        assertEquals(20240125, result.outputDateYyyyMmDd());
    }

    @Test
    void rule008_uses360DayPortWhenRequested() {
        var calc = new DatePlusDaysCalculator((base, days) -> base.plusDays(days * 2));
        var result = calc.addDays(new DatePlusDaysCalculator.DateRequest(20240115, 5, 0, 0, 360));
        assertTrue(result.success());
        assertEquals(20240125, result.outputDateYyyyMmDd());
    }

    @Test
    void rule009_readsMachineTimestampFromClock() {
        var clock = Clock.fixed(Instant.parse("2024-06-15T14:30:45Z"), ZoneOffset.UTC);
        var reader = new MachineTimestampReader(clock);
        var result = reader.readTimestamp();
        assertTrue(result.success());
        assertEquals(20240615, result.machineDate());
        assertEquals(143045, result.machineTime());
    }

    @Test
    void rule010_treatsWeekendAsNonBankDay() {
        var calc = new BankDayCalculator(date -> false);
        var result = calc.evaluate(new BankDayCalculator.BankDayRequest(20240615));
        assertTrue(result.success());
        assertFalse(result.bankDay());
        assertTrue(result.daysToNextBankDay() >= 1);
    }

    @Test
    void rule010_respectsHolidayPort() {
        var calc = new BankDayCalculator(date -> date.getMonthValue() == 12 && date.getDayOfMonth() == 24);
        var result = calc.evaluate(new BankDayCalculator.BankDayRequest(20241224));
        assertTrue(result.success());
        assertFalse(result.bankDay());
    }

    @Test
    void rule011_buildsDb2TimestampFromParts() {
        var conv = new TimestampFormatConverter();
        var result = conv.convert(new TimestampFormatConverter.ConvertRequest(20240615, 143045, 12));
        assertTrue(result.success());
        assertTrue(result.db2Timestamp().startsWith("2024-06-15-14.30.45."));
        assertTrue(result.compactTimestamp() > 0);
    }
}
