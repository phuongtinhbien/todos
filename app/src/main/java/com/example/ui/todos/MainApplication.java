package com.example.ui.todos;

import com.example.ui.todos.infrastructures.ApplicationComponent;
import com.example.ui.todos.infrastructures.ApplicationModule;
import com.example.ui.todos.infrastructures.DaggerApplicationComponent;
import com.example.ui.todos.infrastructures.DaggerNetComponent;
import com.example.ui.todos.infrastructures.DaggerWeatherComponent;
import com.example.ui.todos.infrastructures.NetComponent;
import com.example.ui.todos.infrastructures.NetModule;
import com.example.ui.todos.infrastructures.WeatherComponent;
import com.example.ui.todos.infrastructures.WeatherModule;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;

import net.hockeyapp.android.CrashManager;

import org.androidannotations.annotations.EApplication;

import androidx.multidex.MultiDexApplication;


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
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/Montserrat-Regular.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );

        setApplicationComponent(DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build());
        setNetComponent(DaggerNetComponent
                .builder()
                .netModule(new NetModule(END_POINT_WEATHER))
                .build());
        setWeatherComponent(DaggerWeatherComponent
                .builder()
                .netComponent(netComponent)
                .weatherModule(new WeatherModule(netComponent.retrofit())).build());
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

}
