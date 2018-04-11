package com.github.chenglei1986.statusbar;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class StatusBarColorManager {

    private interface StatusBarColorManagerImpl {

        void setStatusBarColor(@ColorInt int color, boolean layoutFullscreen, boolean withActionBar);

        void setStatusBarColorRes(@ColorRes int colorRes, boolean layoutFullscreen, boolean withActionBar);

        void setStatusBarBackground(@DrawableRes int drawableRes, boolean layoutFullscreen, boolean withActionBar);

        void setStatusBarBackground(Drawable drawable, boolean layoutFullscreen, boolean withActionBar);

        void setLightStatusBar(boolean light);

    }

    private static class StatusBarColorManagerBase implements StatusBarColorManagerImpl {

        @Override
        public void setStatusBarColor(@ColorInt int color, boolean layoutFullscreen, boolean withActionBar) {

        }

        @Override
        public void setStatusBarColorRes(int colorRes, boolean layoutFullscreen, boolean withActionBar) {

        }

        @Override
        public void setStatusBarBackground(int drawableRes, boolean layoutFullscreen, boolean withActionBar) {

        }

        @Override
        public void setStatusBarBackground(Drawable drawable, boolean layoutFullscreen, boolean withActionBar) {

        }

        @Override
        public void setLightStatusBar(boolean light) {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
            if (null == mContentView) {
                throw new IllegalStateException("****** You must initialize StatusBarColorManager after setContentView() ******");
            }
            mStatusBarHeight = StatusBarUtil.getStatusHeight(activity);
            mActionBarHeight = StatusBarUtil.getActionBarHeight(activity);
            if (null == mStatusBarBackground) {
                mStatusBarBackground = new FrameLayout(activity);
                mDecorView.addView(mStatusBarBackground, ViewGroup.LayoutParams.MATCH_PARENT, mStatusBarHeight);
            }
        }

        protected void setColor(@ColorInt int color, boolean layoutFullscreen) {
            boolean light = ColorUtil.isLightColor(color);
            setLightStatusBar(light);
            mStatusBarBackground.setBackgroundColor(layoutFullscreen ? 0x00000000 : color);
        }

        protected void setColorRes(@ColorRes int colorRes, boolean layoutFullscreen) {
            int color;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                color = mActivity.getColor(colorRes);
            } else {
                color = mActivity.getResources().getColor(colorRes);
            }
            boolean light = ColorUtil.isLightColor(color);
            setLightStatusBar(light);
            mStatusBarBackground.setBackgroundColor(layoutFullscreen ? 0x00000000 : color);
        }

        protected void setDrawable(Drawable drawable) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                mStatusBarBackground.setBackground(drawable);
            } else {
                mStatusBarBackground.setBackgroundDrawable(drawable);
            }
        }

        protected void setDrawableRes(@DrawableRes int drawableRes) {
            mStatusBarBackground.setBackgroundResource(drawableRes);
        }

        protected void setContentPadding(boolean layoutFullscreen, boolean withActionBar) {
            int contentViewPaddingTop = (layoutFullscreen ? 0 : mStatusBarHeight) + (withActionBar ? mActionBarHeight : 0);
            mContentView.setPadding(mContentView.getPaddingLeft(), contentViewPaddingTop, mContentView.getPaddingRight(), mContentView.getPaddingBottom());
        }

        @Override
        public void setStatusBarColor(@ColorInt int color, boolean layoutFullscreen, boolean withActionBar) {
            setColor(color, layoutFullscreen);
            setContentPadding(layoutFullscreen, withActionBar);
        }

        @Override
        public void setStatusBarColorRes(int colorRes, boolean layoutFullscreen, boolean withActionBar) {
            setColorRes(colorRes, layoutFullscreen);
            setContentPadding(layoutFullscreen, withActionBar);
        }

        @Override
        public void setStatusBarBackground(int drawableRes, boolean layoutFullscreen, boolean withActionBar) {
            setDrawableRes(drawableRes);
            setContentPadding(layoutFullscreen, withActionBar);
        }

        @Override
        public void setStatusBarBackground(Drawable drawable, boolean layoutFullscreen, boolean withActionBar) {
            setDrawable(drawable);
            setContentPadding(layoutFullscreen, withActionBar);
        }

        @Override
        public void setLightStatusBar(boolean light) {
            if (BrandUtil.checkBrand(BrandUtil.BRAND.XIAOMI)) {
                StatusBarUtil.setMiuiStatusBarIconDarkMode(mActivity, light);
            } else if (BrandUtil.checkBrand(BrandUtil.BRAND.MEIZU)) {
                StatusBarUtil.setFlymeStatusBarIconDarkMode(mActivity, light);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static class StatusBarColorManagerImplApi21 extends StatusBarColorManagerImplApi19 {

        public StatusBarColorManagerImplApi21(@NonNull Activity activity) {
            super(activity);
        }

        @Override
        public void setLightStatusBar(boolean light) {
            if (BrandUtil.checkBrand(BrandUtil.BRAND.XIAOMI)) {
                StatusBarUtil.setMiuiStatusBarIconDarkMode(mActivity, light);
            } else if (BrandUtil.checkBrand(BrandUtil.BRAND.MEIZU)) {
                StatusBarUtil.setFlymeStatusBarIconDarkMode(mActivity, light);
            } else {
                mStatusBarBackground.removeAllViews();
                if (light) {
                    View scrimView = new View(mActivity);
                    scrimView.setBackground(ScrimUtil.makeCubicGradientScrimDrawable(0xFF000000, 8, Gravity.TOP));
                    mStatusBarBackground.addView(scrimView);
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static class StatusBarColorManagerImplApi23 extends StatusBarColorManagerImplApi21 {

        public StatusBarColorManagerImplApi23(@NonNull Activity activity) {
            super(activity);
        }

        @Override
        public void setLightStatusBar(boolean light) {
            if (BrandUtil.checkBrand(BrandUtil.BRAND.XIAOMI)) {
                StatusBarUtil.setMiuiStatusBarIconDarkMode(mActivity, light);
            } else if (BrandUtil.checkBrand(BrandUtil.BRAND.MEIZU)) {
                StatusBarUtil.setFlymeStatusBarIconDarkMode(mActivity, light);
            }
            if (light) {
                mDecorView.setSystemUiVisibility(mDecorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
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

    public void setStatusBarColorRes(@ColorRes int colorRes, boolean layoutFullscreen, boolean withActionBar) {
        mStatusBarColorManagerImpl.setStatusBarColorRes(colorRes, layoutFullscreen, withActionBar);
    }

    public void setStatusBarBackground(@DrawableRes int drawableRes, boolean layoutFullscreen, boolean withActionBar) {
        mStatusBarColorManagerImpl.setStatusBarBackground(drawableRes, layoutFullscreen, withActionBar);
    }

    public void setStatusBarBackground(Drawable drawable, boolean layoutFullscreen, boolean withActionBar) {
        mStatusBarColorManagerImpl.setStatusBarBackground(drawable, layoutFullscreen, withActionBar);
    }

    public void setLightStatusBar(boolean light) {
        mStatusBarColorManagerImpl.setLightStatusBar(light);
    }
}
