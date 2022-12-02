package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.GetSingleDepartmentDetailsRequest;
import com.nashss.se.employeecontactservice.activity.results.GetSingleDepartmentDetailsResult;
import com.nashss.se.employeecontactservice.dynamodb.DepartmentDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Department;
import com.nashss.se.employeecontactservice.exceptions.DepartmentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetSingleDepartmentDetailsActivityTest {

    @Mock
    DepartmentDao departmentDao;

    GetSingleDepartmentDetailsActivity activity;

    @BeforeEach
    void setup() {
        openMocks(this);
        activity = new GetSingleDepartmentDetailsActivity(departmentDao);
    }

    @Test
    void handleRequest_validRequest_returnsDepartment() {
        // GIVEN
        GetSingleDepartmentDetailsRequest request = GetSingleDepartmentDetailsRequest.builder()
                .withDeptId("test good")
                .build();
        Department department = new Department();
        department.setDeptId("test good");
        department.setDeptName("test good name");
        department.setDeptStatus("Active");

        // WHEN
        when(departmentDao.getDepartment(request.getDeptId())).thenReturn(department);
        GetSingleDepartmentDetailsResult result = activity.handleRequest(request);

        // THEN
        verify(departmentDao).getDepartment(request.getDeptId());
        assertEquals(department, result.getSingleDepartment());
    }

    @Test
    void handleRequest_idNotFound_throwsDepartmentNotFoundException() {
        // GIVEN
        GetSingleDepartmentDetailsRequest request = GetSingleDepartmentDetailsRequest.builder()
                .withDeptId("test bad")
                .build();

        // WHEN
        when(departmentDao.getDepartment(request.getDeptId())).thenReturn(null);

        // THEN
        assertThrows(DepartmentNotFoundException.class, () -> activity.handleRequest(request));
        verify(departmentDao).getDepartment(request.getDeptId());
    }
}