package com.nashss.se.employeecontactservice.activity.results;

import com.nashss.se.employeecontactservice.dynamodb.models.Department;
import com.nashss.se.employeecontactservice.models.DepartmentModel;

public class CreateDepartmentResult {
    private final DepartmentModel departmentModel;
    /**
     * Constructs results.
     *
     * @param department the department.
     */
    public CreateDepartmentResult(Department department) {
        this.departmentModel = new DepartmentModel(department);
    }

    public DepartmentModel getDepartmentModel() {
        return departmentModel;
    }

    @Override
    public String toString() {
        return "CreateDepartmentResult{" +
                "departmentModel=" + departmentModel +
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
