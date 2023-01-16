package com.xqx.calendar;

import com.xqx.enums.HolidayEnums;

import java.time.LocalDate;
import java.util.*;

public final class CalendarDefined {


    /**
     *  http://www.gov.cn/zhengce/content/2022-12/08/content_5730844.htm
     *         一、元旦：2022年12月31日至2023年1月2日放假调休，共3天。
     *         二、春节：1月21日至27日放假调休，共7天。1月28日（星期六）、1月29日（星期日）上班。
     *         三、清明节：4月5日放假，共1天。
     *         四、劳动节：4月29日至5月3日放假调休，共5天。4月23日（星期日）、5月6日（星期六）上班。
     *         五、端午节：6月22日至24日放假调休，共3天。6月25日（星期日）上班。
     *         六、中秋节、国庆节：9月29日至10月6日放假调休，共8天。10月7日（星期六）、10月8日（星期日）上班。
     */
    public static final CalendarDefined YEAR_2023 =
            CalendarDefined.getInstance()
                    .year(2023)
                    .nyd(new MonthDay(1, 1), new MonthDay(1, 2))
                    .sf(new MonthDay(1, 21), new MonthDay(1, 27))
                    .tsd(new MonthDay(4, 5), new MonthDay(4, 5))
                    .ld(new MonthDay(4, 29), new MonthDay(5, 3))
                    .dbf(new MonthDay(6, 22), new MonthDay(6, 24))
                    .maf(new MonthDay(9, 29), new MonthDay(9, 29))
                    .nd(new MonthDay(9, 30), new MonthDay(10, 6))
                    .work(HolidayEnums.SPRING_FESTIVAL, new MonthDay(1, 28), new MonthDay(1, 29))
                    .work(HolidayEnums.LABOUR_DAY, new MonthDay(4, 23), new MonthDay(5, 6))
                    .work(HolidayEnums.DRAGON_BOAT_FESTIVAL, new MonthDay(6, 25))
                    .work(HolidayEnums.NATIONAL_DAY, new MonthDay(10, 7), new MonthDay(10, 8))
                    .lieu(HolidayEnums.SPRING_FESTIVAL, new MonthDay(1, 26), new MonthDay(1, 27))
                    .lieu(HolidayEnums.LABOUR_DAY, new MonthDay(5,2), new MonthDay(5,3))
                    .lieu(HolidayEnums.DRAGON_BOAT_FESTIVAL, new MonthDay(6, 23), new MonthDay(6, 23))
                    .lieu(HolidayEnums.NATIONAL_DAY, new MonthDay(10, 6), new MonthDay(10, 7));


    /**
     *  http://www.gov.cn/zhengce/content/2021-10/25/content_5644835.htm
     *         一、元旦：2022年1月1日至3日放假，共3天。
     *         二、春节：1月31日至2月6日放假调休，共7天。1月29日（星期六）、1月30日（星期日）上班。
     *         三、清明节：4月3日至5日放假调休，共3天。4月2日（星期六）上班。
     *         四、劳动节：4月30日至5月4日放假调休，共5天。4月24日（星期日）、5月7日（星期六）上班。
     *         五、端午节：6月3日至5日放假，共3天。
     *         六、中秋节：9月10日至12日放假，共3天。
     *         七、国庆节：10月1日至7日放假调休，共7天。10月8日（星期六）、10月9日（星期日）上班。
     */
    public static final CalendarDefined YEAR_2022 =
            CalendarDefined.getInstance()
                    .year(2022).nyd(new MonthDay(1, 1), new MonthDay(1, 3))
                    .sf(new MonthDay(1, 31), new MonthDay(2, 6))
                    .tsd(new MonthDay(4, 3), new MonthDay(4, 5))
                    .ld(new MonthDay(4, 30), new MonthDay(5, 4))
                    .dbf(new MonthDay(6, 3), new MonthDay(6, 5))
                    .maf(new MonthDay(9, 10), new MonthDay(9, 12))
                    .nd(new MonthDay(10, 1), new MonthDay(10, 7))
                    .work(HolidayEnums.SPRING_FESTIVAL, new MonthDay(1, 29), new MonthDay(1, 30))
                    .work(HolidayEnums.LABOUR_DAY, new MonthDay(4, 24), new MonthDay(5, 7))
                    .work(HolidayEnums.DRAGON_BOAT_FESTIVAL, new MonthDay(6, 25))
                    .work(HolidayEnums.NATIONAL_DAY, new MonthDay(10, 8), new MonthDay(10, 9))
                    .lieu(HolidayEnums.SPRING_FESTIVAL, new MonthDay(2, 3), new MonthDay(2, 4))
                    .lieu(HolidayEnums.TOMB_SWEEPING_DAY, new MonthDay(4, 4), new MonthDay(4, 4))
                    .lieu(HolidayEnums.LABOUR_DAY, new MonthDay(5,3), new MonthDay(5,4))
                    .lieu(HolidayEnums.NATIONAL_DAY, new MonthDay(10, 6), new MonthDay(10, 7));



