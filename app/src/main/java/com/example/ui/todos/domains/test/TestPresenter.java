package com.example.ui.todos.domains.test;


import com.example.ui.todos.db.DBHelper;
import com.example.ui.todos.db.model.Test;
import com.example.ui.todos.db.model.Word;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;


public class TestPresenter extends MvpBasePresenter<TestView> {

    private DBHelper dbHelper;

    @Inject
    public TestPresenter() {
    }

    protected void getAllToDo(String code) {
        dbHelper.listAllTest(code).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Test>>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(List<Test> toDos) {
                getView().showListWord(toDos);
            }
        });
    }



    public void setDbHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }


}
