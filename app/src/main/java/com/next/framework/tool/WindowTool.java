package com.next.framework.tool;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * ClassName:窗口工具类
 *
 * @author Afton
 * @time 2023/10/26
 * @auditor
 */
public class WindowTool {

    /**
     * 设置全屏
     *
     * @param activity Activity对象
     */
    public static void setFullScreen(Activity activity) {
        setFullScreen(getWindow(activity));
    }

    /**
     * 设置全屏
     *
     * @param window 窗口对象
     */
    public static void setFullScreen(Window window) {
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    /**
     * 设置状态栏图标颜色
     *
     * @param isDark   是否是深色
     * @param activity Activity对象
     */
    public static void setStatusBarColor(boolean isDark, Activity activity) {
        setStatusBarColor(isDark, getWindow(activity));
    }

    /**
     * 设置状态栏图标颜色
     *
     * @param isDark 是否是深色
     * @param window 窗口对象
     */
    public static void setStatusBarColor(boolean isDark, Window window) {
        if (isDark) {
            setStatusBarDark(window);
        } else {
            setStatusBarLight(window);
        }
    }

    /**
     * 设置导航栏图标颜色
     *
     * @param isDark   是否是深色
     * @param activity Activity对象
     */
    public static void setNavigationBarColor(boolean isDark, Activity activity) {
        setNavigationBarColor(isDark, getWindow(activity));
    }

    /**
     * 设置导航栏图标颜色
     *
     * @param isDark 是否是深色
     * @param window 窗口对象
     */
    public static void setNavigationBarColor(boolean isDark, Window window) {
        if (isDark) {
            setNavigationBarDark(window);
        } else {
            setNavigationBarLight(window);
        }
    }

    /**
     * 设置界面沉浸
     *
     * @param activity Activity对象
     */
    public static void setWindowImmersion(Activity activity) {
        setWindowImmersion(getWindow(activity));
    }

    /**
     * 设置界面沉浸
     *
     * @param window 窗口对象
     */
    public static void setWindowImmersion(Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );

        //设置横屏黑边填充
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(params);
        }
    }

    /**
     * 设置状态栏深色图标
     *
     * @param window 窗口对象
     */
    private static void setStatusBarDark(Window window) {
        int flags = window.getDecorView().getSystemUiVisibility();
        window.getDecorView().setSystemUiVisibility(flags | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    /**
     * 设置状态栏浅色图标
     *
     * @param window 窗口对象
     */
    private static void setStatusBarLight(Window window) {
        int flags = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        window.getDecorView().setSystemUiVisibility(flags ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    /**
     * 设置导航栏深色图标
     *
     * @param window 窗口对象
     */
    private static void setNavigationBarDark(Window window) {
        int flags = window.getDecorView().getSystemUiVisibility();
        window.getDecorView().setSystemUiVisibility(flags | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
    }

    /**
     * 设置导航栏浅色图标
     *
     * @param window 窗口对象
     */
    private static void setNavigationBarLight(Window window) {
        int flags = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
        window.getDecorView().setSystemUiVisibility(flags ^ View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
    }

    /**
     * 设置窗口宽度
     *
     * @param dialog Dialog对象
     * @param width  宽度
     */
    public static void setWindowWidth(Dialog dialog, int width) {
        setWindowWidth(getWindow(dialog), width);
    }

    /**
     * 设置窗口宽度
     *
     * @param window 窗口对象
     * @param width  宽度
     */
    public static void setWindowWidth(Window window, int width) {
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = width;
    }

    /**
     * 设置窗口布局重心
     *
     * @param dialog  Dialog对象
     * @param gravity 重心
     */
    public static void setWindowGravity(Dialog dialog, int gravity) {
        setWindowGravity(getWindow(dialog), gravity);
    }

    /**
     * 设置窗口布局重心
     *
     * @param window  窗口对象
     * @param gravity 重心
     */
    public static void setWindowGravity(Window window, int gravity) {
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = gravity;
    }

    /**
     * 获取窗口对象
     *
     * @param activity Activity对象
     * @return 窗口对象
     */
    private static Window getWindow(Activity activity) {
        return activity.getWindow();
    }

    /**
     * 获取窗口对象
     *
     * @param dialog Dialog对象
     * @return 窗口对象
     */
    private static Window getWindow(Dialog dialog) {
        return dialog.getWindow();
    }
}