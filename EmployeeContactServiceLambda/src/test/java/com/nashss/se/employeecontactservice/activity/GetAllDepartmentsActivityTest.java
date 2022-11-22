package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.GetAllDepartmentsRequest;
import com.nashss.se.employeecontactservice.activity.results.GetAllDepartmentsResult;
import com.nashss.se.employeecontactservice.dynamodb.DepartmentDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetAllDepartmentsActivityTest {

    @Mock
    DepartmentDao departmentDao;

    GetAllDepartmentsActivity getAllDepartmentsActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        getAllDepartmentsActivity = new GetAllDepartmentsActivity(departmentDao);
    }

    @Test
    void handleRequest_departmentsFound_deptListReturnedInResult() {
        // GIVEN
        String deptId = "1";
        GetAllDepartmentsRequest request =
                GetAllDepartmentsRequest.builder()
                        .withDeptId(deptId)
                        .build();

        Department dept = new Department();
        dept.setDeptId("1");
        dept.setDeptName("Some Dept");
        dept.setDeptStatus("Active");

        List<Department> deptList = new ArrayList<>();
        deptList.add(dept);

        when(departmentDao.getAllActiveDepartmentsWithLimit(deptId)).thenReturn(deptList);

        // WHEN
        GetAllDepartmentsResult result = getAllDepartmentsActivity.handleRequest(request);

        // THEN
        verify(departmentDao).getAllActiveDepartmentsWithLimit(deptId);
        assertNotNull(result.getDeptList());
        assertEquals(deptList, result.getDeptList());
    }
}