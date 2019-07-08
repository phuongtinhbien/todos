package com.example.ui.todos.domains.codeTest;

import com.example.ui.todos.infrastructures.ApplicationComponent;
import com.example.ui.todos.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class)
public abstract class CodeTestComponent {

    public abstract CodeTestPresenter presenter();

    public abstract void inject(CodeTestActivity mainActivity);
}
