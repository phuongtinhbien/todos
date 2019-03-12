package com.example.ui.todos.infrastructures;


import com.example.ui.todos.scope.ApplicationScope;

import dagger.Component;
import retrofit2.Retrofit;


@ApplicationScope
@Component(modules = NetModule.class)
public interface NetComponent {
    Retrofit retrofit();
}
