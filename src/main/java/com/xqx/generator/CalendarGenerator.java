package com.xqx.generator;

import com.xqx.calendar.CalendarDefined;
import com.xqx.calendar.DayDefined;
import com.xqx.enums.HolidayEnums;
import com.xqx.enums.LieuEnums;
import com.xqx.enums.WorkdayEnums;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class CalendarGenerator {

    private static List<CalendarDefined> getCalendarDefined() throws IllegalAccessException {
        Class<CalendarDefined> definedClass = CalendarDefined.class;
        Field[] declaredFields = definedClass.getDeclaredFields();
        List<CalendarDefined> calendarDefinedList = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            int modifiers = declaredField.getModifiers();
            if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers) && Modifier.isPublic(modifiers)) {
                CalendarDefined defined = (CalendarDefined) declaredField.get(CalendarDefined.class);
                calendarDefinedList.add(defined);
            }
        }
        return calendarDefinedList;
    }

    public static void generate() throws IllegalAccessException, IOException {
        List<CalendarDefined> calendarDefined = CalendarGenerator.getCalendarDefined();

        Map<LocalDate, WorkdayEnums> workDay = new TreeMap<>();
        Map<LocalDate, LieuEnums> lieu = new TreeMap<>();
        Map<LocalDate, HolidayEnums> holiday = new TreeMap<>();
        Map<LocalDate, HolidayEnums> weekend = new TreeMap<>();

        for (CalendarDefined defined : calendarDefined) {

            Map<LocalDate, HolidayEnums> holidayEnumsMap = holiday(defined);
            holidayEnumsMap.forEach((key, value) -> holiday.merge(key, value, (v1, v2) -> v1));

            Map<LocalDate, WorkdayEnums> workdayEnumsMap = workDay(defined, holidayEnumsMap);
            workdayEnumsMap.forEach((key, value) -> workDay.merge(key, value, (v1, v2) -> v1));

            Map<LocalDate, LieuEnums> lieuEnumsMap = lieu(defined);
            lieuEnumsMap.forEach((key, value) -> lieu.merge(key, value, (v1, v2) -> v1));

            Map<LocalDate, HolidayEnums> dateHolidayEnumsMap = weekend(defined.getYear(), workdayEnumsMap);
            dateHolidayEnumsMap.forEach((key, value) -> weekend.merge(key, value, (v1, v2) -> v1));
        }

        // 渲染java模板数据
        TemplateEngine engine = new TemplateEngine();
        ITemplateResolver iTemplateResolver = textTemplateResolver();
        engine.addTemplateResolver(iTemplateResolver);
        Context context = new Context(Locale.CHINA);
        context.setVariable("workDayMap1", workDay);
        context.setVariable("holidayMap", holiday);
        context.setVariable("lieuMap", lieu);
        context.setVariable("weekendMap", weekend);
        //调用引擎，处理模板和数据
        String out = engine.process("days.java.txt", context);
        System.out.println("result:"+out);

        FileWriter writer;
        try {

            var file = new File("E:\\idea-workplace\\src\\ChineseCalendar\\src\\main\\java\\com\\xqx\\calendar\\Days.java");
            if (file.exists()) {
                file.delete();
            }
            writer = new FileWriter("E:\\idea-workplace\\src\\ChineseCalendar\\src\\main\\java\\com\\xqx\\calendar\\Days.java");
            writer.write(out);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ITemplateResolver textTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(1);
        // templateResolver.setResolvablePatterns(Collections.singleton("generator/*"));
        templateResolver.setPrefix("/generator/");
        templateResolver.setSuffix(".txt");
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }


    private static Map<LocalDate, LieuEnums> lieu(CalendarDefined calendarDefined) {

        EnumMap<HolidayEnums, DayDefined> days = calendarDefined.getDays();

        Map<LocalDate, LieuEnums> map = new TreeMap<>();

        for (Map.Entry<HolidayEnums, DayDefined> holiday : days.entrySet()) {
            DayDefined value = holiday.getValue();
            List<LocalDate> lieudays = value.getLieudays();
            if (lieudays == null) {
                continue;
            }
            for (LocalDate day : lieudays) {
                map.put(day, value.getHolidayType().getLieuType());
            }
        }
        return map;
    }

    public static Map<LocalDate, HolidayEnums> weekend(Integer year, Map<LocalDate, WorkdayEnums> workDay) {
        List<LocalDate> weekend = weekend(year);
        Map<LocalDate, HolidayEnums> map = new TreeMap<>();
        for (LocalDate localDate : weekend) {
            if (!workDay.containsKey(localDate)) {
                map.put(localDate, HolidayEnums.WEEKEND_DAY);
            }
        }
        return map;
    }


    private static Map<LocalDate, HolidayEnums> holiday(CalendarDefined calendarDefined) {
        // Integer year = calendarDefined.getYear();
        EnumMap<HolidayEnums, DayDefined> days = calendarDefined.getDays();

        Map<LocalDate, HolidayEnums> map = new TreeMap<>();

        for (Map.Entry<HolidayEnums, DayDefined> holiday : days.entrySet()) {
            DayDefined value = holiday.getValue();
            List<LocalDate> holidays = value.getHolidays();
            if (holidays == null) {
                continue;
            }
            for (LocalDate day : holidays) {
                map.put(day, value.getHolidayType());
            }
        }
        return map;
    }

    private static List<LocalDate> weekend(Integer year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        List<LocalDate> list = new ArrayList<>();
        var tmp = startDate;
        var valid = true;
        do {
            tmp = tmp.plusDays(1);
            if (tmp.isBefore(endDate)) {
                var dayOfWeek = tmp.getDayOfWeek();
                if ((dayOfWeek.compareTo(DayOfWeek.SATURDAY) == 0
                        || dayOfWeek.compareTo(DayOfWeek.SUNDAY) == 0)
                ) {
                    list.add(tmp);
                }
            } else {
                valid = false;
            }
        } while (valid);

        var dayOfWeek = endDate.getDayOfWeek();
        if (dayOfWeek.compareTo(DayOfWeek.SATURDAY) == 0 || dayOfWeek.compareTo(DayOfWeek.SUNDAY) == 0) {
            list.add(endDate);
        }
        return list;
    }


    private static Map<LocalDate, WorkdayEnums> workDay(CalendarDefined calendarDefined, Map<LocalDate, HolidayEnums> holiday) {
        Integer year = calendarDefined.getYear();
        Map<LocalDate, WorkdayEnums> workDayMap = normalWorkDay(year, holiday);
        EnumMap<HolidayEnums, DayDefined> days = calendarDefined.getDays();
        for (Map.Entry<HolidayEnums, DayDefined> work : days.entrySet()) {
            DayDefined value = work.getValue();
            List<LocalDate> workdays = value.getWorkdays();
            if (workdays == null) {
                continue;
            }
            for (LocalDate workday : workdays) {
                workDayMap.put(workday, value.getHolidayType().getWorkdayType());
            }
        }
        return workDayMap;
    }

    private static Map<LocalDate, WorkdayEnums> normalWorkDay(Integer year, Map<LocalDate, HolidayEnums> holiday) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        Map<LocalDate, WorkdayEnums> map = new TreeMap<>();

        var tmp = startDate;
        var valid = true;
        do {
            tmp = tmp.plusDays(1);
            if (tmp.isBefore(endDate)) {
                var dayOfWeek = tmp.getDayOfWeek();
                if (dayOfWeek.compareTo(DayOfWeek.FRIDAY) <= 0 && !holiday.containsKey(tmp)) {
                    map.put(tmp, WorkdayEnums.NORMAL_WORK_DAY);
                }
            } else {
                valid = false;
            }
        } while (valid);

        var dayOfWeek = endDate.getDayOfWeek();
        if (dayOfWeek.compareTo(DayOfWeek.FRIDAY) <= 0) {
            map.put(endDate, WorkdayEnums.NORMAL_WORK_DAY);
        }
        return map;
    }

    public static void main(String[] args) throws IllegalAccessException, IOException {
        /*LocalDate date = LocalDate.now();
        List<CalendarDefined> calendarDefined = CalendarGenerator.getCalendarDefined();
        System.err.println(calendarDefined);*/
        generate();
    }

}
