package com.example.ui.todos.domains.codeTest;


import com.example.ui.todos.db.DBHelper;
import com.example.ui.todos.db.model.CodeTest;
import com.example.ui.todos.db.model.Word;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;


public class CodeTestPresenter extends MvpBasePresenter<CodeView> {

    private DBHelper dbHelper;

    @Inject
    public CodeTestPresenter() {
    }

    protected void getAllToDo() {
        dbHelper.listAllCodeTest().observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<CodeTest>>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(List<CodeTest> toDos) {
                getView().showListWord(toDos);
            }
        });
    }



    public void setDbHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
        getAllToDo();
    }


}
