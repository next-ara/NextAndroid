package com.next.framework.tool;

import android.util.DisplayMetrics;

import com.next.framework.FrameManager;

/**
 * ClassName:密度转换工具类
 *
 * @author Afton
 * @time 2023/10/27
 * @auditor
 */
public class DensityTool {

    /**
     * dp转px
     *
     * @param dp 屏幕密度
     * @return 像素
     */
    public static int dpToPx(float dp) {
        float density = getDisplayMetrics().density;
        return Math.round((dp * density));
    }

    /**
     * px转dp
     *
     * @param px 像素
     * @return 屏幕密度
     */
    public static int pxToDp(float px) {
        float density = getDisplayMetrics().density;
        return Math.round((px / density));
    }

    /**
     * 获取DisplayMetrics对象
     *
     * @return DisplayMetrics对象
     */
    private static DisplayMetrics getDisplayMetrics() {
        return FrameManager.getApplication().getResources().getDisplayMetrics();
    }
}