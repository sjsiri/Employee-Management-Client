package com.nashss.se.employeecontactservice.dependency;

import com.nashss.se.employeecontactservice.activity.CreateEmployeeActivity;
import com.nashss.se.employeecontactservice.activity.GetAllEmployeesActivity;

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
     * @return CreateEmployeeActivity
     */
    CreateEmployeeActivity provideCreateEmployeeActivity();
}
