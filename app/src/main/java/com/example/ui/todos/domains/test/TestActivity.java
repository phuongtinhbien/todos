package com.example.ui.todos.domains.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.ui.todos.MainApplication;
import com.example.ui.todos.R;
import com.example.ui.todos.db.model.Test;
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


@EActivity(R.layout.activity_test)
public class TestActivity extends BaseActivity<TestView, TestPresenter> implements TestView, Button.OnClickListener {

    @App
    MainApplication application;
    @Inject
    TestPresenter presenter;
    @ViewById(R.id.tvName)
    TextView tvName;
    @ViewById(R.id.tvPoint)
    TextView tvPoint;
    @ViewById(R.id.tvWord)
    TextView tvWord;

    @ViewById(R.id.btnCheck)
    MaterialButton btnCheck;
    @ViewById (R.id.tvOldPoint)
    TextView tvOldPoint;

    @ViewById(R.id.one)
    RadioButton one;
    @ViewById(R.id.two)
    RadioButton two;
    @ViewById(R.id.three)
    RadioButton three;
    @ViewById(R.id.four)
    RadioButton four;

    private WordListAdapter adapter;
    private List<Test> tests;
    private int position = 0;
    private int point = 0;
    private List<String> answers;

    private String answer = "";
    private String code;
    private String codeName;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("user");

    @AfterInject
    void inject() {
        Intent intent = getIntent();
        code = intent.getStringExtra("codeTest");
        codeName = intent.getStringExtra("codeName");
        setTitle(codeName);
        DaggerTestComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .build()
                .inject(this);
        presenter.setDbHelper(application.getApplicationComponent().dbHelper());
        presenter.getAllToDo(code);

    }

    @SuppressLint("ClickableViewAccessibility")
    @AfterViews
    void init() {
        Intent intent = getIntent();
        code = intent.getStringExtra("codeTest");
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            tvName.setText(mAuth.getCurrentUser().getDisplayName());

                myRef.child(mAuth.getUid()).child("test").child(code).orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                        tvOldPoint.setText(String.format("Điểm cũ: %s đ",
                                dataSnapshot.getChildren().iterator().next().child("point").getValue().toString()));
                        }catch (Exception e) {

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
        if (answer.equals("")) {
            Toast.makeText(application, "Hãy chọn đáp án", Toast.LENGTH_SHORT).show();
        } else {
            if (answer.equals(tests.get(position).getRightAnswer().toLowerCase().trim())) {
                point++;
                Toast.makeText(application, "Đáp án chính xác", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(application, "Đáp án không chính xác", Toast.LENGTH_SHORT).show();
            }
            position++;
            if (position >= tests.size()) {
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
                                myRef.child(mAuth.getUid()).child("test").child(code)
                                        .child(Calendar.getInstance().getTime().getTime() + "").child("point").setValue(point);
                            }
                            onBackPressed();
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            } else {
                initAnswerState();
                tvPoint.setText(point + " đ");
                tvWord.setText(tests.get(position).getQuestion());
                initAnswer();
            }
        }


    }

    @NonNull
    @Override
    public TestPresenter createPresenter() {
        return presenter;
    }


    @Override
    public void showListWord(List<Test> words) {
        if (words.size() > 0) {
            this.tests = words;
            initAnswer();
            tvWord.setText(words.get(position).getQuestion());
            tvPoint.setText(point + " đ");
        }

    }

    private void initAnswer() {
        one.setText(tests.get(position).getAnswer1());
        two.setText(tests.get(position).getAnswer2());
        three.setText(tests.get(position).getAnswer3());
        four.setText(tests.get(position).getAnswer4());
    }

    private void initAnswerState() {
        one.setChecked(false);
        two.setChecked(false);
        three.setChecked(false);
        four.setChecked(false);
    }

    @Override
    public void onClick(View v) {
        initAnswerState();
        if (one.getId() == v.getId()) {
            answer = one.getText().toString().toLowerCase().trim();
            one.setChecked(true);
        } else if (two.getId() == v.getId()) {
            answer = two.getText().toString().toLowerCase().trim();
            two.setChecked(true);

        } else if (three.getId() == v.getId()) {
            answer = three.getText().toString().toLowerCase().trim();
            three.setChecked(true);
        } else if (four.getId() == v.getId()) {
            answer = four.getText().toString().toLowerCase().trim();
            four.setChecked(true);
        }
    }
}
