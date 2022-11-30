package com.nashss.se.employeecontactservice.activity.requests;

public class GetAllEmployeesRequest {

    private final String employeeId;

    private Boolean forwardBoolean;
    private String deptId;

    private GetAllEmployeesRequest(String employeeId, Boolean forwardBoolean, String deptId) {
        this.employeeId = employeeId;
        this.forwardBoolean = forwardBoolean;
        this.deptId = deptId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Boolean getForwardBoolean() {
        return forwardBoolean;
    }

    public String getDeptId() {
        return deptId;
    }

    @Override
    public String toString() {
        return "GetAllEmployeesRequest{" +
                "employeeId='" + employeeId + '\'' +
                '}';
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String employeeId;
        private Boolean forwardBoolean;
        private String deptId;

        public Builder withEmployeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder withForwardBoolean(Boolean forwardBoolean) {
            this.forwardBoolean = forwardBoolean;
            return this;
        }

        public Builder withDeptId(String deptId) {
            this.deptId = deptId;
            return this;
        }

        public GetAllEmployeesRequest build() {
            return new GetAllEmployeesRequest(employeeId, forwardBoolean, deptId);
        }
    }
}
