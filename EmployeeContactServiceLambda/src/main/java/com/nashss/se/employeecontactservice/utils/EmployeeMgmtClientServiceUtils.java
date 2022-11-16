package com.nashss.se.employeecontactservice.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class EmployeeMgmtClientServiceUtils {
    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"'\\\\]");

    /**
     * Static utility method to validate a String.
     * @param stringToValidate the String to check
     * @return a boolean representing the validity of the string
     */
    public static boolean isValidString(String stringToValidate) {
        if (StringUtils.isBlank(stringToValidate)) {
            return false;
        } else {
            return !INVALID_CHARACTER_PATTERN.matcher(stringToValidate).find();
        }
    }

    /**
     * Static utility method to generate a random, unique employeeID.
     * @return a random 5 digit alphanumeric
     */
    public static String generateEmployeeId() {
        return RandomStringUtils.randomAlphanumeric(5);
    }
}
