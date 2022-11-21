package com.nashss.se.employeecontactservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;
@DynamoDBTable(tableName = "Departments")
public class Department {

    public static final String DEPARTMENT_STATUS = "DepartmentStatusIndex";

    private String deptId;

    private String deptName;

    private String deptStatus;
    @DynamoDBHashKey(attributeName = "deptId")
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
    @DynamoDBHashKey(attributeName = "deptName")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    @DynamoDBHashKey(attributeName = "deptStatus")
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
        Department that = (Department) o;
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
        return "Department{" +
                "deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", deptStatus='" + deptStatus + '\'' +
                '}';
    }
}