    private Integer year;

    private EnumMap<HolidayEnums, DayDefined> days = new EnumMap<>(HolidayEnums.class);


    record MonthDay(Integer month, Integer day){}

    public static CalendarDefined getInstance() {
        return new CalendarDefined();
    }

    public CalendarDefined year(Integer year) {
        this.setYear(year);
        return this;
    }

    /**
     *  元旦
     * @return CalendarDefined
     */
    public CalendarDefined nyd(MonthDay from, MonthDay to) {
        var startDay = LocalDate.of(year, from.month, from.day);
        var endDay = LocalDate.of(year, to.month, to.day);

        List<LocalDate> date = new ArrayList<>();
        date.add(startDay);

        boolean dayValid = true;
        var tmp = startDay;
        do {
            tmp = tmp.plusDays(1);
            if (tmp.isBefore(endDay)) {
                date.add(tmp);
            } else {
                dayValid = false;
            }
        } while (dayValid);
        date.add(endDay);
        var day = DayDefined.getInstance(HolidayEnums.NEW_YEARS_DAY).holidays(date);
        this.getDays().put(HolidayEnums.NEW_YEARS_DAY, day);
        return this;
    }

    /**
     *  春节
     * @return CalendarDefined
     */
    public CalendarDefined sf(MonthDay from, MonthDay to) {
        var startDay = LocalDate.of(year, from.month, from.day);
        var endDay = LocalDate.of(year, to.month, to.day);

        List<LocalDate> date = new ArrayList<>();
        date.add(startDay);

        boolean dayValid = true;
        var tmp = startDay;
        do {
            tmp = tmp.plusDays(1);
            if (tmp.isBefore(endDay)) {
                date.add(tmp);
            } else {
                dayValid = false;
            }
        } while (dayValid);
        date.add(endDay);
        var day = DayDefined.getInstance(HolidayEnums.SPRING_FESTIVAL).holidays(date);
        this.getDays().put(HolidayEnums.SPRING_FESTIVAL, day);
        return this;
    }

    /**
     *  清明节
     * @return CalendarDefined
     */
    public CalendarDefined tsd(MonthDay from, MonthDay to) {
        var startDay = LocalDate.of(year, from.month, from.day);
        var endDay = LocalDate.of(year, to.month, to.day);

        List<LocalDate> date = new ArrayList<>();
        date.add(startDay);

        boolean dayValid = true;
        var tmp = startDay;
        do {
            tmp = tmp.plusDays(1);
            if (tmp.isBefore(endDay)) {
                date.add(tmp);
            } else {
                dayValid = false;
            }
        } while (dayValid);
        date.add(endDay);
        var day = DayDefined.getInstance(HolidayEnums.TOMB_SWEEPING_DAY).holidays(date);
        this.getDays().put(HolidayEnums.TOMB_SWEEPING_DAY, day);
        return this;
    }

    /**
     *  劳动节
     * @return CalendarDefined
     */
    public CalendarDefined ld(MonthDay from, MonthDay to) {
        var startDay = LocalDate.of(year, from.month, from.day);
        var endDay = LocalDate.of(year, to.month, to.day);

        List<LocalDate> date = new ArrayList<>();
        date.add(startDay);

        boolean dayValid = true;
        var tmp = startDay;
        do {
            tmp = tmp.plusDays(1);
            if (tmp.isBefore(endDay)) {
                date.add(tmp);
            } else {
                dayValid = false;
            }
        } while (dayValid);
        date.add(endDay);
        var day = DayDefined.getInstance(HolidayEnums.LABOUR_DAY).holidays(date);
        this.getDays().put(HolidayEnums.LABOUR_DAY, day);
        return this;
    }

