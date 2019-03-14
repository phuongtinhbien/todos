package com.example.ui.todos.domains.createTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ui.todos.MainApplication;
import com.example.ui.todos.R;
import com.example.ui.todos.db.model.Tags;
import com.example.ui.todos.db.model.ToDo;
import com.example.ui.todos.domains.base.BaseActivity;
import com.example.ui.todos.domains.main.DaggerMainComponent;
import com.github.irshulx.Editor;
import com.github.irshulx.models.EditorTextStyle;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

@EActivity(R.layout.activity_create_task)
public class CreateTaskActivity extends BaseActivity<CreateTaskView, CreateTaskPresenter> implements CreateTaskView {

    @ViewById(R.id.create_todo_btn_save)
    MaterialButton save;

    @ViewById(R.id.create_todo_edt_title)
    TextInputEditText title;

    @ViewById(R.id.create_todo_edt_desc)
    Editor editor;

    @ViewById(R.id.create_todo_layout_title)
    TextView titleAct;

    @ViewById(R.id.create_todo_cg_tags)
    ChipGroup tags;

    @App
    MainApplication application;

    @Inject
    CreateTaskPresenter presenter;

    private List<Tags> tagsList;
    private List<Chip> chipIdList;
    private Integer currTag = 0;

    private ToDo currToDo;

    @NonNull
    @Override
    public CreateTaskPresenter createPresenter() {
        return presenter;
    }

    @AfterInject
    void inject() {
        DaggerMainComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .build()
                .inject(this);
        presenter.setDbHelper(application.getApplicationComponent().dbHelper());
        presenter.getAllTag();
    }

    @AfterViews
    void init() {
        save.setOnClickListener(v -> {
            if (currToDo == null){
                currToDo = new ToDo();
                currToDo.setCreateDate(Calendar.getInstance().getTime().getTime());
            }
            currToDo.setTitle(title.getText() != null ? title.getText().toString() : " ");
            currToDo.setDesc(editor.getContentAsHTML());
            currToDo.setTagsId(tagsList.get(currTag).getId());
            presenter.createToDo(currToDo);
        });
        tags.setSingleSelection(true);
        tags.setOnCheckedChangeListener((chipGroup, i) -> {
            Chip curr = chipGroup.findViewById(chipGroup.getCheckedChipId());
            curr.setChipBackgroundColorResource(R.color.colorPrimary);
            curr.setTextColor(getResources().getColor(android.R.color.white));
            curr.setChipIconVisible(false);
        });
        setUpEditor();

    }

    @Override
    public void notify(boolean success) {
        if (success) {
            Toast.makeText(application, "Created!!!", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }

    private void setUpEditor() {
        findViewById(R.id.action_bold).setOnClickListener(v -> editor.updateTextStyle(EditorTextStyle.BOLD));
        findViewById(R.id.action_Italic).setOnClickListener(v -> editor.updateTextStyle(EditorTextStyle.ITALIC));
        findViewById(R.id.action_blockquote).setOnClickListener(v -> editor.updateTextStyle(EditorTextStyle.BLOCKQUOTE));
        findViewById(R.id.action_unordered_numbered).setOnClickListener(v -> editor.insertList(true));
        findViewById(R.id.action_insert_image).setOnClickListener(v -> editor.openImagePicker());
        findViewById(R.id.action_insert_link).setOnClickListener(v -> editor.insertLink());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == editor.PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                editor.insertImage(bitmap);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showToDo(ToDo toDo) {
        currToDo = toDo;
        System.out.println(toDo.getTitle());
        titleAct.setText(getString(R.string.edit_task));
        title.setText(toDo.getTitle());
        editor.render(toDo.getDesc());
    }

    @Override
    public void showListTags(List<Tags> t) {
        tagsList = t;
        chipIdList = new ArrayList<>();
        for (Tags i : t) {
            Chip newChip = new Chip(this);
            chipIdList.add(newChip);
            newChip.setCheckable(true);
            newChip.setChipIconResource(i.getIcon());
            newChip.setText(i.getName());
            if (currToDo != null && currToDo.getTagsId() == i.getId()){
                newChip.setChecked(true);
            }
            newChip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                Chip curr = (Chip) buttonView;
                if (!isChecked) {
                    curr.setChipBackgroundColorResource(R.color.gray_background);
                    curr.setTextColor(getResources().getColor(android.R.color.black));
                    curr.setChipIconVisible(true);
                } else {
                    this.currTag = chipIdList.indexOf(curr);
                }
            });
            tags.addView(newChip);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int toDoId = intent.getIntExtra("TODO_ID", -1);
        if (toDoId > 0)
            presenter.getToDo(toDoId);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
