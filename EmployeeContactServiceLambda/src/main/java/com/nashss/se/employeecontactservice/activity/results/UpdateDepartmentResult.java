package com.nashss.se.employeecontactservice.activity.results;

import com.nashss.se.employeecontactservice.dynamodb.models.Department;

public class UpdateDepartmentResult {
    private final Department department;
    /**
     * Constructs results.
     *
     * @param department the department to be updated.
     */
    public UpdateDepartmentResult(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
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

        public UpdateDepartmentResult.Builder withDepartment(Department dept) {
            this.dept = dept;
            return this;
        }

        public UpdateDepartmentResult build() {
            return new UpdateDepartmentResult(dept);
        }
    }
}

