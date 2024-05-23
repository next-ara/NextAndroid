package com.next.framework.factory;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * ClassName:工厂基类
 *
 * @author Afton
 * @time 2023/10/27
 * @auditor
 */
abstract public class BaseFactory extends BaseObject {

    //保存状态
    protected Bundle savedInstanceState;

    //控制器对象列表
    protected ArrayList<BaseController> controllerObjList = new ArrayList<>();

    //模型对象Map
    protected LinkedHashMap<String, BaseModel> modelObjMap = new LinkedHashMap<>();

    //视图管理对象Map
    protected LinkedHashMap<String, BaseViewManage> viewManageObjMap = new LinkedHashMap<>();

    //工具对象Map
    protected LinkedHashMap<String, BaseTool> toolObjMap = new LinkedHashMap<>();

    @Override
    public void init() {
        super.init();

        //注册模型对象
        this.registerModel();
        //注册工具对象
        this.registerTool();
        //注册视图管理对象
        this.registerViewManage();
        //注册控制器对象
        this.registerController();

        //初始化数据
        this.initData();
    }

    @Override
    public void recycle() {
        super.recycle();

        for (BaseController controller : this.controllerObjList) {
            controller.recycle();
        }

        for (String key : this.modelObjMap.keySet()) {
            this.modelObjMap.get(key).recycle();
        }

        for (String key : this.viewManageObjMap.keySet()) {
            this.viewManageObjMap.get(key).recycle();
        }

        for (String key : this.toolObjMap.keySet()) {
            this.toolObjMap.get(key).recycle();
        }

        this.controllerObjList.clear();
        this.controllerObjList = null;

        this.modelObjMap.clear();
        this.modelObjMap = null;

        this.viewManageObjMap.clear();
        this.viewManageObjMap = null;

        this.toolObjMap.clear();
        this.toolObjMap = null;
    }

    /**
     * Activity的onStart监听
     */
    public void onStart() {
        for (BaseController controller : this.controllerObjList) {
            controller.onStart();
        }
    }

    /**
     * Activity的onResume监听
     */
    public void onResume() {
        for (BaseController controller : this.controllerObjList) {
            controller.onResume();
        }
    }

    /**
     * Activity的onPause监听
     */
    public void onPause() {
        for (BaseController controller : this.controllerObjList) {
            controller.onPause();
        }
    }

    /**
     * Activity的onStop监听
     */
    public void onStop() {
        for (BaseController controller : this.controllerObjList) {
            controller.onStop();
        }
    }

    /**
     * Activity的onDestroy监听
     */
    public void onDestroy() {
        for (BaseController controller : this.controllerObjList) {
            controller.onDestroy();
        }
    }

    /**
     * Key监听
     *
     * @param keyCode Key代号
     * @param event   动作
     * @return true/false
     */
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean isHandle = false;

        for (BaseController controller : this.controllerObjList) {
            if (!isHandle) {
                isHandle = controller.onKeyUp(keyCode, event);
                continue;
            }

            break;
        }

        return isHandle;
    }

    /**
     * 状态保存监听
     *
     * @param outState 状态
     */
    public void onSaveInstanceState(@NonNull Bundle outState) {
        for (BaseController controller : this.controllerObjList) {
            controller.onSaveInstanceState(outState);
        }
    }

    /**
     * 获取工具对象
     *
     * @param toolObjKey 工具对象Key
     * @param <T>
     * @return 工具对象
     */
    public <T extends BaseTool> T getTool(String toolObjKey) {
        if (this.toolObjMap.containsKey(toolObjKey)) {
            return (T) this.toolObjMap.get(toolObjKey);
        }

        return null;
    }

    /**
     * 获取模型对象
     *
     * @param modelObjKey 模型对象Key
     * @param <T>
     * @return 模型对象
     */
    public <T extends BaseModel> T getModel(String modelObjKey) {
        if (this.modelObjMap.containsKey(modelObjKey)) {
            return (T) this.modelObjMap.get(modelObjKey);
        }

        return null;
    }

    /**
     * 获取视图管理对象
     *
     * @param viewManageObjKey 视图管理对象Key
     * @param <T>
     * @return 视图管理对象
     */
    public <T extends BaseViewManage> T getViewManage(String viewManageObjKey) {
        if (this.viewManageObjMap.containsKey(viewManageObjKey)) {
            return (T) this.viewManageObjMap.get(viewManageObjKey);
        }

        return null;
    }

    /**
     * 注册模型对象
     */
    abstract protected void registerModel();

    /**
     * 注册工具对象
     */
    abstract protected void registerTool();

    /**
     * 注册视图管理对象
     */
    abstract protected void registerViewManage();

    /**
     * 注册控制器对象
     */
    abstract protected void registerController();

    /**
     * 初始化数据
     */
    private void initData() {
        //初始化模型对象
        for (String key : this.modelObjMap.keySet()) {
            BaseModel model = this.modelObjMap.get(key);
            model.setActivity(this.activity);
            model.init();
        }

        //初始化视图管理对象
        for (String key : this.viewManageObjMap.keySet()) {
            BaseViewManage viewManage = this.viewManageObjMap.get(key);
            viewManage.setActivity(this.activity);
            viewManage.init();
            viewManage.initView();
        }

        //初始化工具对象
        for (String key : this.toolObjMap.keySet()) {
            BaseTool tool = this.toolObjMap.get(key);
            tool.setFactory(this);
            tool.setActivity(this.activity);
            tool.init();
        }

        //初始化控制器对象
        for (BaseController controller : this.controllerObjList) {
            controller.setFactory(this);
            controller.setSavedInstanceState(this.savedInstanceState);
            controller.setActivity(this.activity);
            controller.init();
        }
    }

    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }

    public void setSavedInstanceState(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
    }
}