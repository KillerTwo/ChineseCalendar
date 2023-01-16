#### Java判断中国节假日、工作日

```java
// 是否是节假日
boolean holiday = CalendarUtil.isHoliday(LocalDate.of(2023, 1, 21));
Assertions.assertTrue(holiday);
// 是否是工作日
boolean workday = CalendarUtil.isWorkday(LocalDate.of(2023, 1, 16));
Assertions.assertTrue(workday);

// 是否是周末
boolean weekend = CalendarUtil.isWeekend(LocalDate.of(2023, 1, 15));
Assertions.assertTrue(weekend);

// 是否是调休
boolean lieu = CalendarUtil.isInLieu(LocalDate.of(2023, 10, 6));
Assertions.assertTrue(lieu);

// 距离当前时间3天前的工作日
LocalDate localDate = CalendarUtil.dateBeforeWorkday(LocalDate.now(), 3);
Assertions.assertEquals(localDate, LocalDate.of(2023, 1, 11));

// 距离当前时间3天后的工作日
LocalDate localDate = CalendarUtil.dateAfterWorkday(LocalDate.of(2023, 10, 2), 3);
Assertions.assertEquals(localDate, LocalDate.of(2023, 10, 9));
```