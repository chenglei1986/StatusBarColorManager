package com.github.chenglei1986.statusbar;

import android.os.Build;
import android.support.annotation.NonNull;

/**
 * 判断手机品牌的工具类
 */
public final class BrandUtil {

    /**
     * 常见品牌
     */
    public enum BRAND {
        SAMSUNG,            // 三星
        HTC,                // HTC
        SONY,               // 索尼
        LG,                 // LG
        XIAOMI,             // 小米
        HUAWEI,             // 华为
        MEIZU,              // 魅族
        MOTOROLA,           // 摩托罗拉
        ZTE,                // 中兴
        COOLPAD,            // 酷派
        LENOVO,             // 联想
        OPPO,               // OPPO
        VIVO,               // 步步高VIVO
        GIONEE,             // 金立
        SMARTISAN,          // 锤子科技
    }

    private BrandUtil() {

    }

    /**
     * 判断当前设备是否为指定品牌
     *
     * @param brand 品牌 {@link BRAND}
     * @return
     */
    public static boolean checkBrand(@NonNull BRAND brand) {
        if (Build.MANUFACTURER != null && Build.MANUFACTURER.toUpperCase().contains(brand.toString())) {
            return true;
        } else if (Build.BRAND != null && Build.BRAND.toUpperCase().contains(brand.toString())) {
            return true;
        } else if (Build.MODEL != null && Build.MODEL.toUpperCase().contains(brand.toString())) {
            return true;
        } else {
            return false;
        }
    }

}
