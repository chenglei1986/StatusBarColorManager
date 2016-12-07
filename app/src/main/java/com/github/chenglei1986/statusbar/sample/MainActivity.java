package com.github.chenglei1986.statusbar.sample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.github.chenglei1986.statusbar.ColorUtil;
import com.github.chenglei1986.statusbar.StatusBarColorManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private StatusBarColorManager mStatusBarColorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.random_color_button).setOnClickListener(this);

        mStatusBarColorManager = new StatusBarColorManager(this);
    }

    @Override
    public void onClick(View view) {
        int color = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        mStatusBarColorManager.setStatusBarColor(0xFF333333, true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF333333));
    }
}
