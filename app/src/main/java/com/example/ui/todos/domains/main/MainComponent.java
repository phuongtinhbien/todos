package com.example.ui.todos.domains.main;

import com.example.ui.todos.domains.createTask.CreateTaskActivity;
import com.example.ui.todos.infrastructures.ApplicationComponent;
import com.example.ui.todos.scope.ActivityScope;
import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class)
public abstract class MainComponent {

    public abstract MainPresenter presenter();

    public abstract void inject(MainActivity mainActivity);
}
