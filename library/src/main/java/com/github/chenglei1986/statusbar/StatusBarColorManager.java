package com.github.chenglei1986.statusbar;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class StatusBarColorManager {

    private Activity mActivity;
    private ViewGroup mDecorView;
    private FrameLayout mStatusBarBackground;
    private int mStatusBarHeight;
    private int mActionBarHeight;
    private ViewGroup mContentView;

    public StatusBarColorManager(@NonNull Activity activity) {
        mActivity = activity;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            mDecorView = (ViewGroup) activity.getWindow().getDecorView();
            mContentView = (ViewGroup) mDecorView.findViewById(android.R.id.content);
            if (null == mStatusBarBackground) {
                mStatusBarBackground = new FrameLayout(activity);
            }
            mStatusBarHeight = StatusBarUtil.getStatusHeight(activity);
            mActionBarHeight = StatusBarUtil.getActionBarHeight(activity);
        }
    }

    public void setStatusBarColor(@ColorInt int color, boolean withActionBar) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        mDecorView.removeView(mStatusBarBackground);
        mStatusBarBackground.removeAllViews();
        StatusBarUtil.setTranslucentStatus(mActivity);
        if (ColorUtil.isLightColor(color)) {

            boolean darkMode = false;
            if (BrandUtil.checkBrand(BrandUtil.BRAND.XIAOMI)) {
                darkMode = StatusBarUtil.setMiuiStatusBarIconDarkMode(mActivity, true);
            } else if (BrandUtil.checkBrand(BrandUtil.BRAND.MEIZU)) {
                darkMode = StatusBarUtil.setFlymeStatusBarIconDarkMode(mActivity, true);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (!darkMode) {
                    View scrimView = new View(mActivity);
                    scrimView.setBackground(ScrimUtil.makeCubicGradientScrimDrawable(0xFF000000, 8, Gravity.TOP));
                    mStatusBarBackground.addView(scrimView);
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mDecorView.setSystemUiVisibility(mDecorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }

        } else {
            if (BrandUtil.checkBrand(BrandUtil.BRAND.XIAOMI)) {
                StatusBarUtil.setMiuiStatusBarIconDarkMode(mActivity, false);
            } else if (BrandUtil.checkBrand(BrandUtil.BRAND.MEIZU)) {
                StatusBarUtil.setFlymeStatusBarIconDarkMode(mActivity, false);
            }
        }
        int contentViewPaddingTop = withActionBar ? (mStatusBarHeight + mActionBarHeight) : mStatusBarHeight;
        mContentView.setPadding(mContentView.getPaddingLeft(), contentViewPaddingTop, mContentView.getPaddingRight(), mContentView.getPaddingBottom());
        mStatusBarBackground.setBackgroundColor(color);
        mDecorView.addView(mStatusBarBackground, ViewGroup.LayoutParams.MATCH_PARENT, mStatusBarHeight);
    }

}