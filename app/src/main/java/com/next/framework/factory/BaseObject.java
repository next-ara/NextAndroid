package com.next.framework.factory;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/**
 * ClassName:对象基类
 *
 * @author Afton
 * @time 2023/10/27
 * @auditor
 */
abstract public class BaseObject {

    //Activity对象
    protected Activity activity;

    /**
     * 初始化
     */
    public void init() {
    }

    /**
     * 回收释放
     */
    public void recycle() {
    }

    /**
     * 获取资源对象
     *
     * @return 资源对象
     */
    protected Resources getResources() {
        return this.activity.getResources();
    }

    /**
     * 获取资源文本
     *
     * @param resId 资源id
     * @return 文本
     */
    protected String getString(@StringRes int resId) {
        return this.activity.getString(resId);
    }

    /**
     * 获取资源颜色
     *
     * @param resId 资源id
     * @return 颜色
     */
    protected int getColor(@ColorRes int resId) {
        return this.activity.getColor(resId);
    }

    /**
     * 获取资源图片
     *
     * @param resId 资源id
     * @return 图片对象
     */
    protected Drawable getDrawable(@DrawableRes int resId) {
        return this.activity.getDrawable(resId);
    }

    /**
     * 获取资源尺寸
     *
     * @param resId 资源id
     * @return 尺寸
     */
    protected float getDimension(@DimenRes int resId) {
        return this.getResources().getDimension(resId);
    }

    /**
     * 获取包名
     *
     * @return 包名
     */
    protected String getPackageName() {
        return this.activity.getPackageName();
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}