package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.GetAllEmployeesRequest;
import com.nashss.se.employeecontactservice.activity.results.GetAllEmployeesResult;
import com.nashss.se.employeecontactservice.dynamodb.EmployeeDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.models.EmployeeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetAllEmployeesActivityTest {

    @Mock
    EmployeeDao employeeDao;

    GetAllEmployeesActivity getAllEmployeesActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        getAllEmployeesActivity = new GetAllEmployeesActivity(employeeDao);
    }

    @Test
    void handleRequest_employeesFound_employeeListReturnedInResult() {
        // GIVEN
        String lastNameEmployeeId = "0";
        Boolean forward = true;
        String deptId = "001";
        GetAllEmployeesRequest request =
                GetAllEmployeesRequest.builder()
                .withLastNameEmployeeId(lastNameEmployeeId)
                .withForwardBoolean(forward)
                .withDeptId(deptId)
                .build();

        Employee employee = new Employee();
        employee.setEmployeeId("1");
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setDeptId("001");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        when(employeeDao.getAllActiveEmployeesWithLimit("0", true, deptId)).thenReturn(employeeList);

        // WHEN
        GetAllEmployeesResult result = getAllEmployeesActivity.handleRequest(request);

        // THEN
        verify(employeeDao).getAllActiveEmployeesWithLimit("0", true, deptId);
        assertNotNull(result.getEmployeeList());
        assertEquals(List.of(new EmployeeModel(employee)), result.getEmployeeList());
    }
}