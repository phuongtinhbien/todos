package com.example.ui.todos.domains.listen;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.ui.todos.MainApplication;
import com.example.ui.todos.R;
import com.example.ui.todos.db.model.Word;
import com.example.ui.todos.domains.base.BaseActivity;
import com.example.ui.todos.domains.word.WordListAdapter;
import com.example.ui.todos.ultil.AudioPlayer;
import com.google.android.material.button.MaterialButton;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;


@EActivity(R.layout.activity_listen)
public class ListenActivity extends BaseActivity<ListenView, ListenPresenter> implements ListenView, Button.OnClickListener {

    @App
    MainApplication application;
    @Inject
    ListenPresenter presenter;
    @ViewById(R.id.tvName)
    TextView tvName;
    @ViewById(R.id.tvPoint)
    TextView tvPoint;
    @ViewById(R.id.tvWord)
    ImageView tvWord;

    @ViewById(R.id.btnCheck)
    MaterialButton btnCheck;


    @ViewById(R.id.one)
    RadioButton one;
    @ViewById(R.id.two)
    RadioButton two;
    @ViewById(R.id.three)
    RadioButton three;
    @ViewById(R.id.four)
    RadioButton four;

    private WordListAdapter adapter;
    private List<Word> words;
    private int position = 0;
    private int point = 0;
    private List<String> answers;

    private String answer = "";

    @AfterInject
    void inject() {
        DaggerListenComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .build()
                .inject(this);
        presenter.setDbHelper(application.getApplicationComponent().dbHelper());
        presenter.getAllToDo();

    }

    @SuppressLint("ClickableViewAccessibility")
    @AfterViews
    void init() {
      one.setOnClickListener(this);
      two.setOnClickListener(this);
      three.setOnClickListener(this);
      four.setOnClickListener(this);
    }

    @Click(R.id.btnCheck)
    public void check() {
        if (answer.equals("")) {
            Toast.makeText(application, "Hãy chọn đáp án", Toast.LENGTH_SHORT).show();
        } else {
            if (answer.equals(words.get(position).getWord().toLowerCase().trim())) {
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
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                onBackPressed();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            } else {
                initAnswerState();
                tvPoint.setText(point + " đ");

                initAnswer();
            }
        }




    }

    @NonNull
    @Override
    public ListenPresenter createPresenter() {
        return presenter;
    }


    @Override
    public void showListWord(List<Word> words) {
        this.words = words;
        tvWord.setOnClickListener(v -> {
            int id = getResources().getIdentifier(this.words.get(position).getWord().toLowerCase().replace('-', '_').trim(),
                    "raw", getPackageName());
            AudioPlayer.play(this, id);
        });
        initAnswer();
        tvPoint.setText(point + " đ");
    }

    private void initAnswer() {
        Random random = new Random();
        int ind = random.nextInt(4);
        List<Integer> ids = new ArrayList<>();
        ids.add(words.get(position).getId());
        switch (ind) {
            case 0: {
                one.setText(words.get(position).getWord());
                two.setText(getOtherAnswer(ids));
                three.setText(getOtherAnswer(ids));
                four.setText(getOtherAnswer(ids));
                break;
            }
            case 1: {
                two.setText(words.get(position).getWord());
                one.setText(getOtherAnswer(ids));
                three.setText(getOtherAnswer(ids));
                four.setText(getOtherAnswer(ids));
                break;
            }
            case 2: {
                three.setText(words.get(position).getWord());
                one.setText(getOtherAnswer(ids));
                two.setText(getOtherAnswer(ids));
                four.setText(getOtherAnswer(ids));
                break;
            }
            case 3: {
                four.setText(words.get(position).getWord());
                one.setText(getOtherAnswer(ids));
                two.setText(getOtherAnswer(ids));
                three.setText(getOtherAnswer(ids));
                break;
            }
        }
    }

    private String getOtherAnswer(List<Integer> ids) {
        String res = "";
        Random random = new Random();

        while (true){
            int ind = random.nextInt(words.size());
            if (!ids.contains(words.get(ind).getId())) {
                res = words.get(ind).getWord();
                ids.add(words.get(ind).getId());
                break;
            }
        }

        return res;
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
        if (one.getId() == v.getId()){
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
