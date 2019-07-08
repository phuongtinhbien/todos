package com.example.ui.todos.domains.codeTest;

import com.example.ui.todos.db.model.CodeTest;
import com.example.ui.todos.db.model.Word;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;


public interface CodeView extends MvpView {

    void showListWord(List<CodeTest> toDos);


}
