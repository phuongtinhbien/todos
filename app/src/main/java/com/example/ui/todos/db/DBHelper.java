package com.example.ui.todos.db;


import com.example.ui.todos.db.model.ToDo;

import java.util.List;

import rx.Observable;

public interface DBHelper {

    Observable<List<ToDo>> listAllToDo();

    Observable<Boolean> saveToDo(ToDo toDo);

    Observable<Boolean> updateToDo(ToDo... toDo);

    Observable<Boolean> deleteToDo(ToDo... toDo);

}
