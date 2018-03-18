package edu.nju;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author Shenmiu
 * @date 2018/03/17
 * <p>
 * 测试LocalDate&LocalDateTime的各种特性
 */
class LocalDateTest {

    //时区
    @Test
    void zone() {
        ZoneId defaultZone = ZoneId.systemDefault();
        System.out.println(defaultZone);
    }

    //时间戳
    @Test
    void instant() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 10, 10, 10, 10);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

        System.out.println(instant.toEpochMilli());
    }

    //format
    @Test
    void formatLocalDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println(dateTimeFormatter.format(LocalDateTime.of(2018, 10, 10, 10, 10)));
    }

    //truncate
    @Test
    void localDateTimeTruncate() {
        LocalDateTime truncate2minute = LocalDateTime.of(2018, 10, 10, 10, 10);
        truncate2minute.truncatedTo(ChronoUnit.MINUTES);
        System.out.println(truncate2minute);
    }

}
