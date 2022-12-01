package com.nashss.se.employeecontactservice.activity.results;

import com.nashss.se.employeecontactservice.dynamodb.models.Department;

public class GetSingleDepartmentDetailsResult {

    private final Department singleDepartment;

    /**
     *
     *
     * @param singleDepartment Department to access the department table.
     */
    public GetSingleDepartmentDetailsResult(Department singleDepartment) {
        this.singleDepartment = singleDepartment;
    }

    public Department getSingleDepartment() {
        return singleDepartment;
    }

    @Override
    public String toString() {
        return "GetSingleDepartmentDetailsResult{" +
                "singleDepartment=" + singleDepartment +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Department singleDepartment;

        public Builder withSingleDepartment(Department singleDepartment) {
            this.singleDepartment = singleDepartment;
            return this;
        }
        public GetSingleDepartmentDetailsResult build() {
            return new GetSingleDepartmentDetailsResult(singleDepartment);
        }
    }
}
