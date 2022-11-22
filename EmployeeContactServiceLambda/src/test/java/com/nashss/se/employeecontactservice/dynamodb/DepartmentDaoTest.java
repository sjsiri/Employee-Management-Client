package com.nashss.se.employeecontactservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.nashss.se.employeecontactservice.dynamodb.models.Department;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.exceptions.DepartmentNotFoundException;
import com.nashss.se.employeecontactservice.exceptions.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DepartmentDaoTest {

    @Mock
    PaginatedScanList<Department> paginatedScanList;

    @Mock
    DynamoDBMapper mapper;

    DepartmentDao departmentDao;

    @BeforeEach
    void setup() {
        openMocks(this);
        departmentDao = new DepartmentDao(mapper);
    }

    @Test
    void getDepartment_deptNotFound_throwsDepartmentNotFoundException() {
        // GIVEN
        String nonexistentDeptId = "NotReal";
        when(mapper.load(Department.class, nonexistentDeptId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(DepartmentNotFoundException.class, () -> departmentDao.getDepartment(nonexistentDeptId));
    }


    @Test
    void getAllActiveDepartmentsWithLimit_queriesDatabase() {
        // GIVEN
        String deptId = "0";

        // WHEN
        when(mapper.scan(eq(Department.class), any())).thenReturn(paginatedScanList);
        departmentDao.getAllActiveDepartmentsWithLimit(deptId);

        // THEN
        verify(mapper).scan(eq(Department.class), any());
        verify(paginatedScanList);
    }

}