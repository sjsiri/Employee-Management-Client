package com.nashss.se.employeecontactservice.exceptions;

public class DepartmentIdTakenException extends InvalidAttributeValueException {
    /**
     * Exception with no message or cause.
     */
    public DepartmentIdTakenException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public DepartmentIdTakenException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public DepartmentIdTakenException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public DepartmentIdTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
