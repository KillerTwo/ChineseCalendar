package com.xqx.calendar;

import com.xqx.enums.HolidayEnums;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class DayDefined {

    /**
     *  节假日类型
     */
    private HolidayEnums holidayType;

    /**
     *  放假的日期
     */
    private List<LocalDate> holidays;

    /**
     *  工作日的日期
     */
    private List<LocalDate> workdays;

    /**
     *  调休的日期
     */
    private List<LocalDate> lieudays;


    public static DayDefined getInstance(HolidayEnums type) {
        var d = new DayDefined();
        d.setHolidayType(type);
        return d;
    }

    public DayDefined holidays(List<LocalDate> holidayList) {
        if (this.holidays == null) {
            this.holidays = new ArrayList<>();
        }
        this.holidays.addAll(holidayList);
        return this;
    }

    public DayDefined workdays(List<LocalDate> workdayList) {
        if (this.workdays == null) {
            this.workdays = new ArrayList<>();
        }
        this.workdays.addAll(workdayList);
        return this;
    }

    public DayDefined lieudays(List<LocalDate> lieudayList) {
        if (this.lieudays == null) {
            this.lieudays = new ArrayList<>();
        }
        this.lieudays.addAll(lieudayList);
        return this;
    }


    public HolidayEnums getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(HolidayEnums holidayType) {
        this.holidayType = holidayType;
    }

    public List<LocalDate> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<LocalDate> holidays) {
        this.holidays = holidays;
    }

    public List<LocalDate> getWorkdays() {
        return workdays;
    }

    public void setWorkdays(List<LocalDate> workdays) {
        this.workdays = workdays;
    }

    public List<LocalDate> getLieudays() {
        return lieudays;
    }

    public void setLieudays(List<LocalDate> lieudays) {
        this.lieudays = lieudays;
    }
}
