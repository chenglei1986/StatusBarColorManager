package com.github.chenglei1986.statusbar.sample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;

import com.github.chenglei1986.statusbar.ColorUtil;
import com.github.chenglei1986.statusbar.StatusBarColorManager;

import java.util.List;

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
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.sample2);
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                List<Palette.Swatch> swatchList = palette.getSwatches();
                for (Palette.Swatch swatch : swatchList) {
                    if (swatch != null) {
                        int color = swatch.getRgb();
                        if (ColorUtil.isLightColor(color)) {
                            mStatusBarColorManager.setStatusBarColor(swatch.getRgb(), true, false);
                            break;
                        }
                    }
                }
            }
        });

    }
}
