package main.java.com.nashss.se.employeecontactservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import main.java.com.nashss.se.employeecontactservice.converters.ZonedDateTimeConverter;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Employee {

        private String employeeId;

        private String firstName;

        private String lastName;

        private String jobTitle;

        private String email;

        private String deptId;

        private ZonedDateTime hireDate;

        private String phoneNumber;

        private ZonedDateTime dateOfBirth;

        @DynamoDBHashKey(attributeName = "employeeId")
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
        @DynamoDBAttribute(attributeName = "deptId")
        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }
        @DynamoDBAttribute(attributeName = "hireDate")
        @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
        public ZonedDateTime getHireDate() {
            return hireDate;
        }

        public void setHireDate(ZonedDateTime hireDate) {
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
        @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
        public ZonedDateTime getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(ZonedDateTime dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Employee)) return false;
            Employee employee = (Employee) o;
            return Objects.equals(employeeId, employee.employeeId) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(jobTitle, employee.jobTitle) && Objects.equals(email, employee.email) && Objects.equals(deptId, employee.deptId) && Objects.equals(hireDate, employee.hireDate) && Objects.equals(phoneNumber, employee.phoneNumber) && Objects.equals(dateOfBirth, employee.dateOfBirth);
        }

        @Override
        public int hashCode() {
            return Objects.hash(employeeId, firstName, lastName, jobTitle, email, deptId, hireDate, phoneNumber, dateOfBirth);
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
                    ", hireDate='" + hireDate + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", dateOfBirth='" + dateOfBirth + '\'' +
                    '}';
        }
}

