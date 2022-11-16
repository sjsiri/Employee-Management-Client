package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.GetSingleEmployeeDetailsRequest;
import com.nashss.se.employeecontactservice.activity.results.GetSingleEmployeeDetailsResult;
import com.nashss.se.employeecontactservice.dynamodb.EmployeeDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetSingleEmployeeDetailsActivityTest {

    @Mock
    EmployeeDao employeeDao;

    GetSingleEmployeeDetailsActivity getSingleEmployeeDetailsActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        getSingleEmployeeDetailsActivity = new GetSingleEmployeeDetailsActivity(employeeDao);
    }

    @Test
    void handleRequest_employeeFound_employeeReturnedInResult() {
        // GIVEN
        String employeeId = "1";
        GetSingleEmployeeDetailsRequest request =
                GetSingleEmployeeDetailsRequest.builder()
                        .withEmployeeId(employeeId)
                        .build();

        Employee employee = new Employee();
        employee.setEmployeeId("1");
        employee.setFirstName("John");
        employee.setLastName("Doe");


        when(employeeDao.getEmployee(employeeId)).thenReturn(employee);

        // WHEN
        GetSingleEmployeeDetailsResult result = getSingleEmployeeDetailsActivity.handleRequest(request);

        // THEN
        verify(employeeDao).getEmployee(employeeId);
        assertNotNull(result.getSingleEmployee());
    }
}