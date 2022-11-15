package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.CreateEmployeeRequest;
import com.nashss.se.employeecontactservice.activity.results.CreateEmployeeResult;
import com.nashss.se.employeecontactservice.converters.LocalDateConverter;
import com.nashss.se.employeecontactservice.dynamodb.EmployeeDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Employee;
import com.nashss.se.employeecontactservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.employeecontactservice.utils.EmployeeMgmtClientServiceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class CreateEmployeeActivity {
    private final Logger log;

    private final EmployeeDao employeeDao;

    @Inject
    public CreateEmployeeActivity(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
        log = LogManager.getLogger();
    }

    public CreateEmployeeResult handleRequest(CreateEmployeeRequest request) {
        log.info("Received Create Employee Request {}", request);


        if (!EmployeeMgmtClientServiceUtils.isValidString(request.getFirstName())) {
            throw new InvalidAttributeValueException("First name \""
                    + request.getFirstName()
                    + "\" contains invalid characters");
        }

        if (!EmployeeMgmtClientServiceUtils.isValidString(request.getLastName())) {
            throw new InvalidAttributeValueException("Last name \""
                    + request.getLastName()
                    + "\" contains invalid characters");
        }

        if (!EmployeeMgmtClientServiceUtils.isValidString(request.getDeptName())) {
            throw new InvalidAttributeValueException("Department name \""
                    + request.getDeptName()
                    + "\" contains invalid characters");
        }

        if (!EmployeeMgmtClientServiceUtils.isValidString(request.getJobTitle())) {
            throw new InvalidAttributeValueException("Job title \""
                    + request.getJobTitle()
                    + "\" contains invalid characters");
        }

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

        employeeDao.createEmployee(employee);

        return CreateEmployeeResult.builder()
                .withEmployee(employee)
                .build();
    }
}
