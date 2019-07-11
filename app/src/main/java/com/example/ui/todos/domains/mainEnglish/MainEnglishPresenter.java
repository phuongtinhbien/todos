package com.example.ui.todos.domains.mainEnglish;

import com.example.ui.todos.db.DBHelper;
import com.example.ui.todos.db.model.Word;
import com.example.ui.todos.domains.word.WordView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class MainEnglishPresenter extends MvpBasePresenter<MainEnglishView> implements MvpPresenter<MainEnglishView> {

    private DBHelper dbHelper;

    @Inject
    public MainEnglishPresenter() {
    }

    protected void deleteAllDb() {
        dbHelper.deleteData().observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {
                getView().updateSuccess();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {

            }
        });
    }



    public void setDbHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }


}
