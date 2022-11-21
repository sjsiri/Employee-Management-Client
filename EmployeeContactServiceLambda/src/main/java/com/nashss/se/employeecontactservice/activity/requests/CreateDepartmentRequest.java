package com.nashss.se.employeecontactservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateDepartmentRequest.Builder.class)
public class CreateDepartmentRequest {

    private String deptId;

    private String deptName;

    private String deptStatus;

    private CreateDepartmentRequest(String deptId,
                                  String deptName,
                                  String deptStatus) {

        this.deptId = deptId;
        this.deptName = deptName;
        this.deptStatus = deptStatus;
    }

    public String getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getDeptStatus() {
        return deptStatus;
    }

    //CHECKSTYLE:OFF:Builder
    public static CreateDepartmentRequest.Builder builder() {
        return new CreateDepartmentRequest.Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {
        private String deptId;

        private String deptName;

        private String deptStatus;

        public CreateDepartmentRequest.Builder withDeptId(String deptId) {
            this.deptId = deptId;
            return this;
        }

        public CreateDepartmentRequest.Builder withDeptName(String deptName) {
            this.deptName = deptName;
            return this;
        }
        public CreateDepartmentRequest.Builder withDeptStatus(String deptStatus) {
            this.deptStatus = deptStatus;
            return this;
        }
        public CreateDepartmentRequest build() {
            return new CreateDepartmentRequest(
                    deptId,
                    deptName,
                    deptStatus);
        }
    }
}
