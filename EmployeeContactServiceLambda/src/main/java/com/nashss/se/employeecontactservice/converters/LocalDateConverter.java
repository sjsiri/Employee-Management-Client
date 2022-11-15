package com.nashss.se.employeecontactservice.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements DynamoDBTypeConverter<String, LocalDate> {
    @Override
    public String convert(LocalDate object) {
        return (object == null) ? null : object.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public LocalDate unconvert(String object) {
        return (object == null) ? null : LocalDate.parse(object, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
