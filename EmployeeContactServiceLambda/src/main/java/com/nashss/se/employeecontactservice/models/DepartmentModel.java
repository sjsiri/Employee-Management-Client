package com.nashss.se.employeecontactservice.models;

import com.nashss.se.employeecontactservice.dynamodb.models.Department;

import java.util.Objects;

public class DepartmentModel {

    private String deptId;

    private String deptName;

    private String deptStatus;

    /**
     * Creates a department model that can easily be converted to JSON.
     *
     * @param department the employee to be converted.
     */
    public DepartmentModel(Department department) {

        this.deptId = department.getDeptId();
        this.deptName = department.getDeptName();
        this.deptStatus = department.getDeptStatus();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptStatus() {
        return deptStatus;
    }

    public void setDeptStatus(String deptStatus) {
        this.deptStatus = deptStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DepartmentModel that = (DepartmentModel) o;
        return Objects.equals(deptId, that.deptId) &&
                Objects.equals(deptName, that.deptName) &&
                Objects.equals(deptStatus, that.deptStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptId, deptName, deptStatus);
    }

    @Override
    public String toString() {
        return "DepartmentModel{" +
                "deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", deptStatus='" + deptStatus + '\'' +
                '}';
    }
}
