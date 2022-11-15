package com.nashss.se.employeecontactservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class CreateEmployeeRequest {

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

    private CreateEmployeeRequest(String firstName,
                                 String lastName,
                                 String jobTitle,
                                 String email,
                                 String deptId,
                                 String deptName,
                                 String hireDate,
                                 String phoneNumber,
                                 String dateOfBirth,
                                 String employeeStatus) {
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

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
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

        public CreateEmployeeRequest build() {
            return new CreateEmployeeRequest(firstName,
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
    }
}
