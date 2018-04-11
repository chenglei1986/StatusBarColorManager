package com.github.chenglei1986.statusbar.sample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.chenglei1986.statusbar.StatusBarColorManager;

public class LayoutFullscreenSample2 extends AppCompatActivity {

    private StatusBarColorManager mStatusBarColorManager;

    public static void actionLayoutFullscreenSample2(Context context) {
        Intent intent = new Intent(context, LayoutFullscreenSample2.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_fullscreen_sample2);
        mStatusBarColorManager = new StatusBarColorManager(this);
        mStatusBarColorManager.setStatusBarColor(Color.WHITE, true, false);
    }
}
