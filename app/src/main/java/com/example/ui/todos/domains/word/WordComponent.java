package com.example.ui.todos.domains.word;

import com.example.ui.todos.db.model.Word;
import com.example.ui.todos.domains.main.MainActivity;
import com.example.ui.todos.domains.main.MainPresenter;
import com.example.ui.todos.infrastructures.ApplicationComponent;
import com.example.ui.todos.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class)
public abstract class WordComponent {

    public abstract WordPresenter presenter();

    public abstract void inject(WordActivity mainActivity);
}
