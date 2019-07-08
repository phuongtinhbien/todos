package com.example.ui.todos.db.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ui.todos.db.model.Test;
import com.example.ui.todos.db.model.Word;

import java.util.List;

@Dao
public interface TestDao {

    @Query("SELECT * FROM TEST_TABLE WHERE GROUP_TEST = :code")
    List<Test> list(String code);

    @Query("SELECT * FROM TEST_TABLE WHERE ID = :id")
    Test get(int id);

    @Query("SELECT * FROM TEST_TABLE ORDER BY RANDOM() LIMIT 2")
    List<Test> listRandom();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Test newInsert);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Test... newUpdate);

    @Delete()
    void delete(Test... deleteItem);

}
