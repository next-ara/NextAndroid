package com.next.framework.factory;

import android.text.TextUtils;

import com.next.framework.tool.ToastTool;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * ClassName:工具基类
 *
 * @author Afton
 * @time 2023/10/27
 * @auditor
 */
abstract public class BaseTool extends BaseObject {

    //工厂对象
    private BaseFactory factory;

    @Override
    public void init() {
        super.init();

        //初始化GetObj注解属性
        this.initGetObjFields();
    }

    /**
     * 获取工具对象
     *
     * @param toolObjKey 工具对象Key
     * @param <T>
     * @return 工具对象
     */
    public <T extends BaseTool> T getTool(String toolObjKey) {
        return this.factory.getTool(toolObjKey);
    }

    /**
     * 获取模型对象
     *
     * @param modelObjKey 模型对象Key
     * @param <T>
     * @return 模型对象
     */
    public <T extends BaseModel> T getModel(String modelObjKey) {
        return this.factory.getModel(modelObjKey);
    }

    /**
     * 获取视图管理对象
     *
     * @param viewManageObjKey 视图管理对象Key
     * @param <T>
     * @return 视图管理对象
     */
    public <T extends BaseViewManage> T getViewManage(String viewManageObjKey) {
        return this.factory.getViewManage(viewManageObjKey);
    }

    /**
     * 显示提示
     *
     * @param content 内容
     */
    protected void showTips(String content) {
        ToastTool.show(content);
    }

    /**
     * 显示提示
     *
     * @param content  内容
     * @param duration 时长
     */
    protected void showTips(String content, int duration) {
        ToastTool.show(content, duration);
    }

    /**
     * 初始化GetObj注解属性
     */
    private void initGetObjFields() {
        //获取所有注解属性
        ArrayList<Field> fieldAnnotationList = this.getAllAnnotationFields();
        if (!fieldAnnotationList.isEmpty()) {
            for (Field field : fieldAnnotationList) {
                GetObj getObj = field.getAnnotation(GetObj.class);
                String objKey = getObj.value();
                if (TextUtils.isEmpty(objKey)) {
                    continue;
                }

                if (BaseTool.class.isAssignableFrom(field.getType())) {
                    this.setFieldValue(field, this.getTool(objKey));
                } else if (BaseModel.class.isAssignableFrom(field.getType())) {
                    this.setFieldValue(field, this.getModel(objKey));
                } else if (BaseViewManage.class.isAssignableFrom(field.getType())) {
                    this.setFieldValue(field, this.getViewManage(objKey));
                }
            }
        }
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
     * 获取所有注解属性列表
     *
     * @return 注解属性列表
     */
    private ArrayList<Field> getAllAnnotationFields() {
        Class c = this.getClass();
        ArrayList<Field> fieldAnnotationList = new ArrayList<>();
        for (Field field : c.getDeclaredFields()) {
            boolean isAnnotationPresent = field.isAnnotationPresent(GetObj.class);
            if (isAnnotationPresent) {
                fieldAnnotationList.add(field);
            }
        }

        return fieldAnnotationList;
    }

    public BaseFactory getFactory() {
        return factory;
    }

    public void setFactory(BaseFactory factory) {
        this.factory = factory;
    }
}