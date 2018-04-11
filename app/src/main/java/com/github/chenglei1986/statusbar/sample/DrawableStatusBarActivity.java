package com.github.chenglei1986.statusbar.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.chenglei1986.statusbar.StatusBarColorManager;

public class DrawableStatusBarActivity extends AppCompatActivity {

    private StatusBarColorManager mStatusBarColorManager;

    public static void actionDrawableStatusBar(Context context) {
        Intent intent = new Intent(context, DrawableStatusBarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_status_bar);
        mStatusBarColorManager = new StatusBarColorManager(this);
        mStatusBarColorManager.setStatusBarBackground(R.drawable.bg, false, true);
    }
}
