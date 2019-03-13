package com.example.ui.todos.domains.main;

import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.ui.todos.R;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public final ActivityTestRule<MainActivity_> main = new ActivityTestRule<>(MainActivity_.class);


    @Test
    public void showListTodo() {
        Espresso.onView(withId(R.id.view_pager)).check(matches(ViewMatchers.hasMinimumChildCount(1)));
    }

    @Test
    public void notifyTest() {
    }

    @Test
    public void showWeatherForcast() {
        Espresso.onView(withId(R.id.activity_main_tv_weather)).check(matches(isDisplayed()));
    }
}