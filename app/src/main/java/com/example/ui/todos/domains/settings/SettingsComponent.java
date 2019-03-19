package com.example.ui.todos.domains.settings;


import com.example.ui.todos.infrastructures.ApplicationComponent;
import com.example.ui.todos.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = ApplicationComponent.class)
public interface SettingsComponent {

    SettingsPresenter presenter();

    void inject(SettingsActivity settingsActivity);
}
