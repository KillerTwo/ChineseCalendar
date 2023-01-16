package com.xqx.enums;

public enum HolidayEnums {

    NEW_YEARS_DAY("New Year's Day", "元旦", 1, WorkdayEnums.NEW_YEARS_WORK_DAY, LieuEnums.NEW_YEARS_LIEU_DAY),
    SPRING_FESTIVAL("Spring Festival", "春节", 3,  WorkdayEnums.SPRING_FESTIVAL_WORK_DAY, LieuEnums.SPRING_FESTIVAL_DAY_LIEU_DAY),
    TOMB_SWEEPING_DAY("Tomb-sweeping Day", "清明", 1,  WorkdayEnums.TOMB_SWEEPING_DAY_WORK_DAY, LieuEnums.TOMB_SWEEPING_DAY_LIEU_DAY),
    LABOUR_DAY("Labour Day", "劳动节", 1,  WorkdayEnums.LABOUR_DAY_WORK_DAY, LieuEnums.LABOUR_DAY_LIEU_DAY),
    DRAGON_BOAT_FESTIVAL("Dragon Boat Festival", "端午", 1,  WorkdayEnums.DRAGON_BOAT_FESTIVAL_WORK_DAY, LieuEnums.LABOUR_DAY_LIEU_DAY),
    NATIONAL_DAY("National Day", "国庆节", 3,  WorkdayEnums.NATIONAL_DAY_WORK_DAY, LieuEnums.NATIONAL_DAY_LIEU_DAY),
    MID_AUTUMN_FESTIVAL("Mid-autumn Festival", "中秋", 1,  WorkdayEnums.MID_AUTUMN_FESTIVAL_WORK_DAY, LieuEnums.MID_AUTUMN_FESTIVAL_LIEU_DAY),

    WEEKEND_DAY("Mid-autumn Festival", "周末", 1,  WorkdayEnums.WEEKEND_WORK_DAY, LieuEnums.WEEKEND_DAY_LIEU_DAY);

    private String en;

    private String cn;

    private Integer days;


    private WorkdayEnums workdayType;

    private LieuEnums lieuType;

    HolidayEnums(String en, String cn, Integer days, WorkdayEnums workdayType, LieuEnums lieuType) {
        this.en = en;
        this.cn = cn;
        this.days = days;
        this.workdayType = workdayType;
        this.lieuType = lieuType;
    }


    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public WorkdayEnums getWorkdayType() {
        return workdayType;
    }

    public void setWorkdayType(WorkdayEnums workdayType) {
        this.workdayType = workdayType;
    }

    public LieuEnums getLieuType() {
        return lieuType;
    }

    public void setLieuType(LieuEnums lieuType) {
        this.lieuType = lieuType;
    }
}
