package com.example.ui.todos.db;


import com.example.ui.todos.db.dao.CodeDao;
import com.example.ui.todos.db.dao.TagsDao;
import com.example.ui.todos.db.dao.TestDao;
import com.example.ui.todos.db.dao.TodoDao;
import com.example.ui.todos.db.dao.WordDao;
import com.example.ui.todos.db.model.CodeTest;
import com.example.ui.todos.db.model.Tags;
import com.example.ui.todos.db.model.Test;
import com.example.ui.todos.db.model.ToDo;
import com.example.ui.todos.db.model.Word;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ToDo.class, Tags.class, Word.class, Test.class, CodeTest.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TodoDao todoDao();

    public abstract TagsDao tagsDao();

    public abstract WordDao wordDao();

    public abstract TestDao testDao();

    public abstract CodeDao codeTestDao();
}
