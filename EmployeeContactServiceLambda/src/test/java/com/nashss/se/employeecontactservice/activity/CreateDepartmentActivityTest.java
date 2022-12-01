package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.CreateDepartmentRequest;
import com.nashss.se.employeecontactservice.activity.results.CreateDepartmentResult;
import com.nashss.se.employeecontactservice.dynamodb.DepartmentDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Department;
import com.nashss.se.employeecontactservice.exceptions.DepartmentNotFoundException;
import com.nashss.se.employeecontactservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateDepartmentActivityTest {

    @Mock
    DepartmentDao deptDao;

    CreateDepartmentActivity createDepartmentActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        createDepartmentActivity = new CreateDepartmentActivity(deptDao);
    }

    @Test
    void handleRequest_validAttributes_callsCreateDepartment() {
        // GIVEN
        CreateDepartmentRequest request = CreateDepartmentRequest.builder().withDeptId("1000")
                .withDeptName("Josh").build();
        when(deptDao.getDepartment(any())).thenThrow(new DepartmentNotFoundException());

        // WHEN
        createDepartmentActivity.handleRequest(request);

        // THEN
        verify(deptDao).saveDepartment(any());
    }

    @Test
    void handleRequest_validAttributes_returnsResult() {
        // GIVEN
        CreateDepartmentRequest request = CreateDepartmentRequest.builder().withDeptId("123").withDeptName("sss")
                .build();
        when(deptDao.getDepartment(any())).thenThrow(new DepartmentNotFoundException());

        // WHEN
        CreateDepartmentResult result = createDepartmentActivity.handleRequest(request);

        // THEN
        Department department = new Department();
        department.setDeptName("sss");
        department.setDeptId(result.getDepartment().getDeptId());
        assertEquals(result.getDepartment(), department);
    }

    @Test
    void handleRequest_invalidDepartmentName_throwsException() {
        // GIVEN
        CreateDepartmentRequest request = CreateDepartmentRequest
                .builder()
                .withDeptName("dep@rtment")
                .build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createDepartmentActivity.handleRequest(request));
    }
}
