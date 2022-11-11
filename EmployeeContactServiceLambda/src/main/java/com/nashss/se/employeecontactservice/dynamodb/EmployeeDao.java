package com.nashss.se.employeecontactservice.dynamodb;

import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.exceptions.EmployeeNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Accesses data for a playlist using {@link Employee} to represent the model in DynamoDB.
 */
public class EmployeeDao {

    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates a EmployeeDao object.
     *
     * @param dynamoDBMapper the {@link DynamoDBMapper} used to interact with the employees table
     */
    @Inject
    public EmployeeDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     * Returns the {@link Employee} corresponding to the specified id.
     *
     * @param employeeId the Employee ID
     * @return the stored Employee, or null if none was found.
     */

    public Employee getEmployee(String employeeId) {
        Employee employee = dynamoDBMapper.load(Employee.class, employeeId);
        if (null == employee) {
            throw new EmployeeNotFoundException(String.format("Could not find Employee with ID '%s' ", employeeId)
            );

        }
        return employee;
    }

    /**
     * Returns the {@link Employee} corresponding to the specified id.
     *
     * @param employee the Employee ID
     * @return the stored Employees, or null if none was found.
     */

    public List<Employee> getAllEmployeesWithLimit(Employee employee) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withLimit(20);

        if (employee != null) {
            Map<String, AttributeValue> startKeyMap = new HashMap<>();
            startKeyMap.put("employeeId", new AttributeValue().withS(employee.getEmployeeId()));
            scanExpression.setExclusiveStartKey(startKeyMap);
        }

        ScanResultPage<Employee> employeePage = dynamoDBMapper.scanPage(Employee.class, scanExpression);
        return employeePage.getResults();
    }

    public List<Employee> getAllEmployeesWithLimit() {
        return getAllEmployeesWithLimit(null);
    }
}
