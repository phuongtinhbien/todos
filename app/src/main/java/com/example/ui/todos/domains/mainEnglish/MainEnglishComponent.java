package com.example.ui.todos.domains.mainEnglish;

import com.example.ui.todos.domains.word.WordActivity;
import com.example.ui.todos.domains.word.WordPresenter;
import com.example.ui.todos.infrastructures.ApplicationComponent;
import com.example.ui.todos.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class)
public abstract class MainEnglishComponent {

    public abstract MainEnglishPresenter presenter();

    public abstract void inject(MainEnglishActivity mainActivity);
}
