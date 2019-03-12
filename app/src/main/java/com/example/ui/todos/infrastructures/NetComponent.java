package com.example.ui.todos.infrastructures;


import com.example.ui.todos.scope.ActivityScope;
import com.example.ui.todos.scope.ApplicationScope;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = NetModule.class)
public interface NetComponent {
    Retrofit retrofit();
}
