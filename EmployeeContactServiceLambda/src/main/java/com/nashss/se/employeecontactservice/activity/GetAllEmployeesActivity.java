package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.GetAllEmployeesRequest;
import com.nashss.se.employeecontactservice.activity.results.GetAllEmployeesResult;
import com.nashss.se.employeecontactservice.dynamodb.EmployeeDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;



public class GetAllEmployeesActivity {
    private final Logger log = LogManager.getLogger();

    private final EmployeeDao employeeDao;

    /**
     * Instantiates a new GetEmployeeActivity object.
     *
     * @param employeeDao EmployeeDao to access the employee table.
     */
    @Inject
    public GetAllEmployeesActivity(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * This method handles the incoming request by retrieving the employee from the database.
     * <p>
     * It then returns the employee.
     * <p>
     * If the employee does not exist, this should throw a EmployeeNotFoundException.
     *
     * @param getEmployeeRequest request object containing the employee ID
     * @return getEmployeeResult result object containing the API defined {@link Employee}
     */
    public GetAllEmployeesResult handleRequest(final GetAllEmployeesRequest getEmployeeRequest) {
        log.info("Received GetEmployeeRequest {}", getEmployeeRequest);
        String requestedId = getEmployeeRequest.getEmployeeId();
        Employee employee = employeeDao.getEmployee(requestedId);
        List<Employee> employeeList = employeeDao.getAllEmployeesWithLimit(employee);


        return GetAllEmployeesResult.builder()
                .withEmployeeList(employeeList)
                .build();
    }
}
