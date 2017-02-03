package com.github.chenglei1986.statusbar;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class StatusBarColorManager {

    private interface StatusBarColorManagerImpl {
        void setStatusBarColor(@ColorInt int color, boolean layoutFullscreen, boolean withActionBar);
    }

    private static class StatusBarColorManagerBase implements StatusBarColorManagerImpl {

        @Override
        public void setStatusBarColor(@ColorInt int color, boolean layoutFullscreen, boolean withActionBar) {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static class StatusBarColorManagerImplApi19 extends StatusBarColorManagerBase {

        protected Activity mActivity;
        protected ViewGroup mDecorView;
        protected FrameLayout mStatusBarBackground;
        protected int mStatusBarHeight;
        protected int mActionBarHeight;
        protected ViewGroup mContentView;

        public StatusBarColorManagerImplApi19(@NonNull Activity activity) {
            mActivity = activity;
            StatusBarUtil.setTranslucentStatus(mActivity);

            mDecorView = (ViewGroup) activity.getWindow().getDecorView();
            mContentView = (ViewGroup) ((ViewGroup) mDecorView.findViewById(android.R.id.content)).getChildAt(0);
            mStatusBarHeight = StatusBarUtil.getStatusHeight(activity);
            mActionBarHeight = StatusBarUtil.getActionBarHeight(activity);
            if (null == mStatusBarBackground) {
                mStatusBarBackground = new FrameLayout(activity);
                mDecorView.addView(mStatusBarBackground, ViewGroup.LayoutParams.MATCH_PARENT, mStatusBarHeight);
            }
        }

        protected void setStatusBarColorLight(@ColorInt int color, boolean layoutFullscreen, boolean withActionBar) {
            setColorAndContentPadding(color, layoutFullscreen, withActionBar, true);
        }

        protected void setStatusBarColorDark(@ColorInt int color, boolean layoutFullscreen, boolean withActionBar) {
            setColorAndContentPadding(color, layoutFullscreen, withActionBar, false);
        }

        private void setColorAndContentPadding(@ColorInt int color, boolean layoutFullscreen, boolean withActionBar, boolean light) {
            if (BrandUtil.checkBrand(BrandUtil.BRAND.XIAOMI)) {
                StatusBarUtil.setMiuiStatusBarIconDarkMode(mActivity, light);
            } else if (BrandUtil.checkBrand(BrandUtil.BRAND.MEIZU)) {
                StatusBarUtil.setFlymeStatusBarIconDarkMode(mActivity, light);
            }
            int contentViewPaddingTop = (layoutFullscreen ? 0 : mStatusBarHeight) + (withActionBar ? mActionBarHeight : 0);
            mContentView.setPadding(mContentView.getPaddingLeft(), contentViewPaddingTop, mContentView.getPaddingRight(), mContentView.getPaddingBottom());
            mStatusBarBackground.setBackgroundColor(layoutFullscreen ? 0x00000000 : color);
        }

        @Override
        public void setStatusBarColor(@ColorInt int color, boolean layoutFullscreen, boolean withActionBar) {
            if (ColorUtil.isLightColor(color)) {
                setStatusBarColorLight(color, layoutFullscreen, withActionBar);
            } else {
                setStatusBarColorDark(color, layoutFullscreen, withActionBar);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static class StatusBarColorManagerImplApi21 extends StatusBarColorManagerImplApi19 {

        public StatusBarColorManagerImplApi21(@NonNull Activity activity) {
            super(activity);
        }

        @Override
        public void setStatusBarColor(@ColorInt int color, boolean layoutFullscreen, boolean withActionBar) {
            mStatusBarBackground.removeAllViews();
            if (ColorUtil.isLightColor(color)) {
                setStatusBarColorLight(color, layoutFullscreen, withActionBar);
                View scrimView = new View(mActivity);
                scrimView.setBackground(ScrimUtil.makeCubicGradientScrimDrawable(0xFF000000, 8, Gravity.TOP));
                mStatusBarBackground.addView(scrimView);
            } else {
                setStatusBarColorDark(color, layoutFullscreen, withActionBar);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static class StatusBarColorManagerImplApi23 extends StatusBarColorManagerImplApi21 {

        public StatusBarColorManagerImplApi23(@NonNull Activity activity) {
            super(activity);
        }

        @Override
        public void setStatusBarColor(@ColorInt int color, boolean layoutFullscreen, boolean withActionBar) {
            mStatusBarBackground.removeAllViews();
            if (ColorUtil.isLightColor(color)) {
                setStatusBarColorLight(color, layoutFullscreen, withActionBar);
                mDecorView.setSystemUiVisibility(mDecorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                setStatusBarColorDark(color, layoutFullscreen, withActionBar);
                mDecorView.setSystemUiVisibility(mDecorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    private StatusBarColorManagerImpl mStatusBarColorManagerImpl;

    public StatusBarColorManager(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mStatusBarColorManagerImpl = new StatusBarColorManagerImplApi23(activity);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mStatusBarColorManagerImpl = new StatusBarColorManagerImplApi21(activity);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mStatusBarColorManagerImpl = new StatusBarColorManagerImplApi19(activity);
        } else {
            mStatusBarColorManagerImpl = new StatusBarColorManagerBase();
        }
    }

    public void setStatusBarColor(@ColorInt int color, boolean layoutFullscreen, boolean withActionBar) {
        mStatusBarColorManagerImpl.setStatusBarColor(color, layoutFullscreen, withActionBar);
    }

}
