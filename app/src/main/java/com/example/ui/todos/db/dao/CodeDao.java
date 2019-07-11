package com.example.ui.todos.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ui.todos.db.model.CodeTest;
import com.example.ui.todos.db.model.Tags;

import java.util.List;

@Dao
public interface CodeDao {
    @Query("SELECT DISTINCT * FROM CODE_TABLE ORDER BY id ASC")
    List<CodeTest> list();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CodeTest newInsert);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(CodeTest... newUpdate);

    @Delete()
    void delete(CodeTest... deleteItem);

    @Query("DELETE FROM CODE_TABLE")
    void nukeTable();


}
