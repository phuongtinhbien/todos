package com.example.ui.todos.db.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TODO_TABLE")
public class ToDo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "TITLE")
    private String title;

    @ColumnInfo(name = "DESC")
    private String desc;

    @ColumnInfo(name = "CREATE_DATE")
    private long createDate;

    @ColumnInfo(name = "UPDATE_DATE")
    private long updateDate;

    @ColumnInfo(name = "TIME_RUN")
    private long timeRun;

    @ColumnInfo(name = "STATUS")
    private String status;


//    @ColumnInfo(name = "TAG")
//    private String tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public long getTimeRun() {
        return timeRun;
    }

    public void setTimeRun(long timeRun) {
        this.timeRun = timeRun;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
//
//    public String getTags() {
//        return tags;
//    }
//
//    public void setTags(String tags) {
//        this.tags = tags;
//    }
}
