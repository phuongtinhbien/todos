package com.example.ui.todos.db.dao;


import com.example.ui.todos.db.model.ToDo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM TODO_TABLE JOIN TAGS_TABLE ON TODO_TABLE.TAG = TAGS_TABLE.id ORDER BY CREATE_DATE DESC")
    List<ToDo> list();

    @Query("SELECT * FROM TODO_TABLE WHERE ID = :id")
    ToDo get(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ToDo newInsert);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ToDo... newUpdate);

    @Delete()
    void delete(ToDo... deleteItem);

}
