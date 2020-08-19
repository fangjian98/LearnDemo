package com.example.learndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class KeyeventActivity extends AppCompatActivity {

    public static String TAG = "KeyEvent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyevent);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d(TAG, "activity dispatchKeyEvent is called" + event.getAction());
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "activity onKeyDown is called" + event.getAction());
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            Toast.makeText(KeyeventActivity.this, "音量减小", Toast.LENGTH_SHORT).show();
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d(TAG, "activity onKeyUp is called" + event.getAction());
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Log.d(TAG, "activity onKeyLongPress is called" + event.getAction());
        return super.onKeyLongPress(keyCode, event);
    }
}