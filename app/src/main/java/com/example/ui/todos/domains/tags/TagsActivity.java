package com.example.ui.todos.domains.tags;

import android.content.Intent;
import android.text.format.DateUtils;

import com.example.ui.todos.MainApplication;
import com.example.ui.todos.R;
import com.example.ui.todos.domains.base.BaseActivity;
import com.example.ui.todos.domains.createTask.CreateTaskActivity_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;

import java.util.Calendar;

import javax.inject.Inject;

import androidx.annotation.NonNull;

@EActivity(R.layout.activity_tags)
public class TagsActivity extends BaseActivity<TagsView, TagsPresenter> implements TagsView {
    @App
    MainApplication application;

    @Inject
    TagsPresenter presenter;
    @AfterInject
    void inject() {
        DaggerTagsComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .build()
                .inject(this);


    }

    @AfterViews
    void init() {
    }
    @NonNull
    @Override
    public TagsPresenter createPresenter() {
        return presenter;
    }
}
