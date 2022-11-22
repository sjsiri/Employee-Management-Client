package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.CreateDepartmentRequest;
import com.nashss.se.employeecontactservice.dynamodb.DepartmentDao;
import com.nashss.se.employeecontactservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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
        CreateDepartmentRequest request = CreateDepartmentRequest.builder().withDeptName("Josh").build();

        // WHEN
        createDepartmentActivity.handleRequest(request);

        // THEN
        verify(deptDao).saveDepartment(any());
    }

    @Test
    void handleRequest_invalidDepartmentName_throwsException() {
        // GIVEN
        CreateDepartmentRequest request = CreateDepartmentRequest.
                builder().
                withDeptName("\'apostrophe").
                build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createDepartmentActivity.handleRequest(request));
    }

    @Test
    void handleRequest_invalidDepartmentId_throwsException() {
        // GIVEN
        CreateDepartmentRequest request = CreateDepartmentRequest.
                builder().
                withDeptId("\'apostrophe").
                build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> createDepartmentActivity.handleRequest(request));
    }

}
