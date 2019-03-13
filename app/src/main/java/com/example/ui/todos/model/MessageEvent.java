package com.example.ui.todos.model;

import com.example.ui.todos.db.model.ToDo;

public class MessageEvent {

    private ToDo toDo;

    public MessageEvent() {
    }

    public ToDo getToDo() {
        return toDo;
    }

    public void setToDo(ToDo toDo) {
        this.toDo = toDo;
    }
}
