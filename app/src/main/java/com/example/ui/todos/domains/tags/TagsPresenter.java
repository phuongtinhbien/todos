package com.example.ui.todos.domains.tags;

import com.example.ui.todos.db.DBHelper;
import com.example.ui.todos.db.model.Tags;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class TagsPresenter extends MvpBasePresenter<TagsView> {

    private DBHelper dbHelper;

    @Inject
    public TagsPresenter() {
    }

    protected void getAllTag() {
        dbHelper.listAllTags().observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Tags>>() {
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


    public void deleteTags(Tags... tags) {
        dbHelper.deleteTags(tags).observeOn(AndroidSchedulers.mainThread()).subscribe(aBoolean -> getView().notify(aBoolean));
        getAllTag();
    }

    public void setDbHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
}
