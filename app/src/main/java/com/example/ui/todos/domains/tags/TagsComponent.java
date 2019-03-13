package com.example.ui.todos.domains.tags;


import com.example.ui.todos.infrastructures.ApplicationComponent;
import com.example.ui.todos.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class)
public abstract class TagsComponent {

    public abstract TagsPresenter presenter();

    public abstract void inject(TagsActivity tagsActivity);

}
