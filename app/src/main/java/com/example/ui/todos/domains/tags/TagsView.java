package com.example.ui.todos.domains.tags;

import com.example.ui.todos.db.model.Tags;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

public interface TagsView extends MvpView {

    void showListTags(List<Tags> tags);

    void notify(boolean success);

}
