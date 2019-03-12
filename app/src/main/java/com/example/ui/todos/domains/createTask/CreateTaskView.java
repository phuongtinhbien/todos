package com.example.ui.todos.domains.createTask;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface CreateTaskView  extends MvpView {

    void notify(boolean success);
}
