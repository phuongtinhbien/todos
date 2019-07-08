package com.example.ui.todos.db;


import com.example.ui.todos.db.model.CodeTest;
import com.example.ui.todos.db.model.Tags;
import com.example.ui.todos.db.model.Test;
import com.example.ui.todos.db.model.ToDo;
import com.example.ui.todos.db.model.Word;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.schedulers.Schedulers;

@Singleton
public class DbHelperImpl implements DBHelper {

    private final AppDatabase db;

    @Inject
    public DbHelperImpl(AppDatabase db) {
        this.db = db;
    }

    //TODOLIST
    @Override
    public Observable<List<ToDo>> listAllToDo() {
        return Observable.fromCallable(() -> {
            List<ToDo> res = db.todoDao().list();
            return res != null ? res : new ArrayList<ToDo>();
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> saveToDo(final ToDo toDo) {
        return Observable.fromCallable(() -> {
            db.todoDao().insert(toDo);
            return true;
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> updateToDo(final ToDo... toDo) {
        return Observable.fromCallable(() -> {
            db.todoDao().update(toDo);
            return true;
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> deleteToDo(final ToDo... toDo) {
        return Observable.fromCallable(() -> {
            db.todoDao().delete(toDo);
            return true;
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ToDo> getToDo(int id) {
        return Observable.fromCallable(() -> db.todoDao().get(id)).subscribeOn(Schedulers.io());
    }

    //TAGS
    @Override
    public Observable<List<Tags>> listAllTags() {
        return Observable.fromCallable(() -> {
            List<Tags> res = db.tagsDao().list();
            return res != null ? res : new ArrayList<Tags>();
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> saveTags(final Tags tags) {
        return Observable.fromCallable(() -> {
            db.tagsDao().insert(tags);
            return true;
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> updateTags(final Tags... tags) {
        return Observable.fromCallable(() -> {
            db.tagsDao().update(tags);
            return true;
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> deleteTags(final Tags... tags) {
        return Observable.fromCallable(() -> {
            db.tagsDao().delete(tags);
            return true;
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Tags> getTag(int id) {
        return Observable.fromCallable(() -> db.tagsDao().get(id)).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Tags> getTagByToDo(int id) {
        return Observable.fromCallable(() -> db.tagsDao().getTagByToDo(id)).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<Word>> listAllWord() {
        return Observable.fromCallable(() -> {
            List<Word> res = db.wordDao().list();
            return res != null ? res : new ArrayList<Word>();
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> saveWord(Word toDo) {
        return Observable.fromCallable(() -> {
            db.wordDao().insert(toDo);
            return true;
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<Word>> listRandomWord() {
        return Observable.fromCallable(() -> {
            List<Word> res = db.wordDao().listRandom();
            return res != null ? res : new ArrayList<Word>();
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<String>> listOtherAnswer(int id) {
        return Observable.fromCallable(() -> {
            List<String> res = db.wordDao().list(id);
            return res != null ? res : new ArrayList<String>();
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<Test>> listAllTest(String code) {
        return Observable.fromCallable(() -> {
            List<Test> res = db.testDao().list(code);
            return res != null ? res : new ArrayList<Test>();
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> saveTest(Test toDo) {
        return Observable.fromCallable(() -> {
            db.testDao().insert(toDo);
            return true;
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<CodeTest>> listAllCodeTest() {
        return Observable.fromCallable(() -> {
            List<CodeTest> res = db.codeTestDao().list();
            return res != null ? res : new ArrayList<CodeTest>();
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> saveCodeTest(CodeTest toDo) {
        return Observable.fromCallable(() -> {
            db.codeTestDao().insert(toDo);
            return true;
        }).subscribeOn(Schedulers.io());
    }

}
