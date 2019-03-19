package com.example.ui.todos.db.dao;

import com.example.ui.todos.db.model.Tags;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TagsDao {
    @Query("SELECT DISTINCT * FROM TAGS_TABLE WHERE STATUS = 'ACTIVE' ORDER BY id DESC")
    List<Tags> list();

    @Query("SELECT * FROM TAGS_TABLE WHERE ID = :id")
    Tags get(int id);

    @Query("SELECT * FROM TAGS_TABLE INNER JOIN TODO_TABLE ON TODO_TABLE.TAG = TAGS_TABLE.id WHERE TODO_TABLE.id = :toDoId LIMIT 1")
    Tags getTagByToDo(int toDoId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tags newInsert);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Tags... newUpdate);

    @Delete()
    void delete(Tags... deleteItem);


}
