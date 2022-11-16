package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.GetSingleEmployeeDetailsRequest;
import com.nashss.se.employeecontactservice.activity.results.GetSingleEmployeeDetailsResult;
import com.nashss.se.employeecontactservice.dynamodb.EmployeeDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;


public class GetSingleEmployeeDetailsActivity {

    private final Logger log = LogManager.getLogger();

    private final EmployeeDao employeeDao;

    /**
     * Instantiates a new GetSingleEmployeeDetailsActivity object.
     *
     * @param employeeDao EmployeeDao to access the employee table.
     */

    @Inject
    public GetSingleEmployeeDetailsActivity(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * This method handles the incoming request by retrieving the employee from the database.
     * <p>
     * It then returns the employee.
     * <p>
     * If the employee does not exist, this should throw a EmployeeNotFoundException.
     *
     * @param getSingleEmployeeDetailsRequest request object containing the employee ID
     * @return getSingleEmployeeDetailsResult result object containing the API defined {@link Employee}
     */

    public GetSingleEmployeeDetailsResult handleRequest(final GetSingleEmployeeDetailsRequest
                                                                getSingleEmployeeDetailsRequest) {
        log.info("Received GetSingleEmployeeRequest {}", getSingleEmployeeDetailsRequest);
        String requestedId = getSingleEmployeeDetailsRequest.getEmployeeId();
        Employee singleEmployee = employeeDao.getEmployee(requestedId);

        return GetSingleEmployeeDetailsResult.builder()
                .withSingleEmployee(singleEmployee)
                .build();
    }
}
