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
    @Query("SELECT * FROM TAGS_TABLE ORDER BY CREATE_DATE DESC")
    List<Tags> list();

    @Query("SELECT * FROM TAGS_TABLE WHERE ID = :id")
    Tags get(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tags newInsert);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Tags... newUpdate);

    @Delete()
    void delete(Tags... deleteItem);

}
