package edu.nju.BindingTest.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Shenmiu
 * @date 2018/03/08
 * <p>
 * String到LocalDate的Converter
 */
public class LocalDateConverter implements Converter<String, LocalDate> {

    private final DateTimeFormatter formatter;

    public LocalDateConverter(String dateFormat) {
        this.formatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    @Override
    public LocalDate convert(String source) {
        if (source.isEmpty()) {
            return null;
        }

        return LocalDate.parse(source, formatter);
    }
}
