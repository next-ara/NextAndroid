package com.next.framework.tool;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassName:设备信息获取工具类
 *
 * @author Afton
 * @time 2023/10/27
 * @auditor
 */
public class DeviceTool {

    /**
     * 获取屏幕宽度
     *
     * @param context 上下文
     * @return 宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕长度
     *
     * @param context 上下文
     * @return 长度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 当前是否是横屏
     *
     * @param context 上下文
     * @return true/false
     */
    public static boolean isLandscapeScreen(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? true : false;
    }

    /**
     * 是否是短屏（-300px < 长度-宽度 < 300px）
     *
     * @param context 上下文
     * @return true/false
     */
    public static boolean isShortScreen(Context context) {
        int width = getScreenWidth(context);
        int height = getScreenHeight(context);
        int differ = width - height;
        if (differ < 0) {
            differ = -differ;
        }

        return differ < 300;
    }

    /**
     * 获取设备名称
     *
     * @return 设备名称
     */
    public static String getDeviceName() {
        String deviceName = "";
        try {
            Class SystemProperties = Class.forName("android.os.SystemProperties");
            Method get = SystemProperties.getDeclaredMethod("get", String.class, String.class);
            deviceName = (String) get.invoke(SystemProperties, "ro.product.marketname", "");
            if (TextUtils.isEmpty(deviceName)) {
                deviceName = (String) get.invoke(SystemProperties, "ro.product.model", "");
            }
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                 ClassNotFoundException e) {
            e.printStackTrace();
        }

        return deviceName;
    }

    /**
     * 获取顶部圆角
     *
     * @param context 上下文
     * @return 圆角
     */
    public static float getCornerRadiusTop(Context context) {
        float radius = 0;
        int resourceId = context.getResources().getIdentifier("rounded_corner_radius_top", "dimen", "android");
        if (resourceId > 0) {
            radius = context.getResources().getDimensionPixelSize(resourceId);

            return radius;
        }

        return 0;
    }
}