package com.nashss.se.employeecontactservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateDepartmentRequest.Builder.class)
public class UpdateDepartmentRequest {

    private String deptId;
    /**
     * Uses the pathDepartment as our path and makes sure its the same
     * DepartmentID that is trying to be changed.
     */
    private String pathDeptId;

    private final String deptName;

    private final String deptStatus;

    /**
     * Takes in all the fields to be updated.
     *
     * @param deptId takes in the department's Id.
     * @param deptName takes in the deptName.
     * @param deptStatus takes in the departmentStatus.
     */

    public UpdateDepartmentRequest(String deptId, String deptName, String deptStatus) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptStatus = deptStatus;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getPathDeptId() {
        return pathDeptId;
    }

    public void setPathDeptId(String pathDeptId) {
        this.pathDeptId = pathDeptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getDeptStatus() {
        return deptStatus;
    }

    @Override
    public String toString() {
        return "UpdateDepartmentRequest{" +
                "deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", deptStatus='" + deptStatus + '\'' +
                '}';
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder(); }

    @JsonPOJOBuilder
    public static class Builder {
        private String deptId;

        private String deptName;

        private String deptStatus;


        public Builder withDeptId(String deptId) {
            this.deptId = deptId;
            return this;
        }

        public Builder withDeptName(String deptName) {
            this.deptName = deptName;
            return this;
        }


        public Builder withDeptStatus(String deptStatus) {
            this.deptStatus = deptStatus;
            return this;
        }

        public UpdateDepartmentRequest build() {
            return new UpdateDepartmentRequest(deptId, deptName, deptStatus);
        }
    }
}


