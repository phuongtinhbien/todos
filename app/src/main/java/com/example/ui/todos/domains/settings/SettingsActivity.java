package com.example.ui.todos.domains.settings;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ui.todos.MainApplication;
import com.example.ui.todos.R;
import com.example.ui.todos.domains.base.BaseActivity;
import com.example.ui.todos.domains.tags.TagsActivity_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

@EActivity(R.layout.activity_settings)
public class SettingsActivity extends BaseActivity<SettingView, SettingsPresenter>
        implements SettingView {

    @App
    MainApplication application;

    @Inject
    SettingsPresenter presenter;

    @ViewById(R.id.activity_settings_content_tags)
    CardView itemTags;

    @ViewById(R.id.activity_settings_content_config)
    CardView itemConfig;

    @AfterInject
    void inject() {
        DaggerSettingsComponent.builder()
                .applicationComponent(application.getApplicationComponent())
                .build()
                .inject(this);
    }

    @AfterViews
    void init() {
        initItem(itemTags, R.drawable.ic_tags, "All Tags", "Manage all your created tags", "");
        initItem(itemConfig, R.drawable.ic_settings, "Configuration", "Change color, theme, language,..", "");
    }

    @Click(R.id.activity_settings_content_tags)
    void clickTags() {
        startActivity(new Intent(this, TagsActivity_.class));
    }

    @NonNull
    @Override
    public SettingsPresenter createPresenter() {
        return this.presenter;
    }

    private void initItem(View v, int idIcon, String titleText, String subTitleText, String timeText) {
        TextView title, subTitle, time;
        ImageView icon;
        icon = v.findViewById(R.id.imageView2);
        title = v.findViewById(R.id.textView);
        subTitle = v.findViewById(R.id.textView2);
        time = v.findViewById(R.id.textView3);
        //set value
        icon.setImageResource(idIcon);
        icon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        title.setText(titleText);
        subTitle.setText(subTitleText);
        time.setText(timeText);
    }
}
