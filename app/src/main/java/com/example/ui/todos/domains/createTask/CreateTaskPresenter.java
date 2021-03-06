package com.example.ui.todos.domains.createTask;


import com.example.ui.todos.db.DBHelper;
import com.example.ui.todos.db.model.Tags;
import com.example.ui.todos.db.model.ToDo;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class CreateTaskPresenter extends MvpBasePresenter<CreateTaskView> {

    private DBHelper dbHelper;

    private final String NEW_STATUS = "NEW";

    @Inject
    public CreateTaskPresenter() {
    }

    public void createToDo(ToDo toDos) {
        toDos.setStatus(NEW_STATUS);
        dbHelper.saveToDo(toDos).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                getView().notify(aBoolean);
            }
        });

    }

    public void getToDo(int id) {
        dbHelper.getToDo(id).zipWith(dbHelper.getTagByToDo(id), (toDo, tags) -> {
            toDo.setTag(tags);
            return toDo;
        })
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ToDo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ToDo toDo) {
                getView().showToDo(toDo);
            }
        });

    }

    protected void getAllTag() {
        dbHelper.listAllTags().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Tags>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<Tags> toDos) {
                getView().showListTags(toDos);
            }
        });

    }

    public void setDbHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
}
