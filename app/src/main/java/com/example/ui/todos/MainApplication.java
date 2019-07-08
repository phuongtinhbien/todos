package com.example.ui.todos;

import android.content.SharedPreferences;

import androidx.multidex.MultiDexApplication;

import com.example.ui.todos.db.model.CodeTest;
import com.example.ui.todos.db.model.Tags;
import com.example.ui.todos.db.model.Test;
import com.example.ui.todos.db.model.Word;
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
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static com.example.ui.todos.ultil.ShareKey.END_POINT_WEATHER;
import static com.example.ui.todos.ultil.ShareKey.LANG_KEY;
import static com.example.ui.todos.ultil.ShareKey.THEME_KEY;
import static com.example.ui.todos.ultil.ShareKey.TODO_SHARE_KEY;


@EApplication
public class MainApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;

    private WeatherComponent weatherComponent;

    private NetComponent netComponent;


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidThreeTen.init(this);
        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(this)).build();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/weathericons.ttf")
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
        initSharePreference();
        configTheme();
        generateWord();
        generateCodeTest();
        generateTest();
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

    private void generateTags() {
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
                        if (toDos.size() == 0) {
                            List<Tags> tagsList = new ArrayList<>();
                            tagsList.add(new Tags(Date.getTime(), "Normal", R.drawable.ic_normal));
                            tagsList.add(new Tags(Date.getTime(), "Shopping", R.drawable.ic_shopping));
                            tagsList.add(new Tags(Date.getTime(), "Work", R.drawable.ic_work));
                            tagsList.add(new Tags(Date.getTime(), "Coffee", R.drawable.ic_coffee));
                            tagsList.add(new Tags(Date.getTime(), "Transport", R.drawable.ic_transport));
                            tagsList.add(new Tags(Date.getTime(), "Event", R.drawable.ic_event));
                            for (Tags i : tagsList) {
                                getApplicationComponent().dbHelper().saveTags(i).subscribe();
                            }
                        }

                    }
                });


    }

    private void initSharePreference() {
        preferences = getSharedPreferences(TODO_SHARE_KEY, MODE_PRIVATE);
        editor = preferences.edit();
        if (preferences.getInt(THEME_KEY, -1) != -1) {
            editor.putInt(THEME_KEY, R.style.AppTheme_NoActionBar);
        }
        preferences.registerOnSharedPreferenceChangeListener((sharedPreferences, key) -> {
            if (key.equals(THEME_KEY)) {
                configTheme();
            }
            if (key.equals(LANG_KEY)) {
            }
        });
    }

    private void configTheme() {
        setTheme(preferences.getInt(THEME_KEY, R.style.AppTheme_NoActionBar));

    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    private void generateWord() {
        System.out.println("GENERATE");
        getApplicationComponent().dbHelper().listAllWord().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Word>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Word> toDos) {
                        if (toDos.size() == 0) {
                            List<Word> tagsList = new ArrayList<>();
                            tagsList.add(new Word("Access", "Truy cập", "(v)", "ˈakˌses"));
                            tagsList.add(new Word("Address", "Địa chỉ", "(n)", "əˈdres"));
                            tagsList.add(new Word("Arithmetic", "Số học", "(n)", "əˈrɪθ.mə.tɪk"));
                            tagsList.add(new Word("Cartridge", "Đầu quay đĩa", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Channel", "Kênh", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Circuit", "Mạch", "(a)", "ˈakˌses"));
                            tagsList.add(new Word("Compiler", "Trình biên dịch", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Component", "Thành phần", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Computer", "máy tính", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Computerize ", "Tin học hóa", "(v)", "ˈakˌses"));
                            tagsList.add(new Word("Conceptual", "Dung lượng", "(a)", "ˈakˌses"));
                            tagsList.add(new Word("Convert", "Chuyển đổi", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Data", "Dữ liệu", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Demagnetize", "Khử từ hóa", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Device", "Thiết bị", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Electronic", "Điện tử", "(n,a)", "ˈakˌses"));
                            tagsList.add(new Word("Equal", "Bằng", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Feature", "Thuộc tính", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Function", "Hàm", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Fundamental", "Cơ bản", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Gateway", "Cổng kết nối internet", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Input", "Đầu vào", "(v,n)", "ˈakˌses"));
                            tagsList.add(new Word("Instruction", "Thiết bị", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Device", "Chỉ dẫn", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Multiplexor", "Bộ dồn kênh", "(n)", "ˈakˌses"));
                            tagsList.add(new Word("Memory", "Bộ nhớ", "(n)", "ˈakˌses"));
                            for (Word i : tagsList) {
                                getApplicationComponent().dbHelper().saveWord(i).subscribe();
                            }
                        }

                    }
                });


    }

    private void generateCodeTest() {
        System.out.println("GENERATE");
        getApplicationComponent().dbHelper().listAllCodeTest().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CodeTest>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<CodeTest> toDos) {
                        if (toDos.size() == 0) {
                            List<CodeTest> tagsList = new ArrayList<>();
                            for (int i = 0; i < 5; i++) {
                                tagsList.add(new CodeTest("Bộ đề " + (i + 1), "CODE_" + (i + 1)));
                            }

                            for (CodeTest i : tagsList) {
                                getApplicationComponent().dbHelper().saveCodeTest(i).subscribe();
                            }
                        }

                    }
                });
    }

    private void generateTest(){
        getApplicationComponent().dbHelper().listAllCodeTest().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CodeTest>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<CodeTest> toDos) {
                        if (toDos.size() == 0) {
                            List<Test> tagsList = new ArrayList<>();
                            tagsList.add(new Test("CODE_1","Từ nào sau đây là nghĩa tiếng việt của hệ điều hành"
                                    ,"Operating system",
                                    "Opeting system",
                                    "Operatong systems",
                                    "Operirting system",
                                    "Operating system"));
                            tagsList.add(new Test("CODE_1","Từ nào sau đây chỉ đa người dùng"
                                    ,"Mullti-user",
                                    "Multity-user",
                                    "Multi-user",
                                    "Multitaly-user",
                                    "Mullti-user"));
                            tagsList.add(new Test("CODE_1",
                                    "Nghĩa tiếng anh nào sau đây chỉ dữ liệu kiểu số trong công nghệ thông tin"
                                    ,"Alphenumeric database",
                                    "Allphanumeric data",
                                    "Alphanstiumeric data",
                                    "Alphanumeric data",
                                    "Alphanumeric data"));
                            tagsList.add(new Test("CODE_1",
                                    "Từ tiếng anh nào mang nghĩa bộ điều khiển trùm"
                                    ,"Clustery controler",
                                    "Cluster controller",
                                    "Clusters controler",
                                    "Closter controller",
                                    "Cluster controller"));
                            tagsList.add(new Test("CODE_1","Từ nào sau đây là nghĩa cổng kết nối internet cho những mạng lớn"
                                    ,"Gateway","Gaterway","Geteway","Gaterways","Gateway"));
                            tagsList.add(new Test("CODE_1","Gói dữ liệu được viết như sau trong công nghệ trong tin"
                                    ,"Pascket","Packeter","Packet","Pecket","Packet"));
                            tagsList.add(new Test("CODE_1","Đâu là tên tiếng anh của từ mã nguồn"
                                    ,"Sources Code","Source Code","Souresce Code","Surce Code","Source Code"));
                            tagsList.add(new Test("CODE_1","Cổng trong tiếng anh chuyên ngành công nghệ thông tin được viết như thế nào"
                                    ,"Ports","Porter","Port","Porty","Port"));
                            tagsList.add(new Test("CODE_1","Từ nào sau đây chỉ ra công tác biên mục theo chuyên ngành công nghệ thông tin"
                                    ,"Catelogong","Caterloging","Cataloging","Catalogrings","Cataloging"));
                            tagsList.add(new Test("CODE_1","Từ nào sau đây mang nghĩa thẻ chủ đề"
                                    ,"Subsject entry","Subjected entry","Subject entry","Subject entrys","Subject entry"));
                            for (Test i : tagsList) {
                                getApplicationComponent().dbHelper().saveTest(i).subscribe();
                            }
                        }

                    }
                });
    }


}
