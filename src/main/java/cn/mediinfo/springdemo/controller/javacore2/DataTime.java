package cn.mediinfo.springdemo.controller.javacore2;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/*
 *@title DataTime
 *@description
 *@author thj
 *@version 1.0
 *@create 2024/1/21 10:10
 */
public class DataTime {

    /**
     * 时间和日期API
     * java时间类型有三种：分别是java.util.Date、java.sql.Date、java.sql.Timestamp
     * java.util.Date：表示一个时间，精确到毫秒，但是不表示一个时区，所以它和时区无关。 java1.0 引入
     * java.sql.Date：表示一个日期，精确到天，但是不表示一个时区，所以它和时区无关。
     * java.sql.Timestamp：表示一个时间，精确到纳秒，但是不表示一个时区，所以它和时区无关。
     * java.util.Calendar：表示一个时间，精确到毫秒，但是它表示一个时区，所以它和时区有关。  java1.1引入
     * java.time： Java 8引入了新的时间和日期API，它是线程安全的，而且定义了很多新的操作，比如计算两个日期之间的天数等等。比如：LocalDate、LocalTime、LocalDateTime、Instant、Duration、Period等等。
     */
    public void example(){
        // java.util.Date和java.util.Calendar
        // java.util.Date是Java标准库中最早的时间类型，它表示一个精确到毫秒的时间戳。java.util.Date可以通过构造函数创建，或者通过System.currentTimeMillis()方法获取当前时间戳得到。

        // 创建当前时间类型实例
        Date now = new Date();

        //获取当前时间戳
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);

        //java.util.Calendar是Java标准库中的另一个时间类型，它提供了更多的时间操作方法。我们可以通过Calendar.getInstance()方法获取一个Calendar实例，然后调用其方法来操作时间。
        // 获取当前时间对应的Calendar实例
        Calendar calendar = Calendar.getInstance();

        // 设置Calendar为特定时间
        calendar.set(2021, Calendar.JANUARY, 1, 0, 0, 0);

        //二、Java 8新时间API
        //为了解决上述问题，Java 8引入了新的时间API，即java.time包下的类。这些类提供了更加简洁、清晰、易用的API，而且是不可变的（immutable），不存在线程安全问题。

        //1. LocalDate、LocalTime、LocalDateTime
        //LocalDate、LocalTime、LocalDateTime分别表示日期、时间、日期时间，这些类都是不可变的。我们可以通过它们的静态工厂方法创建实例。
        // 创建特定日期、时间、日期时间实例
        LocalDate date2 = LocalDate.of(2021, 1, 1);  // 2021-01-01
        LocalTime time = LocalTime.of(0, 0, 0); // 00:00:00
        LocalDateTime dateTime = LocalDateTime.of(2021, 1, 1, 0, 0, 0); // 2021-01-01T00:00:00

        // 获取当前日期、时间、日期时间
        LocalDate today = LocalDate.now(); // 2021-01-01
        LocalTime now2 = LocalTime.now(); // 00:00:00
        LocalDateTime currentTime = LocalDateTime.now();// 2021-01-01T00:00:00

        //这些类提供了丰富的时间操作方法。例如，我们可以使用plusXxx()、minusXxx()方法对时间进行加减，使用withXxx()方法修改时间的各个部分。
        // 对时间进行加减
        LocalDateTime nextYear = dateTime.plusYears(1); // 2022-01-01T00:00:00
        LocalDate yesterday = today.minusDays(1); // 2020-12-31

        // 修改时间的各个部分
        LocalDateTime newDateTime = dateTime.withDayOfMonth(5).withYear(2022); // 2022-01-05T00:00:00

        //2. ZonedDateTime
        //ZonedDateTime类表示带时区信息的日期时间。我们可以通过它的静态工厂方法of()创建实例。

        // 创建特定时区、日期时间实例
        ZoneId zone = ZoneId.of("America/New_York"); // 时区
        ZonedDateTime zdt = ZonedDateTime.of(2021, 1, 1, 0, 0, 0, 0, zone); // 2021-01-01T00:00-05:00[America/New_York]

        // 获取当前时区、日期时间
        ZonedDateTime now3 = ZonedDateTime.now(); // 2021-01-01T00:00:00+08:00[Asia/Shanghai]
        //ZonedDateTime提供了一系列方法来操作时间，其中跟时区相关的操作需要注意。

        //3. Instant
        //Instant类表示时刻，它可以精确到纳秒。

