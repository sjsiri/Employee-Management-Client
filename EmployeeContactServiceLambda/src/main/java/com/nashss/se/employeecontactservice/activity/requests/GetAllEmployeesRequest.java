package com.nashss.se.employeecontactservice.activity.requests;


public class GetAllEmployeesRequest {

    private final String employeeId;

    private GetAllEmployeesRequest(String employeeId){
        this.employeeId = employeeId;
    }

    public String getEmployeeId() { return employeeId; }

    @Override
    public String toString() {
        return "GetAllEmployeesRequest{" +
                "employeeId='" + employeeId + '\'' +
                '}';
    }
    public static Builder builder() {return new Builder(); }

    public static class Builder {
        private String employeeId;

        public Builder withEmployeeId(String employeeId){
            this.employeeId = employeeId;
            return this;
        }
        public GetAllEmployeesRequest build() { return new GetAllEmployeesRequest(employeeId); }
    }
}