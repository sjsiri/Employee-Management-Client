package com.nashss.se.employeecontactservice.metrics;

/**
 * Constant values for use with metrics.
 */
public class MetricsConstants {
    public static final String GETEMPLOYEE_EMPLOYEENOTFOUND_COUNT = "GetEmployee.EmployeeNotFoundException.Count";
    public static final String UPDATEEMPLOYEE_INVALIDATTRIBUTEVALUE_COUNT =
        "UpdateEmployee.InvalidAttributeValueException.Count";
    public static final String UPDATEEMPLOYEE_INVALIDATTRIBUTECHANGE_COUNT =
        "UpdateEmployee.InvalidAttributeChangeException.Count";
    public static final String UPDATEDEPARTMENT_INVALIDATTRIBUTEVALUE_COUNT =
            "UpdateDepartment.InvalidAttributeValueException.Count";
    public static final String SERVICE = "Service";
    public static final String SERVICE_NAME = "EmployeeManagementClient";
    public static final String NAMESPACE_NAME = "U5/EmployeeManagementClient";
}
