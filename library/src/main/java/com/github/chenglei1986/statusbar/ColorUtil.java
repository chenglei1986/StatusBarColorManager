package com.github.chenglei1986.statusbar;

import android.graphics.Color;
import android.support.annotation.ColorInt;

public class ColorUtil {

    public static boolean isLightColor(@ColorInt int color) {
        return Color.red(color) * 0.299 + Color.green(color) * 0.587 + Color.blue(color) * 0.114 > 160;
    }

}
