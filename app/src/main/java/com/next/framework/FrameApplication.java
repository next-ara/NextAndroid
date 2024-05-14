package com.next.framework;

import android.app.Application;

import com.next.framework.tool.LogTool;

/**
 * ClassName:框架应用类
 *
 * @author Afton
 * @time 2024/4/1
 * @auditor
 */
public class FrameApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化框架管理对象
        this.initFrameManager();
    }

    /**
     * 初始化框架管理对象
     */
    private void initFrameManager() {
        FrameManager.getInstance()
                .setLogLevel(this.getLogLevel())
                .init(this);
    }

    /**
     * 获取日志输出级别
     *
     * @return 日志输出级别
     */
    protected int getLogLevel() {
        return LogTool.LogLevel.LEVEL_ALL;
    }
}