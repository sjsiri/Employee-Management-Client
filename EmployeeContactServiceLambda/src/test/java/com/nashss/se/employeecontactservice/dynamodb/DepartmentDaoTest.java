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
    DynamoDBMapper mapper;

    DepartmentDao deptDao;

    @BeforeEach
    void setup() {
        openMocks(this);
        deptDao = new DepartmentDao(mapper);
    }

    @Test
    void createDepartment_callsMapper() {
        // GIVEN
        Department departmentToCreate = new Department();

        // WHEN
        deptDao.saveDepartment(departmentToCreate);

        // THEN
        verify(mapper).save(departmentToCreate);
    }
}
