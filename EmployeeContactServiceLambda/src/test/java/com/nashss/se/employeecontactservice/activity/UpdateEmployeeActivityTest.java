package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.UpdateEmployeeRequest;
import com.nashss.se.employeecontactservice.activity.results.UpdateEmployeeResult;
import com.nashss.se.employeecontactservice.dynamodb.EmployeeDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.exceptions.EmployeeNotFoundException;
import com.nashss.se.employeecontactservice.exceptions.InvalidAttributeChangeException;
import com.nashss.se.employeecontactservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.employeecontactservice.metrics.MetricsConstants;
import com.nashss.se.employeecontactservice.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateEmployeeActivityTest {

    @Mock
    private EmployeeDao employeeDao;

    @Mock
    private MetricsPublisher metricsPublisher;

    private UpdateEmployeeActivity updateEmployeeActivity;

    @BeforeEach
    public void setUp(){
        openMocks(this);
        updateEmployeeActivity = new UpdateEmployeeActivity(employeeDao, metricsPublisher);
    }

    @Test
    public void handleRequest_goodRequest_updatesFirstName() {
        // GIVEN
        String employeeId = "employeeId";
        String pathEmployeeId = "employeeId";
        String expectedFirstName = "newName";

        UpdateEmployeeRequest request = UpdateEmployeeRequest.builder()
                .withEmployeeId(employeeId)
                .withFirstName(expectedFirstName)
                .build();
        request.setPathEmployeeId(pathEmployeeId);
        Employee startingEmployee = new Employee();
        startingEmployee.setFirstName("oldName");


        when(employeeDao.getEmployee(employeeId)).thenReturn(startingEmployee);
        when(employeeDao.saveEmployee(startingEmployee)).thenReturn(startingEmployee);

        // WHEN
        UpdateEmployeeResult result = updateEmployeeActivity.handleRequest(request);

        // THEN
        assertEquals(expectedFirstName, result.getEmployeeModel().getFirstName());
    }

    @Test
    public void handleRequest_goodRequest_updatesLastName() {
        // GIVEN
        String employeeId = "employeeId";
        String pathEmployeeId = "employeeId";
        String expectedLastName = "newName";

        UpdateEmployeeRequest request = UpdateEmployeeRequest.builder()
                .withEmployeeId(employeeId)
                .withLastName(expectedLastName)
                .build();
        request.setPathEmployeeId(pathEmployeeId);
        Employee startingEmployee = new Employee();
        startingEmployee.setFirstName("oldName");

        when(employeeDao.getEmployee(employeeId)).thenReturn(startingEmployee);
        when(employeeDao.saveEmployee(startingEmployee)).thenReturn(startingEmployee);

        // WHEN
        UpdateEmployeeResult result = updateEmployeeActivity.handleRequest(request);

        // THEN
        assertEquals(expectedLastName, result.getEmployeeModel().getLastName());
    }

    @Test
    public void handleRequest_goodRequest_updatesEmail() {
        // GIVEN
        String employeeId = "employeeId";
        String pathEmployeeId = "employeeId";
        String expectedEmail = "email@gmail.com";


        UpdateEmployeeRequest request = UpdateEmployeeRequest.builder()
                .withEmployeeId(employeeId)
                .withEmail(expectedEmail)
                .build();
        request.setPathEmployeeId(pathEmployeeId);
        Employee startingEmployee = new Employee();
        startingEmployee.setEmail("oldName@gmail.com");

        when(employeeDao.getEmployee(employeeId)).thenReturn(startingEmployee);
        when(employeeDao.saveEmployee(startingEmployee)).thenReturn(startingEmployee);

        // WHEN
        UpdateEmployeeResult result = updateEmployeeActivity.handleRequest(request);

        // THEN
        assertEquals(expectedEmail, result.getEmployeeModel().getEmail());
    }

    @Test
    public void handleRequest_goodRequest_updatesJobTitle() {
        // GIVEN
        String employeeId = "employeeId";
        String pathEmployeeId = "employeeId";
        String expectedJobTitle = "cook";

        UpdateEmployeeRequest request = UpdateEmployeeRequest.builder()
                .withEmployeeId(employeeId)
                .withJobTitle(expectedJobTitle)
                .build();
        request.setPathEmployeeId(pathEmployeeId);
        Employee startingEmployee = new Employee();
        startingEmployee.setJobTitle("janitor");

        when(employeeDao.getEmployee(employeeId)).thenReturn(startingEmployee);
        when(employeeDao.saveEmployee(startingEmployee)).thenReturn(startingEmployee);

        // WHEN
        UpdateEmployeeResult result = updateEmployeeActivity.handleRequest(request);

        // THEN
        assertEquals(expectedJobTitle, result.getEmployeeModel().getJobTitle());
    }

    @Test
    public void handleRequest_goodRequest_updatesDeptId() {
        // GIVEN
        String employeeId = "employeeId";
        String pathEmployeeId = "employeeId";
        String expectedDeptId = "20";

        UpdateEmployeeRequest request = UpdateEmployeeRequest.builder()
                .withEmployeeId(employeeId)
                .withDeptId(expectedDeptId)
                .build();

        request.setPathEmployeeId(pathEmployeeId);
        Employee startingEmployee = new Employee();
        startingEmployee.setDeptId("1");

        when(employeeDao.getEmployee(employeeId)).thenReturn(startingEmployee);
        when(employeeDao.saveEmployee(startingEmployee)).thenReturn(startingEmployee);

        // WHEN
        UpdateEmployeeResult result = updateEmployeeActivity.handleRequest(request);

        // THEN
        assertEquals(expectedDeptId, result.getEmployeeModel().getDeptId());
    }

    @Test
    public void handleRequest_goodRequest_updatesDeptName() {
        // GIVEN
        String employeeId = "employeeId";
        String pathEmployeeId = "employeeId";
        String expectedDeptName = "Konoha";

        UpdateEmployeeRequest request = UpdateEmployeeRequest.builder()
                .withEmployeeId(employeeId)
                .withDeptName(expectedDeptName)
                .build();
        request.setPathEmployeeId(pathEmployeeId);
        Employee startingEmployee = new Employee();
        startingEmployee.setDeptName("Sand");

        when(employeeDao.getEmployee(employeeId)).thenReturn(startingEmployee);
        when(employeeDao.saveEmployee(startingEmployee)).thenReturn(startingEmployee);

        // WHEN
        UpdateEmployeeResult result = updateEmployeeActivity.handleRequest(request);

        // THEN
        assertEquals(expectedDeptName, result.getEmployeeModel().getDeptName());
    }

    @Test
    public void handleRequest_invalidName_throwsInvalidAttributeValueException() {
        // GIVEN
        UpdateEmployeeRequest request = UpdateEmployeeRequest.builder()
                .withEmployeeId("ID")
                .withFirstName("I'm Illegalllllll/")
                .build();
        request.setPathEmployeeId("ID");
        // WHEN + THEN
        try {
            updateEmployeeActivity.handleRequest(request);
            fail("Expected InvalidAttributeValueException to be thrown");
        } catch (InvalidAttributeValueException e) {
            verify(metricsPublisher).addCount(MetricsConstants.UPDATEEMPLOYEE_INVALIDATTRIBUTEVALUE_COUNT, 1);
        }

    }

    @Test
    public void handleRequest_employeeDoesNotExist_throwsEmployeeNotFoundException() {
        // GIVEN
        String employeeId = "employeeId";
        String pathEmployeeId = "employeeId";
        UpdateEmployeeRequest request = UpdateEmployeeRequest.builder()
                .withEmployeeId(employeeId)
                .withFirstName("firstName")
                .build();
        request.setPathEmployeeId(pathEmployeeId);
        when(employeeDao.getEmployee(employeeId)).thenThrow(new EmployeeNotFoundException());

        // THEN
        assertThrows(EmployeeNotFoundException.class, () -> updateEmployeeActivity.handleRequest(request));
    }

    @Test
    public void handleRequest_employeeIDsDoesNotMatch_throwsInvalidChangeException() {
        // GIVEN
        String employeeId = "emploi";
        String pathEmployeeId = "NotEmploi";
        UpdateEmployeeRequest request = UpdateEmployeeRequest.builder()
                .withEmployeeId(employeeId)
                .withFirstName("firstName")
                .build();
        request.setPathEmployeeId(pathEmployeeId);

        // THEN
        assertThrows(InvalidAttributeChangeException.class, () -> updateEmployeeActivity.handleRequest(request));

    }
}
