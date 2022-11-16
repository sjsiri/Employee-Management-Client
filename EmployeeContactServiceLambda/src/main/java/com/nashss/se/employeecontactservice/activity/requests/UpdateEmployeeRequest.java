package com.nashss.se.employeecontactservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateEmployeeRequest.Builder.class)
public class UpdateEmployeeRequest {

    private String employeeId;

    private final String firstName;

    private final String lastName;

    private final String jobTitle;

    private final String email;

    private final String deptId;

    private final String deptName;

    private final String hireDate;

    private final String phoneNumber;

    private final String dateOfBirth;

    private final String employeeStatus;

    /**
     * Takes in all the fields to be updated.
     *
     * @param employeeId takes in the employee's Id.
     * @param firstName takes in the firstName.
     * @param lastName takes in the lastName.
     * @param jobTitle takes in the jobTitle.
     * @param email takes in the email.
     * @param deptId takes in the deptId.
     * @param deptName takes in the deptName.
     * @param hireDate takes in the hireDate.
     * @param phoneNumber takes in the phoneNumber.
     * @param dateOfBirth takes in the dateOfBirth.
     * @param employeeStatus takes in the employeeStatus.
     */
    public UpdateEmployeeRequest(String employeeId, String firstName, String lastName,
                                 String jobTitle, String email, String deptId, String deptName,
                                 String hireDate, String phoneNumber, String dateOfBirth, String employeeStatus) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.email = email;
        this.deptId = deptId;
        this.deptName = deptName;
        this.hireDate = hireDate;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.employeeStatus = employeeStatus;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

    @Override
    public String toString() {
        return "UpdateEmployeeRequest{" +
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
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {

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

        public Builder withEmployeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withDeptId(String deptId) {
            this.deptId = deptId;
            return this;
        }

        public Builder withDeptName(String deptName) {
            this.deptName = deptName;
            return this;
        }

        public Builder withHireDate(String hireDate) {
            this.hireDate = hireDate;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder withEmployeeStatus(String employeeStatus) {
            this.employeeStatus = employeeStatus;
            return this;
        }

        public UpdateEmployeeRequest build() {
            return new UpdateEmployeeRequest(employeeId, firstName, lastName, jobTitle, email, deptId, deptName, hireDate, phoneNumber, dateOfBirth, employeeStatus);
        }
    }
}

