package com.example.ui.todos.domains.test;

import com.example.ui.todos.infrastructures.ApplicationComponent;
import com.example.ui.todos.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class)
public abstract class TestComponent {

    public abstract TestPresenter presenter();

    public abstract void inject(TestActivity mainActivity);
}
