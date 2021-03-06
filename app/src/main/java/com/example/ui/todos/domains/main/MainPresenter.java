package com.example.ui.todos.domains.main;


import com.example.ui.todos.db.DBHelper;
import com.example.ui.todos.db.model.Tags;
import com.example.ui.todos.db.model.ToDo;
import com.example.ui.todos.infrastructures.WeatherService;
import com.example.ui.todos.model.weather.response.WeatherResponse;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.example.ui.todos.ultil.ShareKey.APP_ID;


public class MainPresenter extends MvpBasePresenter<MainView> {

    private DBHelper dbHelper;

    private WeatherService weatherService;



    @Inject
    public MainPresenter() {
    }

    protected void getAllToDo() {
        dbHelper.listAllToDo().concatMap((Func1<List<ToDo>, Observable<List<ToDo>>>) toDos -> {
            for (ToDo i: toDos){
                dbHelper.getTag(i.getTagsId()).subscribe(new Observer<Tags>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(Tags tags) {
                        i.setTag(tags);
                    }
                });
            }
            return Observable.just(toDos);
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<ToDo>>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(List<ToDo> toDos) {
                getView().showListTodo(toDos);
            }
        });
    }

    public void deleteToDo(ToDo... toDos) {
        dbHelper.deleteToDo(toDos).observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> getView().notify(aBoolean));
    }

    public void updateToDo(ToDo... toDos) {
        dbHelper.updateToDo(toDos).observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> getView().notify(aBoolean));
    }


    public void setDbHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
        getAllToDo();
    }


    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    protected void getWeatherForcast(){
        weatherService.getForecastByCity("Ho Chi Minh City", APP_ID).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<WeatherResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(" " +e.getMessage());
            }

            @Override
            public void onNext(WeatherResponse weatherResponse) {
                getView().showWeatherForcast(weatherResponse);
            }
        });
    }


}
