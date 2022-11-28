package com.nashss.se.employeecontactservice.dependency;

import com.nashss.se.employeecontactservice.activity.CreateDepartmentActivity;
import com.nashss.se.employeecontactservice.activity.CreateEmployeeActivity;
import com.nashss.se.employeecontactservice.activity.GetAllDepartmentsActivity;
import com.nashss.se.employeecontactservice.activity.GetAllEmployeesActivity;
import com.nashss.se.employeecontactservice.activity.GetSingleEmployeeDetailsActivity;
import com.nashss.se.employeecontactservice.activity.UpdateEmployeeActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Employee Contact Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return GetAllEmployeesActivity
     */
    GetAllEmployeesActivity provideGetAllEmployeesActivity();


    /**
     * Provides the relevant activity.
     * @return UpdateEmployeeActivity
     */
    UpdateEmployeeActivity provideUpdateEmployeeActivity();
    /**
     * Provides the relevant activity.
     * @return GetSingleEmployeeDetailsActivity
     */
    GetSingleEmployeeDetailsActivity provideGetSingleEmployeeDetailsActivity();

    /**
     * Provides the relevant activity.
     * @return CreateEmployeeActivity
     */
    CreateEmployeeActivity provideCreateEmployeeActivity();

    /**
     * Provides the relevant activity.
     * @return CreateDepartmentActivity
     */
    CreateDepartmentActivity provideCreateDepartmentActivity();
    
    /**
     * Provides the relevant activity.
     * @return GetAllDepartmentsActivity
     */
    GetAllDepartmentsActivity provideGetAllDepartmentsActivity();

}
