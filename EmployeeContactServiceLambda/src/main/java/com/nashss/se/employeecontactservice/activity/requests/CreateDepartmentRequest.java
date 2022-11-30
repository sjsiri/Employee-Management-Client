package com.nashss.se.employeecontactservice.activity.requests;

import com.amazonaws.internal.config.Builder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateDepartmentRequest.Builder.class)
public class CreateDepartmentRequest {

    private final String deptId;

    private final String deptName;

    private final String deptStatus;

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
    public static Builder builder() {
        return new Builder();
    }
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
        public CreateDepartmentRequest build() {
            return new CreateDepartmentRequest(
                    deptId,
                    deptName,
                    deptStatus);
        }
    }
}
