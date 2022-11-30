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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateEmployeeActivityTest {

    @Mock
    EmployeeDao employeeDao;

    CreateEmployeeActivity createEmployeeActivity;
    CreateEmployeeRequest validRequest;

    @BeforeEach
    void setup() {
        openMocks(this);
        createEmployeeActivity = new CreateEmployeeActivity(employeeDao);
        validRequest = CreateEmployeeRequest.builder()
                .withFirstName("Josh")
                .withLastName("Taylor")
                .withEmail("j@mail.com")
                .withDateOfBirth("2000-01-01")
                .build();
    }

    @Test
    void handleRequest_validAttributes_callsCreateEmployee() {

        // WHEN
        createEmployeeActivity.handleRequest(validRequest);

        // THEN
        verify(employeeDao).saveEmployee(any());
    }

    @Test
    void handleRequest_validAttributes_returnsResult() {

        // WHEN
        CreateEmployeeResult result = createEmployeeActivity.handleRequest(validRequest);
        Employee employee = new Employee();
        employee.setFirstName("Josh");
        employee.setLastName("Taylor");
        employee.setDateOfBirth(LocalDate.of(2000, 01, 01));
        employee.setEmail("j@mail.com");
        employee.setEmployeeId(result.getEmployeeModel().getEmployeeId());
        employee.setLastNameEmployeeId("Taylor_" + employee.getEmployeeId());

        // THEN
        assertEquals(new EmployeeModel(employee), result.getEmployeeModel());
    }

    @Test
    void handleRequest_invalidFirstName_throwsException() {
        // GIVEN
        CreateEmployeeRequest request = CreateEmployeeRequest
                .builder()
                .withFirstName("$cashMoney")
                .withLastName("good")
                .withDateOfBirth("2022-01-01")
                .withEmail("cash@money.com")
                .build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createEmployeeActivity.handleRequest(request));
    }

    @Test
    void handleRequest_invalidLastName_throwsException() {
        // GIVEN
        CreateEmployeeRequest request = CreateEmployeeRequest
                .builder()
                .withFirstName("cashMoney")
                .withLastName("good$$")
                .withDateOfBirth("2022-01-01")
                .withEmail("cash@money.com")
                .build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createEmployeeActivity.handleRequest(request));
    }

    @Test
    void handleRequest_invalidDeptName_throwsException() {
        // GIVEN
        CreateEmployeeRequest request = CreateEmployeeRequest
                .builder()
                .withFirstName("cashMoney")
                .withLastName("good")
                .withDateOfBirth("2022-01-01")
                .withEmail("cash@money.com")
                .withDeptName("100")
                .build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createEmployeeActivity.handleRequest(request));
    }

    @Test
    void handleRequest_invalidJobTitle_throwsException() {
        // GIVEN
        CreateEmployeeRequest request = CreateEmployeeRequest
                .builder()
                .withFirstName("cashMoney")
                .withLastName("good")
                .withDateOfBirth("2022-01-01")
                .withEmail("cash@money.com")
                .withJobTitle("c@shM0ney")
                .build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createEmployeeActivity.handleRequest(request));
    }

    @Test
    void handleRequest_invalidEmail_throwsException() {
        // GIVEN
        CreateEmployeeRequest request = CreateEmployeeRequest.builder()
                .withFirstName("Josh")
                .withLastName("Taylor")
                .withEmail("fasd")
                .withDateOfBirth("2000-01-01")
                .build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createEmployeeActivity.handleRequest(request));
    }
}