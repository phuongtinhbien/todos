package com.example.ui.todos.domains.word;

import com.example.ui.todos.db.model.ToDo;
import com.example.ui.todos.db.model.Word;
import com.example.ui.todos.model.weather.response.WeatherResponse;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;


public interface WordView extends MvpView {

    void showListWord(List<Word> toDos);


}
