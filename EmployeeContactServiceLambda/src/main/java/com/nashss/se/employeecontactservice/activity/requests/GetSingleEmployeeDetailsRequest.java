package com.nashss.se.employeecontactservice.activity.requests;

public class GetSingleEmployeeDetailsRequest {

    private final String employeeId;

    private GetSingleEmployeeDetailsRequest(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return "GetSingleEmployeeDetailsRequest{" +
                "employeeId='" + employeeId + '\'' +
                ']';
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String employeeId;

        public Builder withEmployeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public GetSingleEmployeeDetailsRequest build() {
            return new GetSingleEmployeeDetailsRequest(employeeId);
        }
    }
}
