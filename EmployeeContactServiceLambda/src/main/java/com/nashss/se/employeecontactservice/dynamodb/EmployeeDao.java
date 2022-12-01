package com.nashss.se.employeecontactservice.dynamodb;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.exceptions.EmployeeNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Accesses data for a playlist using {@link Employee} to represent the model in DynamoDB.
 */
public class EmployeeDao {

    private static final int PAGE_SIZE = 20;
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
     * Returns the list of {@link Employee} starting at the last name + ID.
     *
     * @param employeeStartKey the Employee ID
     * @param forward boolean if true the page will go to the next if false it will go to previous.
     * @param deptId deptId filters the results by the selected dept id
     * @return the stored Employees, or null if none was found.
     */
    public List<Employee> getAllActiveEmployeesWithLimit(String employeeStartKey, Boolean forward, String deptId) {
        Map<String, AttributeValue> startKeyMap = new HashMap<>();
        Map<String, AttributeValue> valueMap = new HashMap<>();

        valueMap.put(":employeeStatus", new AttributeValue().withS("Active"));

        DynamoDBQueryExpression<Employee> queryExpression = new DynamoDBQueryExpression<Employee>()
                .withScanIndexForward(forward)
                .withLimit(PAGE_SIZE)
                .withExclusiveStartKey(startKeyMap)
                .withExpressionAttributeValues(valueMap)
                .withConsistentRead(false);

        //If Department ID is passed as a query parameter, fetch employees in the department ID
        if (deptId != null) {
            startKeyMap.put("deptId", new AttributeValue().withS(deptId));
            valueMap.put(":deptId", new AttributeValue().withS(deptId));

            queryExpression.setIndexName(Employee.DEPARTMENT_GSI);
            queryExpression.setKeyConditionExpression("deptId = :deptId");
            queryExpression.setFilterExpression("employeeStatus = :employeeStatus");

        } else {
            //If Department ID is NOT passed as a query parameter, fetch all employees
            startKeyMap.put("employeeStatus", new AttributeValue().withS("Active"));

            queryExpression.setIndexName(Employee.LASTNAME_STATUS);
            queryExpression.setKeyConditionExpression("employeeStatus = :employeeStatus");
        }

        startKeyMap.put("lastNameEmployeeId", new AttributeValue().withS(employeeStartKey));
        String employeeId = (employeeStartKey.length() < 7) ? "0" :
                employeeStartKey.substring(employeeStartKey.length() - 5);
        startKeyMap.put("employeeId", new AttributeValue().withS(employeeId));

        return dynamoDBMapper.queryPage(Employee.class, queryExpression).getResults();

    }
    /**
     * Saves (creates or updates) the given employee.
     * @param employee The employee to save
     */
    public void saveEmployee(Employee employee) {
        this.dynamoDBMapper.save(employee);
    }

}
