package com.example.ui.todos.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TAGS_TABLE")
public class Tags {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "CREATE_DATE")
    private long createDate;

    @ColumnInfo(name = "UPDATE_DATE")
    private long updateDate;

    @ColumnInfo(name = "NAME")
    private String name;

    @ColumnInfo(name = "STATUS")
    private String status;

    @ColumnInfo(name = "ICON")
    private int icon;

    public Tags() {
    }

    public Tags(long createDate, String name, int icon) {
        this.createDate = createDate;
        this.name = name;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
