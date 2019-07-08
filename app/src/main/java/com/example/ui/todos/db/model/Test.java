package com.example.ui.todos.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TEST_TABLE")
public class Test {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "NAME")
    private String name;

    @ColumnInfo(name = "GROUP_TEST")
    private String group;

    @ColumnInfo(name = "QUESTION")
    private String question;

    @ColumnInfo(name = "ANSWER_1")
    private String answer1;

    @ColumnInfo(name = "ANSWER_2")
    private String answer2;

    @ColumnInfo(name = "ANSWER_3")
    private String answer3;

    @ColumnInfo(name = "ANSWER_4")
    private String answer4;

    @ColumnInfo(name = "RIGHT_ANSWER")
    private String rightAnswer;

    public Test() {
    }

    public Test(String group, String question, String answer1, String answer2, String answer3, String answer4, String rightAnswer) {
        this.group = group;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.rightAnswer = rightAnswer;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
