package com.example.ui.todos.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WORD_TABLE")
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo (name = "WORD")
    private String word;

    @ColumnInfo (name = "MEAN")
    private String mean;

    @ColumnInfo (name = "TYPE")
    private String type;

    @ColumnInfo (name = "pro")
    private String pro;


    public Word(String word, String mean, String type, String pro) {
        this.word = word;
        this.mean = mean;
        this.type = type;
        this.pro = pro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public Word() {
    }
}
