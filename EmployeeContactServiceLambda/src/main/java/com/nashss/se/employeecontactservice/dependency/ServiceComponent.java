package com.nashss.se.employeecontactservice.dependency;



import com.nashss.se.employeecontactservice.activity.GetAllEmployeesActivity;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    GetAllEmployeesActivity provideGetAllEmployeesActivity();


}
