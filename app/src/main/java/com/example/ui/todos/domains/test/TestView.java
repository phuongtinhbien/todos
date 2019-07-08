package com.example.ui.todos.domains.test;

import com.example.ui.todos.db.model.Test;
import com.example.ui.todos.db.model.Word;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;


public interface TestView extends MvpView {

    void showListWord(List<Test> toDos);


}
