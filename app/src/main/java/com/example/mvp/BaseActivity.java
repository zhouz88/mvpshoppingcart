package com.example.mvp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        Window w = getWindow();
        w.setStatusBarColor(Color.TRANSPARENT);
        initViews();
    }

    protected abstract void initViews();

    protected <T extends View> T find(@IdRes int id) {
        return findViewById(id);
    }
}
