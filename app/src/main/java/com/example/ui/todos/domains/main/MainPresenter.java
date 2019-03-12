package com.example.ui.todos.domains.main;


import com.example.ui.todos.db.DBHelper;
import com.example.ui.todos.db.model.ToDo;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by MyPC on 1/3/2017.
 */


public class MainPresenter extends MvpBasePresenter<MainView> {

    private DBHelper dbHelper;

    @Inject
    public MainPresenter() {
    }

    protected void getAllToDo() {
        dbHelper.listAllToDo().observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<ToDo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<ToDo> toDos) {
                getView().showListTodo(toDos);
            }
        });

    }


    public void deleteToDo(ToDo... toDos) {
        dbHelper.deleteToDo(toDos).observeOn(AndroidSchedulers.mainThread()).subscribe(aBoolean -> getView().notify(aBoolean));
        getAllToDo();
    }


    public void setDbHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
        getAllToDo();
    }
}
