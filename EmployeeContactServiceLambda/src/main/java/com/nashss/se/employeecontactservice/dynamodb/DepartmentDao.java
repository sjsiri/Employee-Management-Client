package com.nashss.se.employeecontactservice.dynamodb;
import com.nashss.se.employeecontactservice.dynamodb.models.Department;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import com.nashss.se.employeecontactservice.dynamodb.models.Department;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.exceptions.DepartmentNotFoundException;

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

    private static final int PAGE_SIZE = 20;

 * Accesses data for a playlist using {@link Employee} to represent the model in DynamoDB.
 */
public class DepartmentDao {

    private static final int PAGE_SIZE = 100;
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
     * Returns the {@link Employee} corresponding to the specified id.
     *
     * @param deptId the Employee ID
     * @return the stored Employee, or null if none was found.
     */

    public Department getDepartment(String deptId) {
        Department department = dynamoDBMapper.load(Department.class, deptId);
        if (null == department) {
            throw new DepartmentNotFoundException(String.format("Could not find Department with ID '%s' ", deptId)
            );
        }
        return department;
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
    /**
     * Saves (creates or updates) the given department.
     * @param department The department to save
     */
    public void saveDepartment(Department department) {
        this.dynamoDBMapper.save(department);
    }

}
