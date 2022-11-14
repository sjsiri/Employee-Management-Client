package com.nashss.se.employeecontactservice.activity.results;

import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.models.EmployeeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllEmployeesResult {

    private final List<EmployeeModel> employeeList;

    /**
     * Instantiates a new GetAllEmployees object.
     *
     * @param employeeList to access the employee table.
     */
    public GetAllEmployeesResult(List<Employee> employeeList) {
        this.employeeList = employeeList.stream().map(EmployeeModel::new).collect(Collectors.toList());
    }

    public List<EmployeeModel> getEmployeeList() {
        return new ArrayList<>(employeeList);
    }

    @Override
    public String toString() {
        return "GetAllEmployeesResult{" +
                "employeeList=" + employeeList +
                '}';
    }
        //CHECKSTYLE:OFF:Builder
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

