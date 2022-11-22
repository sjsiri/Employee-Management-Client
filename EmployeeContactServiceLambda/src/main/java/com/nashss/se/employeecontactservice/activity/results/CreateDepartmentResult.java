package com.nashss.se.employeecontactservice.activity.results;

import com.nashss.se.employeecontactservice.dynamodb.models.Department;

public class CreateDepartmentResult {
    private final Department department;
    /**
     * Constructs results.
     *
     * @param department the department to be created.
     */
    public CreateDepartmentResult(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "CreateDepartmentResult{" +
                "departmentModel=" + department +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Department dept = new Department();

        public Builder withDepartment(Department dept) {
            this.dept = dept;
            return this;
        }

        public CreateDepartmentResult build() {
            return new CreateDepartmentResult(dept);
        }
    }
}
