package lonchin.user.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * 日期工具类
 *
 * @author dylan
 */
public class LocalDateUtil {

    public static final String YYYY_MM = "yyyyMM";

    public static final String YYYY_MM_DD = "yyyyMMdd";


    public static final String TIME_ZONE = "GMT+8";

    /**
     * Date格式化字符串
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * DateTime格式化字符串
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * Time格式化字符串
     */
    public static final String TIME_FORMAT = "HH:mm:ss";


    public static final String TIME_H_FORMAT = "HH:mm";


    public static final int BREAK_MINUTES = 4;

    public static final int MINUTES_24 = 24;

    public final static ZoneOffset CHINA_ZONE = ZoneOffset.of("+8");

    public static List<LocalDate> datesOfWeek(int year, long calendarWeek) {
        LocalDate start = LocalDate.ofYearDay(year, 1)
                .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, calendarWeek)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return IntStream.range(0, 7).mapToObj(start::plusDays).collect(toList());
    }

    public static LocalTime fromString(String localtime) {
        return LocalTime.parse(localtime, DateTimeFormatter.ofPattern(TIME_FORMAT));
    }


    public static Boolean isConflict(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime.isAfter(
                endTime.minusMinutes(MINUTES_24)) && startTime.isBefore(endTime.plusMinutes(MINUTES_24));
    }


    public static Boolean isConflict(LocalTime startTime, LocalTime endTime) {

        LocalDateTime now = LocalDateTime.now();
        return isConflict(now.with(startTime), now.with(endTime));
    }

    public static LocalDateTime getStartTime(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MIN);
    }


    public static LocalDateTime getEndTime(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MAX);
    }


    public static LocalDateTime getMonthStart(LocalDate date) {
        return getStartTime(date.with(TemporalAdjusters.firstDayOfMonth()));
    }

    public static LocalDateTime getMonthEnd(LocalDate date) {
        return getEndTime(date.with(TemporalAdjusters.lastDayOfMonth()));
    }

    /**
     * 设置永久时间
     *
     * @return
     */
    public static LocalDateTime getPermanentTime() {
        return LocalDateTime.of(2049, 12, 31, 23, 59, 59);
    }

    /**
     * 根据生日获取年龄
     *
     * @param birthdayTime
     * @return
     */
    public static String getAge(LocalDateTime birthdayTime) {
        if (birthdayTime != null) {
            String birthday = "";
            Period d = Period.between(birthdayTime.toLocalDate(), LocalDate.now());
            long year = d.get(ChronoUnit.YEARS);
            if (year > 0) {
                birthday = year + "岁";
            }
            return birthday;
        }
        return null;
    }


    public static String getYearMonth(YearMonth yearMonth) {
        return yearMonth.toString();
    }

    public static String getYearMonthDay(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
    }

    public static long getBetweenHour(LocalDateTime one, LocalDateTime other) {
        return getBetweenDuration(one, other).toHours();
    }


    public static long getBetweenSecond(LocalDateTime one, LocalDateTime other) {
        return getBetweenDuration(one, other).getSeconds();
    }


    public static long getBetweenDay(LocalDateTime one, LocalDateTime other) {
        return getBetweenDuration(one, other).toDays();
    }

    public static long getBetweenMinute(LocalDateTime one, LocalDateTime other) {
        return getBetweenDuration(one, other).toMinutes();
    }

    private static Duration getBetweenDuration(LocalDateTime one, LocalDateTime other) {
        return Duration.between(one, other);
    }


    /**
     * 判断是否同一个时分
     *
     * @return
     */
    public static boolean isSameHourMinute(LocalDateTime one, LocalDateTime other) {

        return isSameDate(one, other) &&
                one.getHour() == other.getHour() &&
                one.getMinute() == other.getMinute();
    }

    /**
     * 判断是否是同一天
     *
     * @return
     */
    public static boolean isSameDate(LocalDateTime one, LocalDateTime other) {

        return isSameMonth(one, other) &&
                one.getDayOfMonth() == other.getDayOfMonth();
    }

    /**
     * 判断是否是同一年月
     *
     * @param one
     * @return
     */
    public static boolean isSameMonth(LocalDateTime one, LocalDateTime other) {
        if (Objects.isNull(one) || Objects.isNull(other)) {
            return false;
        }
        int month = other.getMonthValue();
        int year = other.getYear();
        return one.getYear() == year &&
                one.getMonthValue() == month;
    }

    /**
     * 功能描述: 将年月日与时分合并
     * @author chenlc
     * @date 2020/10/15
     * @param date yyyy-MM-dd
     * @param time HH:mm
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getByDateJoinTime (LocalDate date, LocalTime time) {
        return LocalDateTime.parse(
                (
                        date.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                        + " "
                        + time.format(DateTimeFormatter.ofPattern(TIME_H_FORMAT))
                        + ":00"
                ),
                DateTimeFormatter.ofPattern(DATETIME_FORMAT)
        );
    }

    /**
     * 功能描述: 格式化时间
     * @author chenlc
     * @date 2020/12/30
     * @param dateTime
     * @param pattern
     * @return java.lang.String
     */
    public static String formatToString (LocalDateTime dateTime, String pattern) {
         return DateTimeFormatter.ofPattern(pattern).format(dateTime);
    }

    /**
     * 功能描述: 获取周一
     * @author chenlc
     * @date 2021/1/11
     * @param date
     * @return java.lang.String
     */
    public static String getMondayDayOfBeforeWeek (LocalDate date) {
        return date.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).plusDays(1).format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * 功能描述: 获取周日
     * @author chenlc
     * @date 2021/1/11
     * @param date
     * @return java.lang.String
     */
    public static String getSundayDayOfBeforeWeek (LocalDate date) {
        return date.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).minusDays(1).format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * 功能描述: 时间字符串转LocalDate
     * @author chenlc
     * @date 2021/1/12
     * @param date
     * @return java.time.LocalDate
     */
    public static LocalDate formatToDate (String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * 功能描述: 时间字符串转LocalDateTime
     * @author chenlc
     * @date 2021/1/12
     * @param datetime
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime formatToDateTime (String datetime) {
        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }

    /**
     * 功能描述: 获取某天的开始时间
     * @author chenlc
     * @date 2021/1/12
     * @param startTime
     * @return java.time.LocalDateTime
     */
    public static String getStartTimeOfDay (LocalDateTime startTime) {
        return formatToString(startTime, DATETIME_FORMAT);
    }

    /**
     * 功能描述: 获取某天的结束时间
     * @author chenlc
     * @date 2021/1/12
     * @param endTime
     * @return java.time.LocalDateTime
     */
    public static String getEndTimeOfDay (LocalDateTime endTime) {
        return formatToString(endTime, DATETIME_FORMAT);
    }

    /**
     * 功能描述: 获取某天的开始时间
     * @author chenlc
     * @date 2021/1/12
     * @param date
     * @return java.time.LocalDateTime
     */
    public static String getStartTimeOfDay (String date) {
        return getStartTimeOfDay(formatToDateTime(date));
    }

    /**
     * 功能描述: 获取某天的结束时间
     * @author chenlc
     * @date 2021/1/12
     * @param date
     * @return java.time.LocalDateTime
     */
    public static String getEndTimeOfDay (String date) {
        return getEndTimeOfDay(formatToDateTime(date));
    }

}
