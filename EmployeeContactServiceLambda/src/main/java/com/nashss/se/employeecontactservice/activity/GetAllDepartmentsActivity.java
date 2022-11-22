package com.nashss.se.employeecontactservice.activity;

import com.nashss.se.employeecontactservice.activity.requests.GetAllDepartmentsRequest;
import com.nashss.se.employeecontactservice.activity.results.GetAllDepartmentsResult;
import com.nashss.se.employeecontactservice.dynamodb.DepartmentDao;
import com.nashss.se.employeecontactservice.dynamodb.models.Department;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;



public class GetAllDepartmentsActivity {
    private final Logger log = LogManager.getLogger();

    private final DepartmentDao departmentDao;

    /**
     * Instantiates a new GetAllDepartmentsActivity object.
     *
     * @param departmentDao EmployeeDao to access the employee table.
     */
    @Inject
    public GetAllDepartmentsActivity(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    /**
     * This method handles the incoming request by retrieving the departments from the database.
     * <p>
     * It then returns the GetAllDepartmentsResult.
     * <p>
     * If the department does not exist, this should throw a DepartmentNotFoundException.
     *
     * @param getAllDepartmentsRequest request object containing the dept ID
     * @return getAllDepartmentResult result object containing the API defined {@link Department}
     */
    public GetAllDepartmentsResult handleRequest(final GetAllDepartmentsRequest getAllDepartmentsRequest) {
        log.info("Received getAllDepartmentsRequest {}", getAllDepartmentsRequest);
        String requestedId = getAllDepartmentsRequest.getDeptId();
        List<Department> deptList = departmentDao.getAllActiveDepartmentsWithLimit(requestedId);

        return GetAllDepartmentsResult.builder()
                .withDeptList(deptList)
                .build();
    }
}
