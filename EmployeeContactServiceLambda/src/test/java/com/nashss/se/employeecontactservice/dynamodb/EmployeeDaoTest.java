package com.nashss.se.employeecontactservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.exceptions.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class EmployeeDaoTest {

    @Mock
    QueryResultPage<Employee> queryResultPage;

    @Mock
    DynamoDBMapper mapper;

    EmployeeDao employeeDao;

    @BeforeEach
    void setup() {
        openMocks(this);
        employeeDao = new EmployeeDao(mapper);
    }

    @Test
    void getEmployee_employeeNotFound_throwsEmployeeNotFoundException() {
        // GIVEN
        String nonexistentEmployeeId = "NotReal";
        when(mapper.load(Employee.class, nonexistentEmployeeId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(EmployeeNotFoundException.class, () -> employeeDao.getEmployee(nonexistentEmployeeId));
    }

    @Test
    void getEmployee_withEmployeeId_callsMapperWithPartitionKey() {
        // GIVEN
        String employeeID = "1";
        Employee employee = new Employee();
        employee.setEmployeeId(employeeID);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        when(mapper.load(Employee.class, employeeID)).thenReturn(employee);

        // WHEN
        Employee result = employeeDao.getEmployee(employeeID);

        // THEN
        assertNotNull(result);
        verify(mapper).load(Employee.class, employeeID);
        assertEquals(employee, result);
    }

    @Test
    void getAllActiveEmployeesWithLimit_queriesDatabase() {
        // GIVEN
        String employeeIdStart = "0";
        Boolean forward = true;
        String deptId = "001";

        // WHEN
        when(mapper.queryPage(eq(Employee.class), any())).thenReturn(queryResultPage);
        employeeDao.getAllActiveEmployeesWithLimit(employeeIdStart, forward, deptId);

        // THEN
        verify(mapper).queryPage(eq(Employee.class), any());
        verify(queryResultPage).getResults();
    }

    @Test
    void createEmployee_callsMapper() {
        // GIVEN
        Employee employeeToCreate = new Employee();

        // WHEN
        employeeDao.saveEmployee(employeeToCreate);

        // THEN
        verify(mapper).save(employeeToCreate);
    }
}