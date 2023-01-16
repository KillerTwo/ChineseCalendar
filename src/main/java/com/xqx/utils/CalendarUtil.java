package com.xqx.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import com.xqx.calendar.Days;

public class CalendarUtil {

    public static boolean isWorkday(LocalDate date) {
        return Days.WORKDAYS.containsKey(toStr(date));
    }

    public static boolean isWorkday(Date date) {
        return Days.WORKDAYS.containsKey(toStr(fromDate(date)));
    }

    public static boolean isHoliday(LocalDate date) {
        return isHoliday(date, false);
    }

    public static boolean isHoliday(Date date) {
        return isHoliday(date, false);
    }

    public static boolean isHoliday(LocalDate date, boolean includeWeekend) {
        if (includeWeekend) {
            return isHolidayOrWeekend(date);
        }
        return Days.HOLIDAYS.containsKey(toStr(date));
    }

    public static boolean isHoliday(Date date, boolean includeWeekend) {
        if (includeWeekend) {
            return isHolidayOrWeekend(date);
        }
        return Days.HOLIDAYS.containsKey(toStr(fromDate(date)));
    }

    public static boolean isWeekend(LocalDate date) {
        return Days.WEEKENDS.containsKey(toStr(date));
    }

    public static boolean isWeekend(Date date) {
        return Days.WEEKENDS.containsKey(toStr(fromDate(date)));
    }

    public static boolean isHolidayOrWeekend(LocalDate date) {
        return Days.HOLIDAYS.containsKey(toStr(date)) || Days.WEEKENDS.containsKey(toStr(date));
    }

    public static boolean isHolidayOrWeekend(Date date) {
        return Days.HOLIDAYS.containsKey(toStr(fromDate(date))) || Days.WEEKENDS.containsKey(toStr(fromDate(date)));
    }

    /**
     *  是否是调休日
     * @param date 日期
     * @return true/false
     */
    public static boolean isInLieu(LocalDate date) {
        return Days.LIEU_DAYS.containsKey(toStr(date));
    }

    /**
     *  是否是调休日
     * @param date 日期
     * @return true/false
     */
    public static boolean isInLieu(Date date) {
        return Days.LIEU_DAYS.containsKey(toStr(fromDate(date)));
    }

    /**
     *  距离当前日期limit天前的工作日
     * @param limit 间隔天数
     * @return LocalDate
     */
    public static LocalDate nowBeforeWorkday(int limit) {
        LocalDate now = LocalDate.now();
        return dateBeforeWorkday(now, limit);
    }

    /**
     *  距离当前日期limit天后的工作日
     * @param limit 间隔天数
     * @return LocalDate
     */
    public static LocalDate nowAfterWorkday(int limit) {
        LocalDate now = LocalDate.now();
        return dateAfterWorkday(now, limit);
    }

    /**
     *  距离当前日期limit天前的工作日
     * @param limit 间隔天数
     * @return LocalDate
     */
    public static LocalDate dateBeforeWorkday(LocalDate date, int limit) {
        var tmp = date.minusDays(1);;
        int i = 1;
        while (i <= limit) {
            if (isWorkday(tmp)) {
                if (i >= limit) {
                    return tmp;
                }
                i++;
            }
            tmp = tmp.minusDays(1);
        }
        return null;
    }

    /**
     *  距离当前日期limit天后的工作日
     * @param limit 间隔天数
     * @return LocalDate
     */
    public static LocalDate dateAfterWorkday(LocalDate date, int limit) {
        var tmp = date.plusDays(1);
        int i = 1;
        while (i <= limit) {
            if (isWorkday(tmp)) {
                if (i >= limit) {
                    return tmp;
                }
                i++;
            }
            tmp = tmp.plusDays(1);
        }
        return null;
    }



    private static LocalDate fromDate(Date date) {

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return localDate;
    }

    private static String toStr(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        return formatter.format(date);
    }


}
