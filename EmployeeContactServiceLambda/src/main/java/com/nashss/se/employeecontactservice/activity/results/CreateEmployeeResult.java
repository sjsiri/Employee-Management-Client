package com.nashss.se.employeecontactservice.activity.results;

import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.models.EmployeeModel;

public class CreateEmployeeResult {
    private final EmployeeModel employeeModel;

    private CreateEmployeeResult(Employee employee) {
        this.employeeModel = new EmployeeModel(employee);
    }

    public EmployeeModel getEmployeeModel() {
        return employeeModel;
    }

    @Override
    public String toString() {
        return "CreateEmployeeResult{" +
                "employee=" + employeeModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Employee employee = new Employee();

        public Builder withEmployee(Employee employee) {
            this.employee = employee;
            return this;
        }

        public CreateEmployeeResult build() {
            return new CreateEmployeeResult(employee);
        }
    }
}
