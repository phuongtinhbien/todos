package com.example.ui.todos.domains.createTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.ui.todos.MainApplication;
import com.example.ui.todos.R;
import com.example.ui.todos.db.model.ToDo;
import com.example.ui.todos.domains.base.BaseActivity;
import com.example.ui.todos.domains.main.DaggerMainComponent;
import com.github.irshulx.Editor;
import com.github.irshulx.models.EditorTextStyle;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.Calendar;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;

@EActivity(R.layout.activity_create_task)
public class CreateTaskActivity extends BaseActivity<CreateTaskView, CreateTaskPresenter> implements CreateTaskView {

    @ViewById(R.id.create_todo_btn_save)
    MaterialButton save;

    @ViewById(R.id.create_todo_edt_title)
    TextInputEditText title;

    @ViewById(R.id.create_todo_edt_desc)
    Editor editor;

    @App
    MainApplication application;

    @Inject
    CreateTaskPresenter presenter;

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

    }

    @AfterViews
    void init() {
        save.setOnClickListener(v -> {
            ToDo toDo = new ToDo();
            toDo.setTitle(title.getText() != null ? title.getText().toString() : " ");
            toDo.setDesc(editor.getContentAsHTML());
            toDo.setCreateDate(Calendar.getInstance().getTime().getTime());
            presenter.createToDo(toDo);
        });
        setUpEditor();
    }

    @Override
    public void notify(boolean success) {
        if (success) {
            Toast.makeText(application, "Created!!!", Toast.LENGTH_SHORT).show();
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


}
