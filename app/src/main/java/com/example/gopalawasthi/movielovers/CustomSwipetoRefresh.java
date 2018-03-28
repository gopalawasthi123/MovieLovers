package com.example.gopalawasthi.movielovers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by Gopal Awasthi on 27-03-2018.
 */

public class CustomSwipetoRefresh extends SwipeRefreshLayout {

    private  int mtouchslope;
    private float mprevX;
    public CustomSwipetoRefresh(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mtouchslope = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mprevX = MotionEvent.obtain(ev).getX();
                break;
                case MotionEvent.ACTION_MOVE:
                    final float eventX =ev.getX();
                    mprevX = MotionEvent.obtain(ev).getX();
                    float Xdiff = Math.abs(eventX-mprevX);

                    if(Xdiff > mtouchslope){
                        return false;
                    }
                    break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}