package com.example.ui.todos.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ACTION_TABLE")
public class Actions {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "CREATE_DATE")
    private long createDate;

    @ColumnInfo(name = "UPDATE_DATE")
    private long updateDate;

    @ColumnInfo(name = "TAG_ID")
    private int tag_id;

    @ColumnInfo(name = "ACTION_1")
    private String actionOne;

    @ColumnInfo(name = "ACTION_2")
    private String actionTwo;

    @ColumnInfo(name = "OPERATION")
    private String operation;

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

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getActionOne() {
        return actionOne;
    }

    public void setActionOne(String actionOne) {
        this.actionOne = actionOne;
    }

    public String getActionTwo() {
        return actionTwo;
    }

    public void setActionTwo(String actionTwo) {
        this.actionTwo = actionTwo;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
