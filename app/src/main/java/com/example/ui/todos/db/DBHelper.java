package com.example.ui.todos.db;


import com.example.ui.todos.db.model.CodeTest;
import com.example.ui.todos.db.model.Tags;
import com.example.ui.todos.db.model.Test;
import com.example.ui.todos.db.model.ToDo;
import com.example.ui.todos.db.model.Word;

import java.util.List;

import rx.Observable;

public interface DBHelper {

    Observable<List<ToDo>> listAllToDo();

    Observable<Boolean> saveToDo(ToDo toDo);

    Observable<Boolean> updateToDo(ToDo... toDo);

    Observable<Boolean> deleteToDo(ToDo... toDo);

    Observable<ToDo> getToDo (int id);

    //TAGS

    Observable<List<Tags>> listAllTags();

    Observable<Boolean> saveTags(Tags tags);

    Observable<Boolean> updateTags(Tags... Tags);

    Observable<Boolean> deleteTags(Tags... tags);

    Observable<Tags> getTag (int id);

    Observable<Tags> getTagByToDo (int id);

    //Word
    Observable<List<Word>> listAllWord();
    Observable<Boolean> saveWord(Word toDo);
    Observable<List<Word>> listRandomWord();
    Observable<List<String>> listOtherAnswer(int id);

    //Test
    Observable<List<Test>> listAllTest(String code);
    Observable<Boolean> saveTest(Test toDo);

    //Code Test
    Observable<List<CodeTest>> listAllCodeTest();
    Observable<Boolean> saveCodeTest(CodeTest toDo);

    Observable<Boolean> deleteData();

}
