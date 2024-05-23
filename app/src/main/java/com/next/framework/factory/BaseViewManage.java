package com.next.framework.factory;

import android.view.View;

import androidx.annotation.IdRes;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * ClassName:视图管理基类
 *
 * @author Afton
 * @time 2023/10/27
 * @auditor
 */
abstract public class BaseViewManage extends BaseObject {

    @Override
    public void init() {
        super.init();

        //获取所有注解属性
        ArrayList<Field> fieldAnnotationList = this.getAllAnnotationFields();
        if (!fieldAnnotationList.isEmpty()) {
            for (Field field : fieldAnnotationList) {
                FindView findView = field.getAnnotation(FindView.class);
                int id = findView.id();
                if (id != 0) {
                    View view = this.findViewById(id);
                    this.setFieldValue(field, view);
                }
            }
        }
    }

    /**
     * 初始化视图
     */
    public void initView() {
    }

    /**
     * 获取所有注解属性列表
     *
     * @return 注解属性列表
     */
    private ArrayList<Field> getAllAnnotationFields() {
        Class c = this.getClass();
        ArrayList<Field> fieldAnnotationList = new ArrayList<>();
        for (Field field : c.getDeclaredFields()) {
            boolean isAnnotationPresent = field.isAnnotationPresent(FindView.class);
            if (isAnnotationPresent) {
                fieldAnnotationList.add(field);
            }
        }

        return fieldAnnotationList;
    }

    /**
     * 设置属性值
     *
     * @param field 属性
     * @param value 属性值
     */
    private void setFieldValue(Field field, Object value) {
        field.setAccessible(true);

        try {
            field.set(this, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

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