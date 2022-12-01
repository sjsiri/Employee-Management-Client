package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.UpdateDepartmentRequest;
import com.nashss.se.employeecontactservice.activity.results.UpdateDepartmentResult;
import com.nashss.se.employeecontactservice.dynamodb.DepartmentDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Department;
import com.nashss.se.employeecontactservice.exceptions.InvalidAttributeChangeException;
import com.nashss.se.employeecontactservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.employeecontactservice.metrics.MetricsConstants;
import com.nashss.se.employeecontactservice.metrics.MetricsPublisher;
import com.nashss.se.employeecontactservice.utils.EmployeeMgmtClientServiceUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateDepartmentActivity {

    private final Logger log = LogManager.getLogger();

    private final DepartmentDao departmentDao;

    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new UpdateDepartmentActivity object.
     *
     * @param departmentDao      DepartmentDao to access the departments table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public UpdateDepartmentActivity(DepartmentDao departmentDao, MetricsPublisher metricsPublisher) {
        this.departmentDao = departmentDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by retrieving the department, updating it,
     * and persisting the department.
     * <p>
     * It then returns the updated department.
     * <p>
     * If the department does not exist, this should throw a DepartmentNotFoundException.
     * <p>
     * If the provided departmentId and departmentName has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     *
     * @param updateDepartmentRequest request object containing the departmentId, departmentName, and deptStatus
     * @return updateDepartmentResult result object containing the API defined {@link Department}
     */
    public UpdateDepartmentResult handleRequest(final UpdateDepartmentRequest updateDepartmentRequest) {
        log.info("Received UpdateDepartmentRequest {}", updateDepartmentRequest);

        checkAttributes(updateDepartmentRequest);

        Department department = departmentDao.getDepartment(updateDepartmentRequest.getPathDeptId());

        if (updateDepartmentRequest.getDeptId() != null) {
            department.setDeptId(updateDepartmentRequest.getDeptId());
        }
        if (updateDepartmentRequest.getDeptName() != null) {
            department.setDeptName(updateDepartmentRequest.getDeptName());
        }
        if (updateDepartmentRequest.getDeptStatus() != null) {
            department.setDeptStatus(updateDepartmentRequest.getDeptStatus());
        }

        departmentDao.saveDepartment(department);
        publishExceptionMetrics(false);
        return UpdateDepartmentResult.builder().withDepartment(department).build();

    }

    private void checkAttributes(UpdateDepartmentRequest request) {
        if (request.getDeptId() != null &&
                !request.getDeptId().equals(request.getPathDeptId())) {
            throw new InvalidAttributeChangeException("Department's ID can't be changed");
        }

        if (!EmployeeMgmtClientServiceUtils.isValidString(request.getDeptName()) &&
                request.getDeptName() != null) {
            publishExceptionMetrics(true);
            throw new InvalidAttributeValueException("Department Name [" +
                    request.getDeptName() + "] contains illegal characters");
        }
    }

    /**
     * Helper method to publish exception metrics.
     * @param isInvalidAttributeValue indicates whether InvalidAttributeValueException is thrown
     */
    private void publishExceptionMetrics(final boolean isInvalidAttributeValue) {
        metricsPublisher.addCount(MetricsConstants.UPDATEDEPARTMENT_INVALIDATTRIBUTEVALUE_COUNT,
                isInvalidAttributeValue ? 1 : 0);
    }
}

