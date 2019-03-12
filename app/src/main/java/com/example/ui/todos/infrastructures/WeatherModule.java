package com.example.ui.todos.infrastructures;

import com.example.ui.todos.scope.ActivityScope;
import com.example.ui.todos.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class WeatherModule {

    private Retrofit retrofit;
    public WeatherModule(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Provides
    @ActivityScope
    public WeatherService provideWeatherService() {
        return retrofit.create(WeatherService.class);
    }


}
