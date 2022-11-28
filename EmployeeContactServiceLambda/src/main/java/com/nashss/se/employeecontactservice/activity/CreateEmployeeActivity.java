package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.CreateEmployeeRequest;
import com.nashss.se.employeecontactservice.activity.results.CreateEmployeeResult;
import com.nashss.se.employeecontactservice.converters.LocalDateConverter;
import com.nashss.se.employeecontactservice.dynamodb.EmployeeDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.employeecontactservice.exceptions.MissingRequiredFieldException;
import com.nashss.se.employeecontactservice.utils.EmployeeMgmtClientServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateEmployeeActivity {
    private final Logger log;

    private final EmployeeDao employeeDao;

    /**
     * Instantiates a new CreateEmployeeActivity object.
     *
     * @param employeeDao EmployeeDao to access the employee table.
     */
    @Inject
    public CreateEmployeeActivity(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
        log = LogManager.getLogger();
    }

    /**
     * This method handles the incoming request by creating an employee in the database.
     * <p>
     * It then returns the employee.
     * <p>
     * If First name, last name, department name, or job title are invalid strings,
     * this should throw an InvalidAttributeValueException.
     *
     * @param request request object containing the employee's information
     * @return getEmployeeResult result object containing the API defined {@link Employee}
     */
    public CreateEmployeeResult handleRequest(CreateEmployeeRequest request) {
        log.info("Received Create Employee Request {}", request);

        checkAttributes(request);

        LocalDateConverter converter = new LocalDateConverter();

        Employee employee = new Employee();
        employee.setEmployeeId(EmployeeMgmtClientServiceUtils.generateEmployeeId());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setJobTitle(request.getJobTitle());
        employee.setEmail(request.getEmail());
        employee.setDeptId(request.getDeptId());
        employee.setDeptName(request.getDeptName());
        employee.setHireDate(converter.unconvert(request.getHireDate()));
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setDateOfBirth(converter.unconvert(request.getDateOfBirth()));
        employee.setEmployeeStatus(request.getEmployeeStatus());

        employeeDao.saveEmployee(employee);

        return CreateEmployeeResult.builder()
                .withEmployee(employee)
                .build();
    }

    /**
     * Checks the important attributes of a CreateEmployeeRequest for validity.
     * <p>
     * This includes required fields and String validation for email.
     * </p>
     * @param request the CreateEmployeeRequest to check
     */
    private void checkAttributes(CreateEmployeeRequest request) {
        if (request.getFirstName() == null) {
            throw new MissingRequiredFieldException("firstName is a required field.");
        }

        if (request.getLastName() == null) {
            throw new MissingRequiredFieldException("lastName is a required field.");
        }

        if (request.getEmail() == null) {
            throw new MissingRequiredFieldException("email is a required field.");
        }

        if (request.getDateOfBirth() == null) {
            throw new MissingRequiredFieldException("dateOfBirth is a required field.");
        }

        if (!EmployeeMgmtClientServiceUtils.isValidString(request.getFirstName())) {
            throw new InvalidAttributeValueException("First name \"" +
                    request.getFirstName() +
                    "\" contains invalid characters");
        }

        if (!EmployeeMgmtClientServiceUtils.isValidString(request.getLastName())) {
            throw new InvalidAttributeValueException("Last name \"" +
                    request.getLastName() +
                    "\" contains invalid characters");
        }

        if (request.getDeptName() != null && !EmployeeMgmtClientServiceUtils.isValidString(request.getDeptName())) {
            throw new InvalidAttributeValueException("Department name \"" +
                    request.getDeptName() +
                    "\" contains invalid characters");
        }

        if (request.getJobTitle() != null && !EmployeeMgmtClientServiceUtils.isValidString(request.getJobTitle())) {
            throw new InvalidAttributeValueException("Job title \"" +
                    request.getJobTitle() +
                    "\" contains invalid characters");
        }

        if (!EmployeeMgmtClientServiceUtils.isValidEmail(request.getEmail())) {
            throw new InvalidAttributeValueException("Email \"" +
                    request.getEmail() +
                    "\" contains invalid characters");
        }
    }
}
