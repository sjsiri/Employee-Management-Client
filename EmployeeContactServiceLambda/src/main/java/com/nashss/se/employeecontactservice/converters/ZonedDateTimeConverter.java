package com.nashss.se.employeecontactservice.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeConverter implements DynamoDBTypeConverter<String, ZonedDateTime> {
    @Override
    public String convert(ZonedDateTime object) {
        return object.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    public ZonedDateTime unconvert(String object) {
        return ZonedDateTime.parse(object);
    }
}