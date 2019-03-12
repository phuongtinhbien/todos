package com.example.ui.todos.domains.createTask;


import com.example.ui.todos.infrastructures.ApplicationComponent;
import com.example.ui.todos.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class)
public interface CreateTaskComponent {

    CreateTaskPresenter presenter();

    void inject(CreateTaskActivity createTaskActivity);
}

