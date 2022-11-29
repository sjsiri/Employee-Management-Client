package com.nashss.se.employeecontactservice.activity.requests;

public class GetAllEmployeesRequest {

    private final String lastNameEmployeeId;

    private Boolean forwardBoolean;

    private GetAllEmployeesRequest(String lastNameEmployeeId, Boolean forwardBoolean) {
        this.lastNameEmployeeId = lastNameEmployeeId;
        this.forwardBoolean = forwardBoolean;
    }

    public String getLastNameEmployeeId() {
        return lastNameEmployeeId;
    }

    public Boolean getForwardBoolean() {
        return forwardBoolean;
    }

    @Override
    public String toString() {
        return "GetAllEmployeesRequest{" +
                "lastNameEmployeeId='" + lastNameEmployeeId + '\'' +
                '}';
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String lastNameEmployeeId;

        private Boolean forwardBoolean;

        public Builder withLastNameEmployeeId(String lastNameEmployeeId) {
            this.lastNameEmployeeId = lastNameEmployeeId;
            return this;
        }

        public Builder withforwardBoolean(Boolean forwardBoolean) {
            this.forwardBoolean = forwardBoolean;
            return this;
        }
        public GetAllEmployeesRequest build() {
            return new GetAllEmployeesRequest(lastNameEmployeeId, forwardBoolean);
        }
    }
}
