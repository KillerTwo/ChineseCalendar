package com.xqx.enums;

public enum WorkdayEnums {

    NORMAL_WORK_DAY("Normal Work DAY", "正常工作日"),
    NEW_YEARS_WORK_DAY("New Year's Day", "元旦"),
    SPRING_FESTIVAL_WORK_DAY("Spring Festival", "春节"),
    TOMB_SWEEPING_DAY_WORK_DAY("Tomb-sweeping Day", "清明"),
    LABOUR_DAY_WORK_DAY("Labour Day", "劳动节"),
    DRAGON_BOAT_FESTIVAL_WORK_DAY("Dragon Boat Festival", "端午"),
    NATIONAL_DAY_WORK_DAY("National Day", "国庆节"),
    MID_AUTUMN_FESTIVAL_WORK_DAY("Mid-autumn Festival", "中秋"),
    WEEKEND_WORK_DAY("weekend", "周末");

    private String en;

    private String cn;


    WorkdayEnums(String en, String cn) {
        this.en = en;
        this.cn = cn;
    }

}
