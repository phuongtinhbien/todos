package com.example.ui.todos.domains.write;

import com.example.ui.todos.infrastructures.ApplicationComponent;
import com.example.ui.todos.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class)
public abstract class WriteComponent {

    public abstract WritePresenter presenter();

    public abstract void inject(WriteActivity mainActivity);
}
