package com.example.ui.todos.domains.write;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.ui.todos.MainApplication;
import com.example.ui.todos.R;
import com.example.ui.todos.db.model.Word;
import com.example.ui.todos.domains.base.BaseActivity;
import com.example.ui.todos.domains.word.WordListAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;


@EActivity(R.layout.activity_write)
public class WriteActivity extends BaseActivity<WriteView, WritePresenter> implements WriteView {

    @App
    MainApplication application;
    @Inject
    WritePresenter presenter;

    @ViewById(R.id.tvName)
    TextView tvName;
    @ViewById(R.id.tvPoint)
    TextView tvPoint;
    @ViewById(R.id.tvWord)
    TextView tvWord;
    @ViewById(R.id.edtAnswer)
    EditText edtAnswer;
    @ViewById(R.id.btnCheck)
    MaterialButton btnCheck;

    @ViewById (R.id.tvOldPoint)
    TextView tvOldPoint;

    private WordListAdapter adapter;
    private List<Word> words;
    private int position = 0;
    private int point = 0;
    private FirebaseAuth mAuth;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("user");

    @AfterInject
    void inject() {
        DaggerWriteComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .build()
                .inject(this);
        presenter.setDbHelper(application.getApplicationComponent().dbHelper());
        presenter.getAllToDo();

    }

    @SuppressLint("ClickableViewAccessibility")
    @AfterViews
    void init() {

        edtAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edtAnswer.setSelection(s.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            tvName.setText(mAuth.getCurrentUser().getDisplayName());


                myRef.child(mAuth.getUid()).child("write").orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                        tvOldPoint.setText(String.format("Điểm cũ: %s đ",
                                dataSnapshot.getChildren().iterator().next().child("point").getValue().toString()));
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


        }
    }

    @Click(R.id.btnCheck)
    public void check() {
        if (edtAnswer.getText().toString().equals("")) {
            Toast.makeText(application, "Hãy nhập đáp án", Toast.LENGTH_SHORT).show();
        } else {
            if (edtAnswer.getText().toString().trim().toLowerCase().equals(words.get(position).getWord().toLowerCase().trim())) {
                point++;
                Toast.makeText(application, "Đáp án chính xác", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(application, "Đáp án không chính xác", Toast.LENGTH_SHORT).show();
            }
            position++;
            if (position >= 10) {
                tvPoint.setText(point + " đ");
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Bạn đã hoàn tất");
                builder1.setMessage("Kết quả: " + point + " diểm");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Kết thúc",
                        (dialog, id) -> {
                            dialog.cancel();
                            if (mAuth.getCurrentUser() != null) {
                                myRef.child(mAuth.getUid()).child("write")
                                        .child(Calendar.getInstance().getTime().getTime() + "").child("point").setValue(point);
                            }
                            onBackPressed();
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            } else {
                tvWord.setText(this.words.get(position).getMean());
                tvPoint.setText(point + " đ");
                edtAnswer.setText("");

            }
        }

    }

    @NonNull
    @Override
    public WritePresenter createPresenter() {
        return presenter;
    }


    @Override
    public void showListWord(List<Word> words) {
        this.words = words;
        tvWord.setText(this.words.get(position).getMean());
        tvPoint.setText(point + " đ");
    }
}
