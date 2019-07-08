package com.example.ui.todos.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "CODE_TABLE", indices = {@Index(value = {"CODE"},
        unique = true)})
public class CodeTest {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "NAME")
    private String name;
    @ColumnInfo(name = "CODE")
    private String code;

    public CodeTest() {
    }

    public CodeTest(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
