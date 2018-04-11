package com.github.chenglei1986.statusbar.sample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.chenglei1986.statusbar.StatusBarColorManager;

public class ColoredStatusBarActivity extends AppCompatActivity {

    private static final String EXTRA_STATUS_BAR_COLOR = "extra_status_bar_color";

    private StatusBarColorManager mStatusBarColorManager;

    public static void actionColoredStatusBar(Context context, @ColorInt int statusBarColor) {
        Intent intent = new Intent(context, ColoredStatusBarActivity.class);
        intent.putExtra(EXTRA_STATUS_BAR_COLOR, statusBarColor);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colored_status_bar);
        mStatusBarColorManager = new StatusBarColorManager(this);

        int statusBarColor = getIntent().getIntExtra(EXTRA_STATUS_BAR_COLOR, Color.BLACK);
        mStatusBarColorManager.setStatusBarColor(statusBarColor, false, true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(statusBarColor));

        findViewById(R.id.randomColor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
                mStatusBarColorManager.setStatusBarColor(color, false, true);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
            }
        });
    }
}
