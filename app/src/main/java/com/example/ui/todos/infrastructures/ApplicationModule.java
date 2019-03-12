package com.example.ui.todos.infrastructures;

import android.app.Application;

import com.example.ui.todos.db.AppDatabase;
import com.example.ui.todos.db.DBHelper;
import com.example.ui.todos.db.DbHelperImpl;
import com.example.ui.todos.scope.ApplicationScope;

import java.io.IOException;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

/**
 * Created by MyPC on 12/13/2016.
 */
@Module
public class ApplicationModule {

    private Application application;

    private AppDatabase database;


    public ApplicationModule(Application application) {
        this.application = application;
        this.database = Room.databaseBuilder(application, AppDatabase.class, "TODO.db").allowMainThreadQueries().build();
    }

    @Provides
    @ApplicationScope
    Application provideApplication() {
        return application;
    }



    @Provides
    @ApplicationScope
    public DBHelper provideDbHepler() {
        return new DbHelperImpl(database);
    }

}
