package com.nashss.se.employeecontactservice.models;

import com.nashss.se.employeecontactservice.converters.LocalDateConverter;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;

import java.util.Objects;

public class EmployeeModel {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String email;
    private String deptId;
    private String deptName;
    private String hireDate;
    private String phoneNumber;
    private String dateOfBirth;
    private String employeeStatus;
    private String lastNameEmployeeId;

    private LocalDateConverter converter;

    /**
     * Creates an employee model that can easily be converted to JSON.
     *
     * @param employee the employee to be converted.
     */
    public EmployeeModel(Employee employee) {
        converter = new LocalDateConverter();
        this.employeeId = employee.getEmployeeId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.jobTitle = employee.getJobTitle();
        this.email = employee.getEmail();
        this.deptId = employee.getDeptId();
        this.deptName = employee.getDeptName();
        this.hireDate = converter.convert(employee.getHireDate());
        this.phoneNumber = employee.getPhoneNumber();
        this.dateOfBirth = converter.convert(employee.getDateOfBirth());
        this.employeeStatus = employee.getEmployeeStatus();
        this.lastNameEmployeeId = employee.getLastNameEmployeeId();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public String getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getHireDate() {
        return hireDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public String getLastNameEmployeeId() {
        return lastNameEmployeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmployeeModel that = (EmployeeModel) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(jobTitle, that.jobTitle) &&
                Objects.equals(email, that.email) &&
                Objects.equals(deptId, that.deptId) &&
                Objects.equals(deptName, that.deptName) &&
                Objects.equals(hireDate, that.hireDate) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(employeeStatus, that.employeeStatus) &&
                Objects.equals(lastNameEmployeeId, that.lastNameEmployeeId);
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
                employeeStatus,
                lastNameEmployeeId);
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", email='" + email + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", hireDate='" + hireDate + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", employeeStatus='" + employeeStatus + '\'' +
                ", lastNameEmployeeId='" + lastNameEmployeeId + '\'' +
                '}';
    }
}
