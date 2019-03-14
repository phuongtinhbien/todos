package com.example.ui.todos.db.model;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class ToDoWithTags {

    @Embedded
    private ToDo toDo;

    @Relation(entity = Tags.class, parentColumn = "id", entityColumn = "TAG")
    private List<Tags> tags;
}
