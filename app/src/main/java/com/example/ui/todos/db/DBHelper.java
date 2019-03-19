package com.example.ui.todos.db;


import com.example.ui.todos.db.model.Tags;
import com.example.ui.todos.db.model.ToDo;

import java.util.List;

import rx.Observable;

public interface DBHelper {

    Observable<List<ToDo>> listAllToDo();

    Observable<Boolean> saveToDo(ToDo toDo);

    Observable<Boolean> updateToDo(ToDo... toDo);

    Observable<Boolean> deleteToDo(ToDo... toDo);

    Observable<ToDo> getToDo (int id);

    //TAGS

    Observable<List<Tags>> listAllTags();

    Observable<Boolean> saveTags(Tags tags);

    Observable<Boolean> updateTags(Tags... Tags);

    Observable<Boolean> deleteTags(Tags... tags);

    Observable<Tags> getTag (int id);

    Observable<Tags> getTagByToDo (int id);

}
