package com.example.ui.todos.domains.main;

import com.example.ui.todos.db.model.ToDo;
import com.example.ui.todos.model.weather.Weather;
import com.example.ui.todos.model.weather.response.WeatherResponse;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Created by MyPC on 1/3/2017.
 */

public interface MainView extends MvpView {

    void showListTodo(List<ToDo> toDos);

    void notify(boolean success);

    void showWeatherForcast(WeatherResponse weather);

}
