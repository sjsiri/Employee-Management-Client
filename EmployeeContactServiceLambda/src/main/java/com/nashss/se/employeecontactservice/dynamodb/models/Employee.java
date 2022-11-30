package com.nashss.se.employeecontactservice.dynamodb.models;

import com.nashss.se.employeecontactservice.converters.LocalDateConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.time.LocalDate;

import java.util.Objects;
@DynamoDBTable(tableName = "Employees")
public class Employee {

    public static final String LASTNAME_STATUS = "LastNameIdStatusIndex";
    public static final String EMPLOYEE_STATUS = "EmployeeStatusIndex";
    public static final String DEPARTMENT_GSI = "DepartmentLastNameIdIndex";

    private String employeeId;

    private String firstName;

    private String lastName;

    private String jobTitle;

    private String email;

    private String deptId;

    private String deptName;

    private LocalDate hireDate;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private String employeeStatus;

    private String lastNameEmployeeId;

    @DynamoDBHashKey(attributeName = "employeeId")
    @DynamoDBIndexRangeKey(globalSecondaryIndexName  = EMPLOYEE_STATUS)
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @DynamoDBAttribute(attributeName = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DynamoDBAttribute(attributeName = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DynamoDBAttribute(attributeName = "jobTitle")
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @DynamoDBAttribute(attributeName = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = DEPARTMENT_GSI)
    @DynamoDBAttribute(attributeName = "deptId")
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @DynamoDBAttribute(attributeName = "deptName")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @DynamoDBAttribute(attributeName = "hireDate")
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @DynamoDBAttribute(attributeName = "phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @DynamoDBAttribute(attributeName = "dateOfBirth")
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @DynamoDBAttribute(attributeName = "employeeStatus")
    @DynamoDBIndexHashKey(globalSecondaryIndexNames = {EMPLOYEE_STATUS, LASTNAME_STATUS})
    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    @DynamoDBAttribute(attributeName = "lastNameEmployeeId")
    @DynamoDBIndexRangeKey(globalSecondaryIndexNames = {LASTNAME_STATUS, DEPARTMENT_GSI})
    public String getLastNameEmployeeId() {
        return lastNameEmployeeId;
    }

    public void setLastNameEmployeeId(String lastNameEmployeeId) {
        this.lastNameEmployeeId = lastNameEmployeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(jobTitle, employee.jobTitle) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(deptId, employee.deptId) &&
                Objects.equals(deptName, employee.deptName) &&
                Objects.equals(hireDate, employee.hireDate) &&
                Objects.equals(phoneNumber, employee.phoneNumber) &&
                Objects.equals(dateOfBirth, employee.dateOfBirth) &&
                Objects.equals(employeeStatus, employee.employeeStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                employeeId,
                firstName,
                lastName,
                jobTitle,
                email,
                deptId,
                deptName,
                hireDate,
                phoneNumber,
                dateOfBirth,
                employeeStatus);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", email='" + email + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", hireDate=" + hireDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", employeeStatus='" + employeeStatus + '\'' +
                ", lastNameEmployeeId='" + lastNameEmployeeId + '\'' +
                '}';
    }
}

