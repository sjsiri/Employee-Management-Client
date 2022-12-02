package com.nashss.se.employeecontactservice.dynamodb;
import com.nashss.se.employeecontactservice.dynamodb.models.Department;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Accesses data for a department using {@link Department} to represent the model in DynamoDB.
 */
public class DepartmentDao {

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
     * Saves (creates or updates) the given department.
     * @param dept The department to save
     */
    public void saveDepartment(Department dept) {
        this.dynamoDBMapper.save(dept);
    }

    /**
     * Returns the {@link Department} corresponding to the specified id.
     *
     * @param deptId the Department ID
     * @return the stored Department, or null if none was found.
     */
    public Department getDepartment(String deptId) {
        return dynamoDBMapper.load(Department.class, deptId);
    }

    /**
     * Returns the list of {@link Department} starting at the supplied ID.
     *
     * @param deptStartKey the Department ID
     * @return the stored Departments, or null if none was found.
     */
    public List<Department> getAllActiveDepartmentsWithLimit(String deptStartKey) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":deptStatus", new AttributeValue().withS("Active"));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("deptStatus = :deptStatus")
                .withExpressionAttributeValues(valueMap);

        return dynamoDBMapper.scan(Department.class, scanExpression);
    }
}
