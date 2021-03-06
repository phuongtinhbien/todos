package com.example.ui.todos.domains.codeTest;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.todos.MainApplication;
import com.example.ui.todos.R;
import com.example.ui.todos.db.model.CodeTest;
import com.example.ui.todos.db.model.Word;
import com.example.ui.todos.domains.base.BaseActivity;
import com.example.ui.todos.domains.word.DaggerWordComponent;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;


@EActivity(R.layout.activity_code)
public class CodeTestActivity extends BaseActivity<CodeView, CodeTestPresenter> implements CodeView {

    @App
    MainApplication application;
    @Inject
    CodeTestPresenter presenter;
    @ViewById(R.id.word_list)
    RecyclerView wordList;

    private CodeTestListAdapter adapter;

    @AfterInject
    void inject() {
        DaggerCodeTestComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .build()
                .inject(this);
        presenter.setDbHelper(application.getApplicationComponent().dbHelper());
        presenter.getAllToDo();

    }

    @SuppressLint("ClickableViewAccessibility")
    @AfterViews
    void init() {

    }

    @NonNull
    @Override
    public CodeTestPresenter createPresenter() {
        return presenter;
    }


    @Override
    public void showListWord(List<CodeTest> words) {
        adapter = new CodeTestListAdapter(this, words);
        wordList.setAdapter(adapter);
    }
}
