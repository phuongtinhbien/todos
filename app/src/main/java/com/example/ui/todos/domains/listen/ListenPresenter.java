package com.example.ui.todos.domains.listen;


import com.example.ui.todos.db.DBHelper;
import com.example.ui.todos.db.model.Word;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;


public class ListenPresenter extends MvpBasePresenter<ListenView> {

    private DBHelper dbHelper;

    @Inject
    public ListenPresenter() {
    }

    protected void getAllToDo() {
        dbHelper.listAllWord().observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Word>>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(List<Word> toDos) {
                getView().showListWord(toDos);
            }
        });
    }



    public void setDbHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
        getAllToDo();
    }


}
