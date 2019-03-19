package com.example.ui.todos.domains.tags;

import com.example.ui.todos.MainApplication;
import com.example.ui.todos.R;
import com.example.ui.todos.db.model.Tags;
import com.example.ui.todos.domains.base.BaseActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

@EActivity(R.layout.activity_tags)
public class TagsActivity extends BaseActivity<TagsView, TagsPresenter> implements TagsView {
    @App
    MainApplication application;

    @Inject
    TagsPresenter presenter;

    @ViewById(R.id.activity_tags_content_lv_tags)
    RecyclerView tags;

    TagsListAdapter tagsListAdapter;

    private List<Tags> tagsList;

    @AfterInject
    void inject() {
        DaggerTagsComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .build()
                .inject(this);
        presenter.setDbHelper(application.getApplicationComponent().dbHelper());
        presenter.getAllTag();
    }

    @AfterViews
    void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        tags.setHasFixedSize(true);
        tags.setLayoutManager(layoutManager);
        SwipeToDeleteCallback.RecyclerItemTouchHelperListener listener = (viewHolder, direction, position) -> {
            if (viewHolder instanceof TagsViewHolder) {
                final Tags deletedItem = tagsList.get(viewHolder.getAdapterPosition());
                tagsListAdapter.removeItem(viewHolder.getAdapterPosition());
                tagsList.remove(deletedItem);
                presenter.deleteTags(deletedItem);
            }
        };
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new SwipeToDeleteCallback(listener);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(tags);
    }

    @NonNull
    @Override
    public TagsPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void showListTags(List<Tags> tags) {
        tagsList = tags;
        System.out.println("tag_size: " + tags.size());
        tagsListAdapter = new TagsListAdapter(this, tags);
        this.tags.setAdapter(tagsListAdapter);
    }

    @Override
    public void notify(boolean success) {

    }
}
