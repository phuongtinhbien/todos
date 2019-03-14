package com.example.ui.todos.domains.createTask;

import com.example.ui.todos.db.model.ToDo;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface CreateTaskView  extends MvpView {

    void notify(boolean success);
    void showToDo(ToDo toDo);
}
