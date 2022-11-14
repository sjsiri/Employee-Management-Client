package com.nashss.se.employeecontactservice.activity.requests;

public class GetAllEmployeesRequest {

    private final String employeeId;

    private Boolean forwardBoolean;

    private GetAllEmployeesRequest(String employeeId, Boolean forwardBoolean) {
        this.employeeId = employeeId;
        this.forwardBoolean = forwardBoolean;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Boolean getForwardBoolean() {
        return forwardBoolean;
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

        public Builder withEmployeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder withforwardBoolean(Boolean forwardBoolean) {
            this.forwardBoolean = forwardBoolean;
            return this;
        }
        public GetAllEmployeesRequest build() {
            return new GetAllEmployeesRequest(employeeId, forwardBoolean);
        }
    }
}
