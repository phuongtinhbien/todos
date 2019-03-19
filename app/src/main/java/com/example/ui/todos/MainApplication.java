package com.example.ui.todos;

import com.example.ui.todos.db.model.Tags;
import com.example.ui.todos.infrastructures.ApplicationComponent;
import com.example.ui.todos.infrastructures.ApplicationModule;
import com.example.ui.todos.infrastructures.DaggerApplicationComponent;
import com.example.ui.todos.infrastructures.DaggerNetComponent;
import com.example.ui.todos.infrastructures.DaggerWeatherComponent;
import com.example.ui.todos.infrastructures.NetComponent;
import com.example.ui.todos.infrastructures.NetModule;
import com.example.ui.todos.infrastructures.WeatherComponent;
import com.example.ui.todos.infrastructures.WeatherModule;
import com.example.ui.todos.ultil.Date;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;

import net.hockeyapp.android.CrashManager;

import org.androidannotations.annotations.EApplication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.multidex.MultiDexApplication;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


@EApplication
public class MainApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;

    private WeatherComponent weatherComponent;

    private NetComponent netComponent;
    private static final String END_POINT_WEATHER = "http://api.openweathermap.org";

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidThreeTen.init(this);
        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(this)).build();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/weathericons.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setApplicationComponent(DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build());

        setNetComponent(DaggerNetComponent
                .builder()
                .netModule(new NetModule(END_POINT_WEATHER))
                .build());

        setWeatherComponent(DaggerWeatherComponent
                .builder().netComponent(netComponent)
                .weatherModule(new WeatherModule(netComponent.retrofit())).build());

        generateTags();
        checkForCrashes();
    }


    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    public WeatherComponent getWeatherComponent() {
        return weatherComponent;
    }

    public void setWeatherComponent(WeatherComponent weatherComponent) {
        this.weatherComponent = weatherComponent;
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

    public void setNetComponent(NetComponent netComponent) {
        this.netComponent = netComponent;
    }

    private void checkForCrashes() {
        CrashManager.register(this);
    }

    private void generateTags (){
        System.out.println("GENERATE");
        getApplicationComponent().dbHelper().listAllTags().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Tags>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<Tags> toDos) {
                if (toDos.size()== 0){
                    List<Tags> tagsList = new ArrayList<>();
                    tagsList.add(new Tags(Date.getTime(),"Normal", R.drawable.ic_normal));
                    tagsList.add(new Tags(Date.getTime(),"Shopping", R.drawable.ic_shopping));
                    tagsList.add(new Tags(Date.getTime(),"Work", R.drawable.ic_work));
                    tagsList.add(new Tags(Date.getTime(),"Coffee", R.drawable.ic_coffee));
                    tagsList.add(new Tags(Date.getTime(),"Transport", R.drawable.ic_transport));
                    tagsList.add(new Tags(Date.getTime(),"event", R.drawable.ic_event));
                    for (Tags i: tagsList) {
                        getApplicationComponent().dbHelper().saveTags(i).subscribe();
                    }
                }

            }
        });


    }

}
