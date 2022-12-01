package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.UpdateDepartmentRequest;
import com.nashss.se.employeecontactservice.activity.results.UpdateDepartmentResult;
import com.nashss.se.employeecontactservice.dynamodb.DepartmentDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Department;
import com.nashss.se.employeecontactservice.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateDepartmentActivityTest {
    @Mock
    private DepartmentDao departmentDao;

    @Mock
    private MetricsPublisher metricsPublisher;

    private UpdateDepartmentActivity updateDepartmentActivity;

    @BeforeEach
    public void setUp(){
        openMocks(this);
        updateDepartmentActivity = new UpdateDepartmentActivity(departmentDao, metricsPublisher);
    }

    @Test
    public void handleRequest_goodRequest_updatesDeptName() {
        // GIVEN
        String deptId = "123";
        String pathDeptId = "123";
        String expectedDeptName = "newDept";

        UpdateDepartmentRequest request = UpdateDepartmentRequest.builder()
                .withDeptId(deptId)
                .withDeptName(expectedDeptName)
                .build();
        request.setPathDeptId(pathDeptId);
        Department startingDepartment = new Department();
        startingDepartment.setDeptName("oldDept");


        when(departmentDao.getDepartment(deptId)).thenReturn(startingDepartment);

        // WHEN
        UpdateDepartmentResult result = updateDepartmentActivity.handleRequest(request);

        // THEN
        assertEquals(expectedDeptName, result.getDepartment().getDeptName());
    }

    @Test
    public void handleRequest_goodRequest_updatesDeptStatus() {
        // GIVEN
        String deptId = "123";
        String pathDeptId = "123";
        String expectedDeptStatus = "Active";

        UpdateDepartmentRequest request = UpdateDepartmentRequest.builder()
                .withDeptId(deptId)
                .withDeptStatus(expectedDeptStatus)
                .build();
        request.setPathDeptId(pathDeptId);
        Department startingDepartment = new Department();
        startingDepartment.setDeptStatus("Inactive");


        when(departmentDao.getDepartment(deptId)).thenReturn(startingDepartment);

        // WHEN
        UpdateDepartmentResult result = updateDepartmentActivity.handleRequest(request);

        // THEN
        assertEquals(expectedDeptStatus, result.getDepartment().getDeptStatus());
    }
}
