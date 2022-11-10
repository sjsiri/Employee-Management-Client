package com.nashss.se.employeecontactservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.exceptions.EmployeeNotFoundException;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDao {

    private final DynamoDBMapper dynamoDBMapper;
    @Inject
    public EmployeeDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }


    public Employee getEmployee(String employeeId) {
        Employee employee = dynamoDBMapper.load(Employee.class, employeeId);
        if (null == employee) {
            throw new EmployeeNotFoundException(String.format("Could not find Employee with ID '%s' ", employeeId)
            );

        }

        return employee;
    }

    public List<Employee> getAllEmployeesWithLimit(Employee employee){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withLimit(20);

        if (employee != null){
            Map<String, AttributeValue> startKeyMap = new HashMap<>();
            startKeyMap.put("employeeId", new AttributeValue().withS(employee.getEmployeeId()));
            scanExpression.setExclusiveStartKey(startKeyMap);
        }

        ScanResultPage<Employee> employeePage = dynamoDBMapper.scanPage(Employee.class, scanExpression);
        return employeePage.getResults();
    }

    public List<Employee> getAllEmployeesWithLimit(){
        return getAllEmployeesWithLimit(null);
    }
}