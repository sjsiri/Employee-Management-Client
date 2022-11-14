package com.nashss.se.employeecontactservice.converters;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateConverterTest {

    LocalDateConverter converter = new LocalDateConverter();

    @Test
    void convert() {
        // GIVEN
        LocalDate time = LocalDate.of(2022, 11, 14);

        // WHEN
        String convertedTime = converter.convert(time);

        // THEN
        assertEquals("2022-11-14", convertedTime);
    }

    @Test
    void unconvert() {
        // GIVEN
        String time = "2022-11-14";

        // WHEN
        LocalDate unconvertedTime = converter.unconvert(time);

        // THEN
        assertEquals(LocalDate.of(2022, 11, 14), unconvertedTime);
    }
}