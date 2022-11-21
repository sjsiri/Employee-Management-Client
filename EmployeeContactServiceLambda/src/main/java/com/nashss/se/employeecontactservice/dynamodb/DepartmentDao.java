package com.nashss.se.employeecontactservice.dynamodb;
import com.nashss.se.employeecontactservice.dynamodb.models.Department;
import com.nashss.se.employeecontactservice.exceptions.DepartmentNotFoundException;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Accesses data for a department using {@link Department} to represent the model in DynamoDB.
 */

public class DepartmentDao {

    private static final int PAGE_SIZE = 20;

    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates a DepartmentDao object.
     *
     * @param dynamoDBMapper the {@link DynamoDBMapper} used to interact with the department table
     */

    @Inject
    public DepartmentDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }
    /**
     * Returns the {@link Department} corresponding to the specified id.
     *
     * @param deptId the Department ID
     * @return the stored Department, or null if none was found.
     */

    public Department getDepartment(String deptId) {
        Department dept = dynamoDBMapper.load(Department.class, deptId);
        if (null == dept) {
            throw new DepartmentNotFoundException(String.format("Could not find Employee with ID '%s' ", deptId)
            );
        }
        return dept;
    }
    /**
     * Returns the list of {@link Department} starting at the supplied ID.
     *
     * @param departmentStartKey the Employee ID
     * @param forward boolean if true the page will go to the next if false it will go to previous.
     * @return the stored Departments, or null if none was found.
     */
    public List<Department> getAllActiveDepartmentsWithLimit(String departmentStartKey, Boolean forward) {
        Map<String, AttributeValue> startKeyMap = new HashMap<>();
        startKeyMap.put("deptStatus", new AttributeValue().withS("Active"));
        startKeyMap.put("deptId", new AttributeValue().withS(departmentStartKey));

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":deptStatus", new AttributeValue().withS("Active"));
        DynamoDBQueryExpression<Department> queryExpression = new DynamoDBQueryExpression<Department>()
                .withIndexName(Department.DEPARTMENT_STATUS)
                .withLimit(PAGE_SIZE)
                .withScanIndexForward(forward)
                .withConsistentRead(false)
                .withExclusiveStartKey(startKeyMap)
                .withKeyConditionExpression("deptStatus = :deptStatus")
                .withExpressionAttributeValues(valueMap);

        return dynamoDBMapper.queryPage(Department.class, queryExpression).getResults();

    }
    /**
     * Saves (creates or updates) the given department.
     * @param dept The employee to save
     */
    public void saveDepartment(Department dept) {
        this.dynamoDBMapper.save(dept);
    }


}
