package com.example.ui.todos.db.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ui.todos.db.model.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM WORD_TABLE")
    List<Word> list();

    @Query("SELECT * FROM WORD_TABLE WHERE ID = :id")
    Word get(int id);

    @Query("SELECT * FROM WORD_TABLE ORDER BY RANDOM() LIMIT 2")
    List<Word> listRandom();

    @Query("SELECT WORD FROM WORD_TABLE WHERE ID IS NOT :id ORDER BY RANDOM() LIMIT 3 ")
    List<String> list(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Word newInsert);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Word... newUpdate);

    @Delete()
    void delete(Word... deleteItem);

    @Query("DELETE FROM WORD_TABLE")
    void nukeTable();
}
