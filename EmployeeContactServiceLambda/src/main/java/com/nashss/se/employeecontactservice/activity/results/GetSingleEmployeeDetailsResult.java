package com.nashss.se.employeecontactservice.activity.results;

import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.models.EmployeeModel;

public class GetSingleEmployeeDetailsResult {

    private final EmployeeModel singleEmployee;

    /**
     * Instantiates a new GetSingleEmployeeDetails object.
     *
     * @param singleEmployee to access the employee.
     */

    public GetSingleEmployeeDetailsResult(Employee singleEmployee) {
        this.singleEmployee = new EmployeeModel(singleEmployee);
    }

    public EmployeeModel getSingleEmployee() {
        return singleEmployee;
    }

    @Override
    public String toString() {
        return "GetSingleEmployeeDetailsResult{" +
                "employee='" + singleEmployee + '\'' +
                '}';
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Employee singleEmployee;

        public Builder withSingleEmployee(Employee singleEmployee) {
            this.singleEmployee = singleEmployee;
            return this;
        }
        public GetSingleEmployeeDetailsResult build() {
            return new GetSingleEmployeeDetailsResult(singleEmployee);
        }
    }
}
