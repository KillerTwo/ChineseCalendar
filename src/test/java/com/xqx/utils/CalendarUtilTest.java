package com.xqx.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;

public class CalendarUtilTest {

    @Test
    public void testIsWorkday() {
        boolean workday = CalendarUtil.isWorkday(LocalDate.of(2023, 1, 16));
        Assertions.assertTrue(workday);
    }

    @Test
    public void testIsWorkdayDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(2023, 0, 16);
        boolean workday = CalendarUtil.isWorkday(instance.getTime());
        Assertions.assertTrue(workday);
    }

    @Test
    public void testIsHoliday() {
        boolean holiday = CalendarUtil.isHoliday(LocalDate.of(2023, 1, 21));
        Assertions.assertTrue(holiday);
    }

    @Test
    public void testIsHolidayDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(2023, 0, 21);
        boolean holiday = CalendarUtil.isHoliday(instance.getTime());
        Assertions.assertTrue(holiday);
    }

    @Test
    public void testIsWeekend() {
        boolean weekend = CalendarUtil.isWeekend(LocalDate.of(2023, 1, 15));
        Assertions.assertTrue(weekend);
    }

    @Test
    public void testIsWeekendDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(2023, 0, 15);
        boolean weekend = CalendarUtil.isWeekend(instance.getTime());
        Assertions.assertTrue(weekend);
    }

    @Test
    public void testIsInLieu() {
        boolean lieu = CalendarUtil.isInLieu(LocalDate.of(2023, 10, 6));
        Assertions.assertTrue(lieu);
    }

    @Test
    public void testIsInLieuDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(2023, 9, 6);
        boolean lieu = CalendarUtil.isInLieu(instance.getTime());
        Assertions.assertTrue(lieu);
    }

    @Test
    public void testNowBeforeWorkday() {
        LocalDate localDate = CalendarUtil.nowBeforeWorkday(1);
        Assertions.assertEquals(localDate, LocalDate.of(2023, 1, 13));
    }

    @Test
    public void testNowAfterWorkday() {
        LocalDate localDate = CalendarUtil.nowAfterWorkday(1);
        Assertions.assertEquals(localDate, LocalDate.of(2023, 1, 17));
    }

    @Test
    public void testDateBeforeWorkday() {
        LocalDate localDate = CalendarUtil.dateBeforeWorkday(LocalDate.now(), 3);
        Assertions.assertEquals(localDate, LocalDate.of(2023, 1, 11));
    }

    @Test
    public void testDateAfterWorkday() {
        LocalDate localDate = CalendarUtil.dateAfterWorkday(LocalDate.of(2023, 10, 2), 3);
        Assertions.assertEquals(localDate, LocalDate.of(2023, 10, 9));
    }

}
