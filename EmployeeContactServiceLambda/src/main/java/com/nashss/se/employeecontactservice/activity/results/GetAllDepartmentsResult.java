package com.nashss.se.employeecontactservice.activity.results;

import com.nashss.se.employeecontactservice.dynamodb.models.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetAllDepartmentsResult {

    private final List<Department> deptList;

    /**
     * Instantiates a new GetAllDepartmentsResult object.
     *
     * @param deptList to access the department table.
     */
    public GetAllDepartmentsResult(List<Department> deptList) {
        this.deptList = new ArrayList<>(deptList);
    }

    public List<Department> getDeptList() {
        return new ArrayList<>(deptList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAllDepartmentsResult that = (GetAllDepartmentsResult) o;
        return Objects.equals(deptList, that.deptList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptList);
    }

    @Override
    public String toString() {
        return "GetAllDepartmentsResult{" +
                "departmentList=" + deptList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private List<Department> deptList;

        public Builder withDeptList(List<Department> deptList){
            this.deptList = new ArrayList<>(deptList);
            return this;
        }

        public GetAllDepartmentsResult build() { return new GetAllDepartmentsResult(deptList); }
    }
}

