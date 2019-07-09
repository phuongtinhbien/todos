package com.example.ui.todos.domains.main;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class ProvideAnimation {

    private Context context;
    Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);


    public ProvideAnimation(Context context) {
        this.context = context;
    }

}
