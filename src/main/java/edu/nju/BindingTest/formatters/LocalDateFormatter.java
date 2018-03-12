package edu.nju.BindingTest.formatters;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Shenmiu
 * @date 2018/03/08
 */
public class LocalDateFormatter implements Formatter<LocalDate> {

    private final DateTimeFormatter formatter;

    public LocalDateFormatter(String pattern) {
        formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, formatter.withLocale(locale));
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return formatter.withLocale(locale).format(object);
    }
}
