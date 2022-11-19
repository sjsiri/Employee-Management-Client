package com.nashss.se.employeecontactservice.activity.requests;

public class GetAllDepartmentsRequest {

    private final Integer deptId;
    private final String deptName;
    private final String deptStatus;

    private GetAllDepartmentsRequest(Integer deptId, String deptName, String deptStatus) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptStatus = deptStatus;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getDeptStatus() {
        return deptStatus;
    }

    @Override
    public String toString() {
        return "GetAllDepartmentsRequest{" +
                "deptId='" + deptId + '\'' +
                '}';
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer deptId;
        private String deptName;
        private String deptStatus;

        public Builder withDeptId(Integer deptId) {
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


        public GetAllDepartmentsRequest build() {
            return new GetAllDepartmentsRequest(deptId, deptName, deptStatus);
        }
    }
}
