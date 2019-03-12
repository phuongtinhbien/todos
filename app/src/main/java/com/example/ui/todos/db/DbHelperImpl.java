package com.example.ui.todos.db;


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

    @Override
    public Observable<List<ToDo>> listAllToDo() {
        return Observable.fromCallable(new Callable<List<ToDo>>() {
            @Override
            public List<ToDo> call() throws Exception {
                List<ToDo> res = db.todoDao().list();
                return res != null ? res : new ArrayList<ToDo>();
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> saveToDo(final ToDo toDo) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                db.todoDao().insert(toDo);
                return true;
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> updateToDo(final ToDo... toDo) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                db.todoDao().update(toDo);
                return true;
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> deleteToDo(final ToDo... toDo) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                db.todoDao().delete(toDo);
                return true;
            }
        }).subscribeOn(Schedulers.io());
    }
}
