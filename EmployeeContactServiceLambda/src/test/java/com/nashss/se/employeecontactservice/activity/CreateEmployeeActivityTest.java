package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.CreateEmployeeRequest;
import com.nashss.se.employeecontactservice.activity.results.CreateEmployeeResult;
import com.nashss.se.employeecontactservice.dynamodb.EmployeeDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.employeecontactservice.models.EmployeeModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateEmployeeActivityTest {

    @Mock
    EmployeeDao employeeDao;

    CreateEmployeeActivity createEmployeeActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        createEmployeeActivity = new CreateEmployeeActivity(employeeDao);
    }

    @Test
    void handleRequest_validAttributes_callsCreateEmployee() {
        // GIVEN
        CreateEmployeeRequest request = CreateEmployeeRequest.builder().withFirstName("Josh").build();

        // WHEN
        createEmployeeActivity.handleRequest(request);

        // THEN
        verify(employeeDao).saveEmployee(any());
    }

    @Test
    void handleRequest_validAttributes_returnsResult() {
        // GIVEN
        CreateEmployeeRequest request = CreateEmployeeRequest.builder().withFirstName("Josh").build();

        // WHEN
        CreateEmployeeResult result = createEmployeeActivity.handleRequest(request);

        // THEN
        Employee employee = new Employee();
        employee.setFirstName("Josh");
        employee.setEmployeeId(result.getEmployeeModel().getEmployeeId());
        assertEquals(result.getEmployeeModel(), new EmployeeModel(employee));
    }

    @Test
    void handleRequest_invalidFirstName_throwsException() {
        // GIVEN
        CreateEmployeeRequest request = CreateEmployeeRequest.
                builder().
                withFirstName("\'apostrophe").
                build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createEmployeeActivity.handleRequest(request));
    }

    @Test
    void handleRequest_invalidLastName_throwsException() {
        // GIVEN
        CreateEmployeeRequest request = CreateEmployeeRequest.
                builder().
                withLastName("\'apostrophe").
                build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createEmployeeActivity.handleRequest(request));
    }

    @Test
    void handleRequest_invalidDeptName_throwsException() {
        // GIVEN
        CreateEmployeeRequest request = CreateEmployeeRequest.
                builder().
                withDeptName("\'apostrophe").
                build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createEmployeeActivity.handleRequest(request));
    }

    @Test
    void handleRequest_invalidJobTitle_throwsException() {
        // GIVEN
        CreateEmployeeRequest request = CreateEmployeeRequest.
                builder().
                withJobTitle("\'apostrophe").
                build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createEmployeeActivity.handleRequest(request));
    }
}