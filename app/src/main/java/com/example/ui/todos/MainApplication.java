package com.example.ui.todos;
import com.example.ui.todos.infrastructures.ApplicationComponent;
import com.example.ui.todos.infrastructures.ApplicationModule;
import com.example.ui.todos.infrastructures.DaggerApplicationComponent;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;

import org.androidannotations.annotations.EApplication;

import androidx.multidex.MultiDexApplication;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


@EApplication
public class MainApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;

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

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

}