        // 获取当前时刻实例
        Instant instant = Instant.now(); // 2021-01-01T00:00:00Z
        //Instant提供了丰富的时间操作方法。例如，我们可以通过plusXxx()、minusXxx()方法对时间进行加减。

         // 对时间进行加减
        Instant nextHour = instant.plusSeconds(3600); // 2021-01-01T01:00:00Z
        Instant lastMinute = instant.minusSeconds(60); // 2020-12-31T23:59:00Z

    }

    /**
     * 时间线
     * 在java中，Instant 表示时间线上的某个点。被称为“新纪元”的时间线原点被设置为 1700-01-01 00:00:00Z（即 1970-01-01 00:00:00Z）。
     * Instant 的值 Instant.Min 可往回追溯到 -1000000000-01-01 00:00:00Z（即公元前 10 亿年）。
     * Instant 提供了丰富的方法来进行日期和时间的计算
     */
    public void example2(){
        //1.1 通过 Instant 得到当前时刻。
        Instant instant = Instant.now(); // 输出当前时间：2021-01-01T00:00:00Z
        System.out.println(instant);

        //1.2 通过 Duration 得到两个时刻之间的时间差
        //note:Instant、Duration 类都是不可修改的类，所以诸如 MultipliedBy()、Plus()、Minus() 等方法都会返回一个新的实例。
        Instant start = Instant.now();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        long millis = timeElapsed.toMillis(); // 毫秒,也可以通过调用toNanos()、toMinutes()等方法得到其他单位的时间差
        long seconds = timeElapsed.getSeconds(); //在java8中，必须调用getSeconds()，而非 toSeconds()
        long nancos= timeElapsed.toNanos(); // 纳秒
        long minutes= timeElapsed.toMinutes(); // 分钟
        long hours= timeElapsed.toHours(); // 小时
        long days= timeElapsed.toDays(); // 天


        timeElapsed.multipliedBy(10); // 将时间差乘以10
        timeElapsed.dividedBy(10); // 将时间差除以10
        timeElapsed.negated(); // 将时间差取反
        timeElapsed.abs(); // 将时间差取绝对值
        timeElapsed.isNegative(); // 检查时间差是否为负数
        timeElapsed.isZero(); // 检查时间差是否为0

        boolean overTenSeconds = timeElapsed.toNanos()*10<timeElapsed.toNanos(); //检查某个算法的时间是否至少比另外一个算法快10倍


        //1.3 产生一个时刻，通过 Duration 实现该时刻与当前时刻距离既定的时间量。
        Instant now = Instant.now();
        now.plus(Duration.ofMillis(500)); // 500毫秒之后
        now.plus(Duration.ofSeconds(10)); // 10秒之后
        now.plus(Duration.ofMinutes(30)); // 30分钟之后
        now.plus(Duration.ofHours(2)); // 2小时之后
        now.plus(Duration.ofDays(1)); // 1天之后
        now.plus(Duration.ofNanos(365)); // 365纳秒之后
    }

    /**
     * 本地日期 LocalDate
     * 有很多计算并不需要时区，在某些情况下，时区甚至会成为一种障碍。假设你每天安排10：00开一次会，如果你加7天（即7 * 24 * 60 *600）到最后一次会议的时区上，那么你可能碰巧跨越了夏令时的时间调整边界，这次会议可能早1个小时或者晚一个小时。
     * 正是因为这个原因，不推荐使用时区时间，除非确实想要表示绝对时间的实例。生日、假日、计划计时等通常最好表示为本地日期。
     */
    public void example3(){
        //1.1 通过 LocalDate 得到当前日期。
        LocalDate today = LocalDate.now(); // 2021-01-01
        System.out.println(today);

        today.getDayOfWeek().getValue(); // 星期几 值为1-7，周末值为7 ，在java.util.Calendar中，星期日的值为1，星期一的值为2，以此类推，星期六的值为7

        today.getDayOfMonth(); // 一个月中的第几天

        today.getMonth(); // 月份
        today.getMonthValue(); // 月份值

        today.getYear();// 年份
        today.getDayOfYear(); // 一年中的第几天

        //1.2 通过 LocalDate 得到某个特定的日期。
        LocalDate dateOfBirth = LocalDate.of(2018, 01, 21);
        System.out.println(dateOfBirth);

        //1.3 通过 LocalDate 得到某个特定的日期，该日期是某个时刻的偏移量。
        LocalDate dateOfBirth2 = LocalDate.ofEpochDay(365);
        System.out.println(dateOfBirth2);

        //1.4 通过 LocalDate 得到某个特定的日期，该日期是某个时刻的偏移量。
        LocalDate dateOfBirth3 = LocalDate.ofYearDay(2018, 32);
        System.out.println(dateOfBirth3);

        //1.5 通过 LocalDate 得到某个特定的日期，该日期是某个时刻的偏移量。
        LocalDate dateOfBirth4 = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        System.out.println(dateOfBirth4);

        //1.6 通过 LocalDate 得到某个特定的日期，该日期是某个时刻的偏移量。
        LocalDate dateOfBirth5 = LocalDate.ofInstant(Instant.now(), ZoneId.of("America/New_York"));
        System.out.println(dateOfBirth5);


        //1.8 两个Instant之间的时间差是 Duration，两个LocalDate之间的时间差是 Period。
        LocalDate today2 = LocalDate.now();
        var period= Period.between(today2,today2);
        period.getDays(); // 天
        period.getMonths(); // 月
        period.getYears(); // 年
        period.isNegative(); // 检查时间差是否为负数
        period.isZero(); // 检查时间差是否为0

        //1.7 假设程序员日是每年的第256天，计算今年的程序员日是哪天。
        LocalDate programmersDay = LocalDate.of(2018, 1, 1).plusDays(255);
        today.with(TemporalAdjusters.firstDayOfMonth()); // 本月第一天,返回一个新的Period实例,将年月日修改为指定的值
        today.minus(period); // 本月第一天,返回一个新的Period实例,将年月日修改为指定的值


        //1.9 日期比较
        today.isBefore(today2); // 检查某个日期是否在另一个日期之前
        today.isAfter(today2); // 检查某个日期是否在另一个日期之后
        today.isEqual(today2); // 检查某个日期是否在另一个日期相等
    }

    /**
     * 日期调整器
     * 对于日程安排应用来说，经常需要计算诸如：“每个月的第一个星期二”这样的日期。这种日期调整器可以通过 TemporalAdjuster 接口来实现。
     * TemporalAdjuster 提供了大量的静态方法来实现各种日期调整器。例如，我们可以通过 TemporalAdjusters.firstDayOfMonth() 方法得到本月的第一天，通过 TemporalAdjusters.lastDayOfMonth() 方法得到本月的最后一天。
     */
    public void example4(){
       //1.1 通过 TemporalAdjusters 得到某个特定的日期调整器。
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth()); // 本月第一天
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth()); // 本月最后一天
        LocalDate firstDayOfNextMonth = today.with(TemporalAdjusters.firstDayOfNextMonth()); // 下月第一天
        LocalDate firstDayOfNextYear = today.with(TemporalAdjusters.firstDayOfNextYear()); // 下一年第一天
        LocalDate firstDayOfNextYear2 = today.with(TemporalAdjusters.firstDayOfNextYear()); // 下一年第一天
        LocalDate firstDayOfNextYear10 = today.with(TemporalAdjusters.firstDayOfNextYear()); // 下一年第一天

        LocalDate firstDayOfNextYear11 = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); // 下一个星期一
        LocalDate firstDayOfNextYear111 = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)); // 这个方法会返回指定日期之前的最近一个指定的星期几，如果指定日期就是指定的星期几，那么它会返回今天的日期。例如，如果今天是星期一，那么 previousOrSame(DayOfWeek.MONDAY) 会返回今天的日期。

        LocalDate firstDayOfNextYear12 = today.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)); // 上一个星期一
        LocalDate firstDayOfNextYear122 = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)); // 这个方法会返回指定日期之前的最近一个指定的星期几，如果指定日期就是指定的星期几，那么它会返回今天的日期。例如，如果今天是星期一，那么 previousOrSame(DayOfWeek.MONDAY) 会返回今天的日期。

        LocalDate firstDayOfNextYear13 = today.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY)); // 本月最后一个星期一
        LocalDate firstDayOfNextYear14 = today.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // 本月第一个星期一

        LocalDate firstDayOfNextYear15 = today.with(TemporalAdjusters.firstDayOfMonth()); // 本月第一天
        LocalDate firstDayOfNextYear16 = today.with(TemporalAdjusters.lastDayOfMonth()); // 本月最后一天
    }

    /**
     * 本地时间 LocalTime
     * LocalTime 表示一天中的某个时间，例如 10:15:30。可以用now()方法获取当前时间，也可以通过静态工厂方法of()创建一个实例。
     * LocalTime 适合在 日程安排、时间跟踪、时间间隔计算、交通运输应用等场景中使用。
     */
    public void example5(){
        //1.1 通过 LocalTime 得到当前时间。
        LocalTime now = LocalTime.now(); // 10:15:30
        System.out.println(now);

        //1.2 通过 LocalTime 得到某个特定的时间。
        LocalTime now2 = LocalTime.of(10, 15, 30);
        System.out.println(now2);

        //1.3 通过 LocalTime 得到某个特定的时间，该时间是某个时刻的偏移量。
        LocalTime now3 = LocalTime.ofSecondOfDay(10000);
        System.out.println(now3);

        //1.4 通过 LocalTime 得到某个特定的时间，该时间是某个时刻的偏移量。
        LocalTime now4 = LocalTime.ofNanoOfDay(1000000000);
        System.out.println(now4);

        //1.5 通过 LocalTime 得到某个特定的时间，该时间是某个时刻的偏移量。
        LocalTime now5 = LocalTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        System.out.println(now5);

        //1.6 通过 LocalTime 得到某个特定的时间，该时间是某个时刻的偏移量。
        LocalTime now6 = LocalTime.ofInstant(Instant.now(), ZoneId.of("America/New_York"));
        System.out.println(now6);
    }

    /**
     * 时区时间 ZonedDateTime
     * 时区可能比地球不规则的旋转方式引起的复杂性还要麻烦，因为它完全是人类认为造出来的概念。中国横跨了四个时区，但是中国只有一个时区，这是因为中国政府认为这样更方便。在中国，所有的时钟都显示北京时间，而不管你在哪里。这意味着在中国西部的某些地区，太阳在中午时分才到达最高点。
     * 每个时区都有一个ID ，例如：Asia/Shanghai 或者 America/New_York 。时区ID是由区域/城市构成的，其中区域是一个大陆或者洋，城市是该区域内的一个城市或者村庄。时区ID的命名遵循惯例，通常是“洲/城市”这样的格式。
     * 通过时区ID，静态方法ZoneId.of()可以创建一个时区实例。时区实例可以用来创建 ZonedDateTime 实例。
     * www.iana.org/time-zones 存储着世界上所有已知的时区，它每年都会更新数次。
     *
     * ZonedDateTime 适合在跨时区的日期和时间计算、日历系统、日志记录、航班或者火车时刻表
     */
    public void example6(){
        //1.1 通过上海时区来创建一个 ZonedDateTime
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(now);

        //1.2 通过 UTC 时区来创建一个 ZonedDateTime
        //UTC 代表“世界协调时间”，它是格林威治标准时间（GMT）的同义词。UTC 时区的偏移量是0，它是世界上最主要的时区。
        ZonedDateTime now8 = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println(now8);

        //1.3 通过 UTC 时区来创建一个 ZonedDateTime
        ZonedDateTime now9 = ZonedDateTime.now(ZoneId.of("GMT"));
        System.out.println(now9);

        //1.4通过 UTC 时区来创建一个 ZonedDateTime
        ZonedDateTime now10 = ZonedDateTime.now(ZoneId.of("GMT+8"));
        System.out.println(now10);

        //1.5 通过 UTC 时区来创建一个 ZonedDateTime
        ZonedDateTime now11 = ZonedDateTime.now(ZoneId.of("GMT-8"));
        System.out.println(now11);

        //1.6 通过 UTC 时区来创建一个 ZonedDateTime
        ZonedDateTime now12 = ZonedDateTime.now(ZoneId.of("GMT+0"));

        //1.7 API使用
        //1.7.1 通过 ZonedDateTime 得到当前时间。
        ZonedDateTime now2 = ZonedDateTime.now(); // 2021-01-01T00:00:00+08:00[Asia/Shanghai]
        System.out.println(now2);

        //1.7.2 通过 ZonedDateTime 得到某个特定的时间。
        ZonedDateTime now3 = ZonedDateTime.of(2021, 1, 1, 0, 0, 0, 0, ZoneId.of("Asia/Shanghai"));
        System.out.println(now3);

        //1.7.3 通过 ZonedDateTime 得到某个特定的时间，该时间是某个时刻的偏移量。
        ZonedDateTime now4 = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        System.out.println(now4);

        //1.7.4 通过 ZonedDateTime 得到某个特定的时间，该时间是某个时刻的偏移量。
        ZonedDateTime now5 = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("America/New_York"));
        System.out.println(now5);

        now5.plus(Duration.ofDays(1)); // 1天之后
        now5.plus(Duration.ofHours(1)); // 1小时之后
        now5.plus(Duration.ofMinutes(1)); // 1分钟之后
        now5.plus(Duration.ofSeconds(1)); // 1秒之后
        now5.plus(Duration.ofMillis(1)); // 1毫秒之后
        now5.plus(Duration.ofNanos(1)); // 1纳秒之后

        now5.minus(Duration.ofDays(1)); // 1天之前
        now5.minus(Duration.ofHours(1)); // 1小时之前
        now5.minus(Duration.ofMinutes(1)); // 1分钟之前
        now5.minus(Duration.ofSeconds(1)); // 1秒之前
        now5.minus(Duration.ofMillis(1)); // 1毫秒之前
        now5.minus(Duration.ofNanos(1)); // 1纳秒之前

        now5.with(TemporalAdjusters.firstDayOfMonth()); // 本月第一天
        now5.with(TemporalAdjusters.lastDayOfMonth()); // 本月最后一天
        now5.with(TemporalAdjusters.firstDayOfNextMonth()); // 下月第一天
        now5.with(TemporalAdjusters.firstDayOfNextYear()); // 下一年第一天
        now5.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); // 下一个星期一
        now5.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)); // 这个方法会返回指定日期之前的最近一个指定的星期几，如果指定日期就是指定的星期几，那么它会返回今天的日期。例如，如果今天是星期一，那么 previousOrSame(DayOfWeek.MONDAY) 会返回今天的日期。
        now5.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)); // 上一个星期一
        now5.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)); // 这个方法会返回指定日期之前的最近一个指定的星期几，如果指定日期就是指定的星期几，那么它会返回今天的日期。例如，如果今天是星期一，那么 previousOrSame(DayOfWeek.MONDAY) 会返回今天的日期。
        now5.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY)); // 本月最后一个星期一
        now5.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // 本月第一个星期一

        now5.withZoneSameInstant(ZoneId.of("Asia/Shanghai")); // 时区转换
        now5.withZoneSameLocal(ZoneId.of("Asia/Shanghai")); // 时区转换

        now5.getYear(); // 年份
        now5.getMonth(); // 月份
        now5.getMonthValue(); // 月份值
        now5.getDayOfMonth(); // 一个月中的第几天
    }

    /**
     * 格式化和解析
     * DateTimeFormatter 类提供了丰富的方法来格式化和解析日期、时间、日期时间。它是线程安全的，所以你可以将其定义为静态变量，然后在多个线程中共享。
     */
    public void example7(){
        //1.1预定义的格式器
        //DateTimeFormatter 类提供了一些预定义的格式器，可以直接使用。例如，DateTimeFormatter.ISO_DATE_TIME 是一个预定义的格式器，它可以将日期时间格式化为 ISO 8601 格式，也可以将 ISO 8601 格式的字符串解析为日期时间。
        //预定义的格式有：
        //ISO_LOCAL_DATE_TIME：格式化或解析日期时间，格式为：2021-01-01T00:00:00。
        //ISO_LOCAL_DATE：格式化或解析日期，格式为：2021-01-01。
        //ISO_LOCAL_TIME：格式化或解析时间，格式为：00:00:00。
        //ISO_DATE_TIME：格式化或解析日期时间，格式为：2021-01-01T00:00:00+08:00[Asia/Shanghai]。
        //ISO_DATE：格式化或解析日期，格式为：2021-01-01。
        //ISO_TIME：格式化或解析时间，格式为：00:00:00+08:00[Asia/Shanghai]。
        //ISO_OFFSET_DATE_TIME：格式化或解析日期时间，格式为：2021-01-01T00:00:00+08:00。
        //ISO_OFFSET_DATE：格式化或解析日期，格式为：2021-01-01+08:00。
        //ISO_OFFSET_TIME：格式化或解析时间，格式为：00:00:00+08:00。
        //ISO_ZONED_DATE_TIME：格式化或解析日期时间，格式为：2021-01-01T00:00:00+08:00[Asia/Shanghai]。
        //ISO_INSTANT：格式化或解析日期时间，格式为：2021-01-01T00:00:00Z。
        //BASIC_ISO_DATE：格式化或解析日期，格式为：20210101。
        //RFC_1123_DATE_TIME：格式化或解析日期时间，格式为：Fri, 1 Jan 2021 00:00:00 +0800。
        //ISO_WEEK_DATE：格式化或解析日期，格式为：2021-W53-5。
        //ISO_ORDINAL_DATE：格式化或解析日期，格式为：2021-001。


        //1.1.1 通过预定义的格式器将日期时间格式化为 ISO 8601 格式。
        LocalDateTime now = LocalDateTime.now();
        String isoDateTime = now.format(DateTimeFormatter.ISO_DATE_TIME); // 2021-01-01T00:00:00
        System.out.println(isoDateTime); // 2021-01-01T00:00:00

        //1.1.2 通过预定义的格式器将 ISO 8601 格式的字符串解析为日期时间。
        LocalDateTime now2 = LocalDateTime.parse("2021-01-01T00:00:00", DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(now2); // 2021-01-01T00:00:00

        //1.1.3 通过预定义的格式器将日期格式化为 ISO 8601 格式。
        LocalDate now3 = LocalDate.now();
        String isoDate = now3.format(DateTimeFormatter.ISO_DATE); // 2021-01-01
        System.out.println(isoDate); // 2021-01-01

        //1.1.4 通过预定义的格式器将 ISO 8601 格式的字符串解析为日期。
        LocalDate now4 = LocalDate.parse("2021-01-01", DateTimeFormatter.ISO_DATE);
        System.out.println(now4); // 2021-01-01

        //1.1.5 通过预定义的格式器将时间格式化为 ISO 8601 格式。
        LocalTime now5 = LocalTime.now();
        String isoTime = now5.format(DateTimeFormatter.ISO_TIME); // 00:00:00
        System.out.println(isoTime); // 00:00:00

        //1.1.6 通过预定义的格式器将 ISO 8601 格式的字符串解析为时间。
        LocalTime now6 = LocalTime.parse("00:00:00", DateTimeFormatter.ISO_TIME);
        System.out.println(now6); // 00:00:00

        //1.1.7 通过预定义的格式器将日期时间格式化为 ISO 8601 格式。
        ZonedDateTime now7 = ZonedDateTime.now();
        String isoDateTime2 = now7.format(DateTimeFormatter.ISO_DATE_TIME); // 2021-01-01T00:00:00+08:00[Asia/Shanghai]

        //local 相关的格式化风格

        //常用的日期/时间格式化的格式化符号：
        //1.2.1 通过Parse解析字符串中的日期
        LocalDate date = LocalDate.parse("2021-01-01");
        System.out.println(date); // 2021-01-01

        ZonedDateTime apollollaunch = ZonedDateTime.parse("1969-07-16 09:32:00-0400", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));
        System.out.println(apollollaunch); // 1969-07-16T09:32-04:00

        LocalDateTime localDateTime= LocalDateTime.parse("2021-01-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 与遗留代码的互操作
     * 作为全新的创造，java date 和 time api 必须能够与已有类之间进行相互操作，特别是无处不在的 java.util.Date 和 java.util.Calendar 、 java.sql.Date 和 java.sql.Time 以及 java.sql.Timestamp 。
     * 为了实现这一点，java date 和 time api 提供了一些方法来将其与遗留代码进行互操作。
     */
    public void example8(){
        // Instant       <-> java.util.Date                 Date.from(Instant)                       Date.toInstant()
        // Instant       <-> java.sql.Timestamp             Timestamp.from(Instant)                  Timestamp.toInstant()
        // ZonedDateTime <-> java.util.GregorianCalendar    GregorianCalendar.from(ZonedDateTime)    GregorianCalendar.toZonedDateTime()
        // ZonedDateTime <-> java.util.Calendar             Calendar.from(ZonedDateTime)             Calendar.toZonedDateTime()
        // LocalDateTime <-> java.sql.Timestamp             Timestamp.valueOf(LocalDateTime)         Timestamp.toLocalDateTime()
        // LocalDate     <-> java.sql.Date                  Date.valueOf(LocalDate)                  Date.toLocalDate()
        // DateTimeFormatter <-> java.text.DateFormat       DateFormat.toFormat(DateTimeFormatter)   DateTimeFormatter.toFormat(DateFormat)
        // java.util.TimeZone <-> ZoneId                    TimeZone.getTimeZone(ZoneId)             TimeZone.toZoneId()
        // java.nio.file.attribute.FileTime <-> Instant     FileTime.from(Instant)                   Instant.toFileTime()
    }
}
