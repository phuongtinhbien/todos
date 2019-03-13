package com.example.ui.todos.db;


import com.example.ui.todos.db.dao.TagsDao;
import com.example.ui.todos.db.dao.TodoDao;
import com.example.ui.todos.db.model.Tags;
import com.example.ui.todos.db.model.ToDo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ToDo.class, Tags.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TodoDao todoDao();

    public abstract TagsDao tagsDao();
}
