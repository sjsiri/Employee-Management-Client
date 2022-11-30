package com.nashss.se.employeecontactservice.activity.requests;

public class GetAllEmployeesRequest {

    private final String lastNameEmployeeId;
    private final Boolean forwardBoolean;
    private final String deptId;


    private GetAllEmployeesRequest(String lastNameEmployeeId,
                                   Boolean forwardBoolean, String deptId) {
        this.lastNameEmployeeId = lastNameEmployeeId;
        this.forwardBoolean = forwardBoolean;
        this.deptId = deptId;
    }

    public String getLastNameEmployeeId() {
        return lastNameEmployeeId;
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
                ", lastNameEmployeeId='" + lastNameEmployeeId + '\'' +
                ", forwardBoolean=" + forwardBoolean +
                ", deptId='" + deptId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String lastNameEmployeeId;
        private Boolean forwardBoolean;
        private String deptId;

        public Builder withLastNameEmployeeId(String lastNameEmployeeId) {
            this.lastNameEmployeeId = lastNameEmployeeId;
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
            return new GetAllEmployeesRequest(lastNameEmployeeId, forwardBoolean, deptId);
        }
    }
}
