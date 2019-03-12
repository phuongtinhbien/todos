package com.example.ui.todos.infrastructures;

import com.example.ui.todos.MainApplication;
import com.example.ui.todos.scope.ActivityScope;
import com.example.ui.todos.scope.ApplicationScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = NetComponent.class, modules = WeatherModule.class)
public interface WeatherComponent {
    WeatherService weatherService();
}
