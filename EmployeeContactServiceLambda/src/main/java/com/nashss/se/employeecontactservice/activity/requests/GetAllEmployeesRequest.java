package com.nashss.se.employeecontactservice.activity.requests;

public class GetAllEmployeesRequest {

    private final String employeeId;

    private Boolean pageMovement;

    private GetAllEmployeesRequest(String employeeId, Boolean pageMovement) {
        this.employeeId = employeeId;
        this.pageMovement = pageMovement;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Boolean getPageMovement() {
        return pageMovement;
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

        private Boolean pageMovement;

        public Builder withEmployeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder withPageMovement(Boolean pageMovement) {
            this.pageMovement = pageMovement;
            return this;
        }
        public GetAllEmployeesRequest build() {
            return new GetAllEmployeesRequest(employeeId, pageMovement);
        }
    }
}
