package com.example.helloandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class MyButton extends androidx.appcompat.widget.AppCompatButton {
    private static final String TAG = "MyButton";

    public MyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
//        Toast.makeText(getContext(), "调用onKeyDown()", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "调用onKeyDown()");
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
//        Toast.makeText(getContext(), "调用onKeyUp()", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "调用onKeyUp()");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
//        Toast.makeText(getContext(), "调用onTouchEvent()", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "调用onTouchEvent()");
        return true;
    }

}
