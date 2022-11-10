package com.nashss.se.employeecontactservice.activity.results;


import com.nashss.se.employeecontactservice.dynamodb.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class GetAllEmployeesResult {


        private final List<Employee> employeeList;

        public GetAllEmployeesResult(List<Employee> employeeList) {
            this.employeeList = employeeList;
        }

        public List<Employee> getEmployeeList() {
            return new ArrayList<>(employeeList);
        }

        @Override
        public String toString() {
            return "GetAllEmployeesResult{" +
                    "employeeList=" + employeeList +
                    '}';
        }

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private List<Employee> employeeList;

            public Builder withEmployeeList(List<Employee> employeeList){
                this.employeeList = new ArrayList<>(employeeList);
                return this;
            }

            public GetAllEmployeesResult build() { return new GetAllEmployeesResult(employeeList); }
        }
}

