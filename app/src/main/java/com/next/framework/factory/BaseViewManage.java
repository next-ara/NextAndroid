package com.next.framework.factory;

import android.view.View;

import androidx.annotation.IdRes;

/**
 * ClassName:视图管理基类
 *
 * @author Afton
 * @time 2023/10/27
 * @auditor
 */
abstract public class BaseViewManage extends BaseObject {

    /**
     * 初始化视图
     */
    abstract public void initView();

    /**
     * 获取控件
     *
     * @param resId 控件id
     * @param <T>
     * @return 控件
     */
    protected <T extends View> T findViewById(@IdRes int resId) {
        return this.activity.findViewById(resId);
    }
}