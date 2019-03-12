package com.example.ui.todos.domains.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.example.ui.todos.MainApplication;
import com.example.ui.todos.R;
import com.example.ui.todos.db.model.ToDo;
import com.example.ui.todos.domains.base.BaseActivity;
import com.example.ui.todos.domains.createTask.CreateTaskActivity_;
import com.example.ui.todos.model.weather.Weather;
import com.example.ui.todos.model.weather.response.WeatherResponse;
import com.google.android.material.button.MaterialButton;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;


@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @App
    MainApplication application;
    @Inject
    MainPresenter presenter;
    @ViewById(R.id.view_pager)
    CustomViewPager viewPager;
    @ViewById(R.id.actitvity_main_btn_create_new_task)
    MaterialButton createTask;

    @ViewById(R.id.activity_main_tv_date)
    TextView date;

    @ViewById(R.id.activity_main_tv_sub_date)
    TextView subDate;

    @ViewById(R.id.activity_main_tv_weather)
    TextView weather;

    @ViewById(R.id.activity_main_tv_sub_weather)
    TextView subWeather;

    @AfterInject
    void inject() {
        DaggerMainComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .build()
                .inject(this);
        presenter.setDbHelper(application.getApplicationComponent().dbHelper());
        presenter.getAllToDo();
        presenter.setWeatherService(application.getWeatherComponent().weatherService());
        presenter.getWeatherForcast();

    }

    @SuppressLint("ClickableViewAccessibility")
    @AfterViews
    void init() {
        createTask.setOnClickListener(v -> startActivity(new Intent(this, CreateTaskActivity_.class)));
        date.setText(DateUtils.formatDateTime(this, Calendar.getInstance().getTimeInMillis(),DateUtils.FORMAT_SHOW_WEEKDAY));
        subDate.setText(DateUtils.formatDateTime(this, Calendar.getInstance().getTimeInMillis(),DateUtils.FORMAT_SHOW_DATE));
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void showListTodo(List<ToDo> toDos) {
        viewPager.setAdapter(new CustomPageAdpater(toDos, this));
        viewPager.setPageTransformer(true, viewPager);
        viewPager.invalidate();
        if (toDos == null || toDos.size() == 0) {
            createTask.setVisibility(View.GONE);
        } else {
            createTask.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void notify(boolean success) {
        hideProgressLoading();

    }

    @Override
    public void showWeatherForcast(WeatherResponse w) {
        System.out.println(w.toString());
        String wea = getResources().getString(R.string.weather_cloudy);
        this.weather.setText(w.getMain().getTemp() + " - " + wea);
        this.subWeather.setText(w.getWeather().get(0).getMain());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getAllToDo();
    }
}
