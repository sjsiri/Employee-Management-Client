package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.GetSingleDepartmentDetailsRequest;
import com.nashss.se.employeecontactservice.activity.results.GetSingleDepartmentDetailsResult;
import com.nashss.se.employeecontactservice.dynamodb.DepartmentDao;

import com.nashss.se.employeecontactservice.dynamodb.models.Department;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetSingleDepartmentDetailsActivity {

    private final Logger log = LogManager.getLogger();

    private final DepartmentDao departmentDao;

    /**
     * Instantiates a new GetSingleDepartmentDetailsActivity object.
     *
     * @param departmentDao DepartmentDao to access the department table.
     */

    @Inject
    public GetSingleDepartmentDetailsActivity(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    /**
     * This method handles the incoming request by retrieving the department from the database.
     * <p>
     * It then returns the department.
     * <p>
     * If the department does not exist, this should throw a DepartmentNotFoundException.
     *
     * @param getSingleDepartmentDetailsRequest request object containing the department ID
     * @return getSingleDepartmentDetailsResult result object containing the API defined {@link Department}
     */
    public GetSingleDepartmentDetailsResult handleRequest(final GetSingleDepartmentDetailsRequest
                                                                  getSingleDepartmentDetailsRequest) {
        log.info("Recieved GetSingleDepartmentRequest {}", getSingleDepartmentDetailsRequest);
        String requestedId = getSingleDepartmentDetailsRequest.getDeptId();
        Department singleDepartment = departmentDao.getDepartment(requestedId);

        return GetSingleDepartmentDetailsResult.builder()
                .withSingleDepartment(singleDepartment)
                .build();
    }
}
