package com.example.ui.todos.db;


import com.example.ui.todos.db.model.Tags;
import com.example.ui.todos.db.model.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

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

}
