package com.example.ui.todos.domains.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager implements ViewPager.PageTransformer {

    private FixedSpeedScroller mScroller = null;
    private boolean enabled;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onTouchEvent(event);
        }
        init();
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private void init() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            mScroller = new FixedSpeedScroller(getContext(),
                    new DecelerateInterpolator());
            scroller.set(this, mScroller);
        } catch (Exception ignored) {
        }
    }


    public void setScrollDuration(int duration) {
        mScroller.setScrollDuration(duration);
    }

    @Override
    public void transformPage(@NonNull View view, float v) {
        float rotation = 30f *((1+v)*v) ;
        float rotationReverse = 30f * (v-1)*-v;

        view.setRotationY(v<=0? rotation:rotationReverse);
        view.setTranslationY(v<=0?20.0f*v: 0);
        view.setTranslationZ(v<=0? 20f*v: 20f*v);
        view.setTranslationY(v>=0?10f*v:0.0f*v);

        setScrollDuration((int) (Math.abs(v) * 1000));
    }



    private class FixedSpeedScroller extends Scroller {

        private int mDuration = 500;

        public FixedSpeedScroller(Context context, DecelerateInterpolator decelerateInterpolator) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setScrollDuration(int duration) {
            mDuration = duration;
        }
    }


}