package com.example.xyz.screen.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by Lenovo on 2016/10/14.
 */
public class CustomView extends TextView{

    private static final String TAG = "CustomView";

    private int mLastX;
    private int mLastY;

    private Scroller scroller = null;

    CustomView(Context context){
        this(context, null);
    }

    CustomView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    CustomView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int)event.getRawX();
        int y = (int)event.getRawY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
//                Log.d(TAG, "move, deltaX" + deltaX + " deltaY" + deltaY);
//                int translationX = (int) getTranslationX() + deltaX;
//                int translationY = (int) getTranslationY() + deltaY;
//
//                setTranslationX(translationX);
//                setTranslationY(translationY);
                Log.d(TAG, "move, X" + x + " Y" + y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }

    private void smoothScrollTo(int destX, int destY){
        int scrollX = getScrollX();
        int deltaX = destX - scrollX;
        scroller.startScroll(scrollX, 0, deltaX, 0, 10);
        invalidate();
    }

    @Override
    public void computeScroll() {
//        super.computeScroll();
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }
}
