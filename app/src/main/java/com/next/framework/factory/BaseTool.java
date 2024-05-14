package com.next.framework.factory;

import com.next.framework.tool.ToastTool;

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

    public BaseFactory getFactory() {
        return factory;
    }

    public void setFactory(BaseFactory factory) {
        this.factory = factory;
    }
}