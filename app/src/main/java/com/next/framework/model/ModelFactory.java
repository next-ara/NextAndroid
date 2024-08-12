package com.next.framework.model;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName:模型工厂类
 *
 * @author Afton
 * @time 2024/8/12
 * @auditor
 */
public class ModelFactory {

    private static ModelFactory instance;

    //全局模型对象Map
    private ConcurrentHashMap<String, BaseGlobalModel> globalModelMap = new ConcurrentHashMap<>();

    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }

        return instance;
    }

    /**
     * 注册模型对象
     *
     * @param modelObjKey 模型对象key
     * @param model       模型对象
     */
    public void put(String modelObjKey, BaseGlobalModel model) {
        this.globalModelMap.put(modelObjKey, model);
    }

    /**
     * 获取模型对象
     *
     * @param modelObjKey 模型对象key
     * @return 模型对象
     */
    public <T extends BaseGlobalModel> T get(String modelObjKey) {
        return (T) this.globalModelMap.get(modelObjKey);
    }

    /**
     * 删除模型对象
     *
     * @param modelObjKey 模型对象key
     */
    public void remove(String modelObjKey) {
        if (this.globalModelMap.containsKey(modelObjKey)) {
            this.globalModelMap.remove(modelObjKey);
        }
    }

    /**
     * 清空模型对象
     */
    public void clear() {
        this.globalModelMap.clear();
    }
}