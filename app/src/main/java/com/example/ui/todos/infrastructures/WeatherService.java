package com.example.ui.todos.infrastructures;


import com.example.ui.todos.model.weather.response.WeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface WeatherService {

    @GET("/data/2.5/weather?units=metric")
    Observable<WeatherResponse> getForecastByCity(@Query("q") String city, @Query("appid") String appId);
}
