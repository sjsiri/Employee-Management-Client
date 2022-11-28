package com.nashss.se.employeecontactservice.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class EmployeeMgmtClientServiceUtils {
    private static final Pattern INVALID_CHARACTER_PATTERN = Pattern.compile("[\"'\\\\]");
    private static final Pattern EMAIL_CHARACTER_PATTERN =
            Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

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
     * Static utility method to validate an email based on the RFC 5322 standard.
     * @param email the Email to check
     * @return a boolean representing the validity of the string as an email
     */
    public static boolean isValidEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        } else {
            return EMAIL_CHARACTER_PATTERN.matcher(email).find();
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
