package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.UpdateEmployeeRequest;
import com.nashss.se.employeecontactservice.activity.results.UpdateEmployeeResult;
import com.nashss.se.employeecontactservice.converters.LocalDateConverter;
import com.nashss.se.employeecontactservice.dynamodb.EmployeeDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.exceptions.InvalidAttributeChangeException;
import com.nashss.se.employeecontactservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.employeecontactservice.metrics.MetricsConstants;
import com.nashss.se.employeecontactservice.metrics.MetricsPublisher;
import com.nashss.se.employeecontactservice.models.EmployeeModel;
import com.nashss.se.employeecontactservice.utils.EmployeeMgmtClientServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the UpdateEmployeeActivity for the EmployeeManagementClient's UpdateEmployee API.
 *
 * This API allows the user to update their saved employee's information.
 */
public class UpdateEmployeeActivity {

    private final Logger log = LogManager.getLogger();

    private final EmployeeDao employeeDao;

    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new UpdateEmployeeActivity object.
     *
     * @param employeeDao      EmployeeDao to access the employees table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public UpdateEmployeeActivity(EmployeeDao employeeDao, MetricsPublisher metricsPublisher) {
        this.employeeDao = employeeDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by retrieving the employee, updating it,
     * and persisting the employee.
     * <p>
     * It then returns the updated employee.
     * <p>
     * If the employee does not exist, this should throw a EmployeeNotFoundException.
     * <p>
     * If the provided employee firstName, lastName, JobTitle, or DeptName has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     *
     * @param updateEmployeeRequest request object containing the employees ID, employee name, and deptID
     *  and everything else associated with the employee
     * @return updateEmployeeResult result object containing the API defined {@link EmployeeModel}
     */
    public UpdateEmployeeResult handleRequest(final UpdateEmployeeRequest updateEmployeeRequest) {
        log.info("Received UpdateEmployeeRequest {}", updateEmployeeRequest);


        if (updateEmployeeRequest.getEmployeeId() != updateEmployeeRequest.getPathEmployeeId() &&
                updateEmployeeRequest.getEmployeeId() != null) {
            throw new InvalidAttributeChangeException("Employee's ID can't be changed");
        }

        if (!EmployeeMgmtClientServiceUtils.isValidString(updateEmployeeRequest.getFirstName()) &&
                updateEmployeeRequest.getFirstName() != null) {
            publishExceptionMetrics(true);
            throw new InvalidAttributeValueException("Employee firstName [" +
                    updateEmployeeRequest.getFirstName() + "] contains illegal characters");
        }

        if (!EmployeeMgmtClientServiceUtils.isValidString(updateEmployeeRequest.getLastName()) &&
                updateEmployeeRequest.getLastName() != null) {
            publishExceptionMetrics(true);
            throw new InvalidAttributeValueException("Employee lastName [" +
                    updateEmployeeRequest.getFirstName() + "] contains illegal characters");
        }

        if (!EmployeeMgmtClientServiceUtils.isValidString(updateEmployeeRequest.getJobTitle()) &&
                updateEmployeeRequest.getJobTitle() != null) {
            publishExceptionMetrics(true);
            throw new InvalidAttributeValueException("Employee jobTitle [" +
                    updateEmployeeRequest.getFirstName() + "] contains illegal characters");
        }

        if (!EmployeeMgmtClientServiceUtils.isValidString(updateEmployeeRequest.getDeptName()) &&
                updateEmployeeRequest.getDeptName() != null) {
            publishExceptionMetrics(true);
            throw new InvalidAttributeValueException("Employee DeptName [" +
                    updateEmployeeRequest.getFirstName() + "] contains illegal characters");
        }

        LocalDateConverter converter = new LocalDateConverter();

        Employee employee = employeeDao.getEmployee(updateEmployeeRequest.getPathEmployeeId());
        if (updateEmployeeRequest.getFirstName() != null) {
            employee.setFirstName(updateEmployeeRequest.getFirstName());
        }
        if (updateEmployeeRequest.getLastName() != null) {
            employee.setLastName(updateEmployeeRequest.getLastName());
        }
        if (updateEmployeeRequest.getJobTitle() != null) {
            employee.setJobTitle(updateEmployeeRequest.getJobTitle());
        }
        if (updateEmployeeRequest.getEmail() != null) {
            employee.setEmail(updateEmployeeRequest.getEmail());
        }
        if (updateEmployeeRequest.getDeptId() != null) {
            employee.setDeptId(updateEmployeeRequest.getDeptId());
        }
        if (updateEmployeeRequest.getDeptName() != null) {
            employee.setDeptName(updateEmployeeRequest.getDeptName());
        }
        if (updateEmployeeRequest.getHireDate() != null) {
            employee.setHireDate(converter.unconvert(updateEmployeeRequest.getHireDate()));
        }
        if (updateEmployeeRequest.getPhoneNumber() != null) {
            employee.setPhoneNumber(updateEmployeeRequest.getPhoneNumber());
        }
        if (updateEmployeeRequest.getDateOfBirth() != null) {
            employee.setDateOfBirth(converter.unconvert(updateEmployeeRequest.getDateOfBirth()));
        }
        if (updateEmployeeRequest.getEmployeeStatus() != null) {
            employee.setEmployeeStatus(updateEmployeeRequest.getEmployeeStatus());
        }

        employee = employeeDao.saveEmployee(employee);
        publishExceptionMetrics(false);
        return UpdateEmployeeResult.builder().withEmployeeModel(employee).build();

    }
    /**
     * Helper method to publish exception metrics.
     * @param isInvalidAttributeValue indicates whether InvalidAttributeValueException is thrown
     */
    private void publishExceptionMetrics(final boolean isInvalidAttributeValue) {
        metricsPublisher.addCount(MetricsConstants.UPDATEEMPLOYEE_INVALIDATTRIBUTEVALUE_COUNT,
                isInvalidAttributeValue ? 1 : 0);
    }
}
