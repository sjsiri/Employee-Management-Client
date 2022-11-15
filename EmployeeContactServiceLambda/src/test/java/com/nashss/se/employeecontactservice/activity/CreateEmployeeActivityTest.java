package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.dynamodb.EmployeeDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.openMocks;

class CreateEmployeeActivityTest {

    @Mock
    EmployeeDao employeeDao;

    CreateEmployeeActivity createEmployeeActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        createEmployeeActivity = new CreateEmployeeActivity(employeeDao);
    }

    @Test
    void handleRequest_invalidAttributes_throwsExceptions() {
        
    }
}