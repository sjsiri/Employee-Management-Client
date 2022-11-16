package com.nashss.se.employeecontactservice.activity.results;

import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.models.EmployeeModel;

public class UpdateEmployeeResult {

    private final EmployeeModel employeeModel;

    /**
     * Takes in employeeModel.
     *
     * @param employeeModel to access the employeeModel.
     */
    public UpdateEmployeeResult(Employee employeeModel) {
        this.employeeModel = new EmployeeModel(employeeModel);
    }

    public EmployeeModel getEmployeeModel() {
        return employeeModel;
    }

    @Override
    public String toString() {
        return "UpdateEmployeeResult{" +
                "employeeModel=" + employeeModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Employee employeeModel;

        public Builder withEmployeeModel(Employee employeeModel) {
            this.employeeModel = employeeModel;
            return this;
        }

        public UpdateEmployeeResult build() {
            return new UpdateEmployeeResult(employeeModel);
        }
    }
}
