package main.java.com.nashss.se.employeecontactservice.activity;

import main.java.com.nashss.se.employeecontactservice.activity.requests.GetAllEmployeesRequest;
import main.java.com.nashss.se.employeecontactservice.activity.results.GetAllEmployeesResult;
import main.java.com.nashss.se.employeecontactservice.dynamodb.EmployeeDao;
import main.java.com.nashss.se.employeecontactservice.dynamodb.models.Employee;

import javax.inject.Inject;
import java.util.List;


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
