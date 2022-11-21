package com.nashss.se.employeecontactservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.nashss.se.employeecontactservice.dynamodb.models.Department;
import com.nashss.se.employeecontactservice.exceptions.DepartmentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class DepartmentDaoTest {

    @Mock
    QueryResultPage<Department> queryResultPage;

    @Mock
    DynamoDBMapper mapper;

    DepartmentDao deptDao;

    @BeforeEach
    void setup() {
        openMocks(this);
        deptDao = new DepartmentDao(mapper);
    }
    @Test
    void getDepartment_departmentNotFound_throwsDepartmentNotFoundException() {
        // GIVEN
        String nonexistentDeptId = "NotReal";
        when(mapper.load(Department.class, nonexistentDeptId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(DepartmentNotFoundException.class, () -> deptDao.getDepartment(nonexistentDeptId));
    }

    @Test
    void getDepartment_withDeptId_callsMapperWithPartitionKey() {
        // GIVEN
        String deptId = "1";
        Department dept = new Department();
        dept.setDeptId(deptId);
        dept.setDeptName("John");
        when(mapper.load(Department.class, deptId)).thenReturn(dept);

        // WHEN
        Department result = deptDao.getDepartment(deptId);

        // THEN
        assertNotNull(result);
        verify(mapper).load(Department.class, deptId);
        assertEquals(dept, result);
    }

    @Test
    void getAllActiveDepartmentsWithLimit_queriesDatabase() {
        // GIVEN
        String deptIdStart = "0";
        Boolean forward = true;

        // WHEN
        when(mapper.queryPage(eq(Department.class), any())).thenReturn(queryResultPage);
        deptDao.getAllActiveDepartmentsWithLimit(deptIdStart, forward);

        // THEN
        verify(mapper).queryPage(eq(Department.class), any());
        verify(queryResultPage).getResults();
    }

    @Test
    void createEmployee_callsMapper() {
        // GIVEN
        Department departmentToCreate = new Department();

        // WHEN
        deptDao.saveDepartment(departmentToCreate);

        // THEN
        verify(mapper).save(departmentToCreate);
    }
}
