package com.example.ui.todos.domains.word;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui.todos.MainApplication;
import com.example.ui.todos.R;
import com.example.ui.todos.db.model.Word;
import com.example.ui.todos.domains.base.BaseActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;


@EActivity(R.layout.activity_word)
public class WordActivity extends BaseActivity<WordView, WordPresenter> implements WordView {

    @App
    MainApplication application;
    @Inject
    WordPresenter presenter;
    @ViewById(R.id.word_list)
    RecyclerView wordList;

    private WordListAdapter adapter;

    @AfterInject
    void inject() {
        DaggerWordComponent.builder()
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
    public WordPresenter createPresenter() {
        return presenter;
    }


    @Override
    public void showListWord(List<Word> words) {
        adapter = new WordListAdapter(this, words);
        wordList.setAdapter(adapter);
    }
}
