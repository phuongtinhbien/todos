package com.example.ui.todos.infrastructures;


import com.example.ui.todos.db.DBHelper;
import com.example.ui.todos.scope.ApplicationScope;

import dagger.Component;

/**
 * Created by MyPC on 12/13/2016.
 */

@ApplicationScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    DBHelper dbHelper();

}
