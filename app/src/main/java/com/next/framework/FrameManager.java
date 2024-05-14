package com.next.framework;

import android.app.Application;

import com.next.framework.tool.ActivityTool;
import com.next.framework.tool.LogTool;

/**
 * ClassName:框架管理类
 *
 * @author Afton
 * @time 2024/4/1
 * @auditor
 */
public class FrameManager {

    private static FrameManager instance;

    //应用对象
    private Application application;

    //日志输出级别
    private int logLevel = LogTool.LogLevel.LEVEL_ALL;

    public static FrameManager getInstance() {
        if (instance == null) {
            instance = new FrameManager();
        }

        return instance;
    }

    /**
     * 获取应用对象
     *
     * @return 应用对象
     */
    public static Application getApplication() {
        return FrameManager.getInstance().application;
    }

    /**
     * 获取日志输出级别
     *
     * @return 日志输出级别
     */
    public static int getLogLevel() {
        return FrameManager.getInstance().logLevel;
    }

    /**
     * 初始化
     *
     * @param application 应用对象
     * @return 框架管理对象
     */
    public FrameManager init(Application application) {
        this.application = application;

        //初始化Activity工具对象
        this.initActivityTool();

        return this;
    }

    /**
     * 设置日志输出级别
     *
     * @param logLevel 日志输出级别
     * @return 框架管理对象
     */
    public FrameManager setLogLevel(int logLevel) {
        this.logLevel = logLevel;

        return this;
    }

    /**
     * 初始化Activity工具对象
     */
    private void initActivityTool() {
        ActivityTool.getInstance().init(this.application);
    }
}