    /**
     *  端午节
     * @return CalendarDefined
     */
    public CalendarDefined dbf(MonthDay from, MonthDay to) {
        var startDay = LocalDate.of(year, from.month, from.day);
        var endDay = LocalDate.of(year, to.month, to.day);

        List<LocalDate> date = new ArrayList<>();
        date.add(startDay);

        boolean dayValid = true;
        var tmp = startDay;
        do {
            tmp = tmp.plusDays(1);
            if (tmp.isBefore(endDay)) {
                date.add(tmp);
            } else {
                dayValid = false;
            }
        } while (dayValid);
        date.add(endDay);
        var day = DayDefined.getInstance(HolidayEnums.DRAGON_BOAT_FESTIVAL).holidays(date);
        this.getDays().put(HolidayEnums.DRAGON_BOAT_FESTIVAL, day);
        return this;
    }

    /**
     *  国庆节
     * @return CalendarDefined
     */
    public CalendarDefined nd(MonthDay from, MonthDay to) {
        var startDay = LocalDate.of(year, from.month, from.day);
        var endDay = LocalDate.of(year, to.month, to.day);

        List<LocalDate> date = new ArrayList<>();
        // date.add(startDay);

        boolean dayValid = true;
        var tmp = startDay;
        do {
            tmp = tmp.plusDays(1);
            if (tmp.isBefore(endDay)) {
                date.add(tmp);
            } else {
                dayValid = false;
            }
        } while (dayValid);
        date.add(endDay);
        var day = DayDefined.getInstance(HolidayEnums.NATIONAL_DAY).holidays(date);
        this.getDays().put(HolidayEnums.NATIONAL_DAY, day);
        return this;
    }

    /**
     *  中秋节
     * @return CalendarDefined
     */
    public CalendarDefined maf(MonthDay from, MonthDay to) {
        var startDay = LocalDate.of(year, from.month, from.day);
        var endDay = LocalDate.of(year, to.month, to.day);

        List<LocalDate> date = new ArrayList<>();
        date.add(startDay);

        boolean dayValid = true;
        var tmp = startDay;
        do {
            tmp = tmp.plusDays(1);
            if (tmp.isBefore(endDay)) {
                date.add(tmp);
            } else {
                dayValid = false;
            }
        } while (dayValid);
        date.add(endDay);
        var day = DayDefined.getInstance(HolidayEnums.MID_AUTUMN_FESTIVAL).holidays(date);
        this.getDays().put(HolidayEnums.MID_AUTUMN_FESTIVAL, day);
        return this;
    }

    /**
     *  工作日
     * @param type 假期名
     * @param work 补班的工作日
     * @return  CalendarDefined
     */
    public CalendarDefined work(HolidayEnums type, MonthDay... work) {
        DayDefined dayDefined = this.getDays().get(type);
        List<LocalDate> date = new ArrayList<>();
        for (MonthDay monthDay : work) {
            var day = LocalDate.of(year, monthDay.month, monthDay.day);
            date.add(day);
        }
        dayDefined.workdays(date);
        return this;
    }

    /**
     *  调休
     * @param type 假期名
     * @param from 开始时间
     * @param to 结束时间
     * @return CalendarDefined
     */
    public CalendarDefined lieu(HolidayEnums type, MonthDay from, MonthDay to) {
        DayDefined dayDefined = this.getDays().get(type);
        var startDay = LocalDate.of(year, from.month, from.day);
        var endDay = LocalDate.of(year, to.month, to.day);

        List<LocalDate> date = new ArrayList<>();
        date.add(startDay);

        boolean dayValid = true;
        var tmp = startDay;
        do {
            tmp = tmp.plusDays(1);
            if (tmp.isBefore(endDay)) {
                date.add(tmp);
            } else {
                dayValid = false;
            }
        } while (dayValid);
        date.add(endDay);
        dayDefined.lieudays(date);
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public EnumMap<HolidayEnums, DayDefined> getDays() {
        return days;
    }

    public void setDays(EnumMap<HolidayEnums, DayDefined> days) {
        this.days = days;
    }
}
