package com.example.ui.todos.domains.listen;

import com.example.ui.todos.infrastructures.ApplicationComponent;
import com.example.ui.todos.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class)
public abstract class ListenComponent {

    public abstract ListenPresenter presenter();

    public abstract void inject(ListenActivity mainActivity);
}
