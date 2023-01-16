package com.xqx.enums;

/**
 *  调休类型
 */
public enum LieuEnums {

    NEW_YEARS_LIEU_DAY("New Year's Day LIEU", "元旦调休", 1),
    SPRING_FESTIVAL_DAY_LIEU_DAY("Spring Festival LIEU", "春节调休", 3),
    TOMB_SWEEPING_DAY_LIEU_DAY("Tomb-sweeping Day LIEU", "清明调休", 1),
    LABOUR_DAY_LIEU_DAY("Labour Day LIEU", "劳动节 调休", 1),
    DRAGON_BOAT_FESTIVAL_LIEU_DAY("Dragon Boat Festival LIEU", "端午 调休", 1),
    NATIONAL_DAY_LIEU_DAY("National Day LIEU", "国庆节 调休", 3),
    MID_AUTUMN_FESTIVAL_LIEU_DAY("Mid-autumn Festival LIEU", "中秋 调休", 1),

    WEEKEND_DAY_LIEU_DAY("Mid-autumn Festival LIEU", "周末 调休", 1);

    private String en;

    private String cn;

    private Integer days;

    LieuEnums(String en, String cn, Integer days) {
        this.en = en;
        this.cn = cn;
        this.days = days;
    }

}
