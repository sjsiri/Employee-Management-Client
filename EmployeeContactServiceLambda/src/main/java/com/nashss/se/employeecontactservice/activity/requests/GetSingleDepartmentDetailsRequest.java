package com.nashss.se.employeecontactservice.activity.requests;

public class GetSingleDepartmentDetailsRequest {

    private final String deptId;

    /**
     *
     *
     * @param deptId linked to the deptId for department table.
     */
    public GetSingleDepartmentDetailsRequest(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptId() {
        return deptId;
    }

    @Override
    public String toString() {
        return "GetSingleDepartmentDetailsRequest{" +
                "deptId='" + deptId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public String deptId;

        public Builder withDeptId(String deptId) {
            this.deptId = deptId;
            return this;
        }

        public GetSingleDepartmentDetailsRequest build() {
            return new GetSingleDepartmentDetailsRequest(deptId);
        }
    }
}
