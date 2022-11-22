package com.nashss.se.employeecontactservice.dynamodb;
import com.nashss.se.employeecontactservice.dynamodb.models.Department;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

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
     * Saves (creates or updates) the given department.
     * @param dept The department to save
     */
    public void saveDepartment(Department dept) {
        this.dynamoDBMapper.save(dept);
    }


}
