package com.example.ui.todos.domains.listen;

import com.example.ui.todos.db.model.Word;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;


public interface ListenView extends MvpView {

    void showListWord(List<Word> toDos);


}
