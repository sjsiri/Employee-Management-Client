package main.java.com.nashss.se.employeecontactservice.dependency;

import com.nashss.se.musicplaylistservice.activity.AddSongToPlaylistActivity;
import com.nashss.se.musicplaylistservice.activity.CreatePlaylistActivity;
import com.nashss.se.musicplaylistservice.activity.GetPlaylistActivity;
import com.nashss.se.musicplaylistservice.activity.GetPlaylistSongsActivity;
import com.nashss.se.musicplaylistservice.activity.UpdatePlaylistActivity;

import dagger.Component;
import main.java.com.nashss.se.employeecontactservice.activity.GetAllEmployeesActivity;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    GetAllEmployeesActivity provideGetAllEmployeesActivity();


}
