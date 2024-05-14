package com.next.framework.tool;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * ClassName:Activity工具类
 *
 * @author Afton
 * @time 2023/7/16
 * @auditor
 */
public class ActivityTool {

    //应用切换监听接口
    public interface OnAppSwithListener {

        /**
         * 前台
         */
        void onForeground();

        /**
         * 后台
         */
        void onBackground();
    }

    private static ActivityTool instance;

    //应用对象
    private Application application;

    //Activity对象列表
    private ArrayList<Activity> activityObjList = new ArrayList<>();

    //当前展示的Activity对象
    private Activity topActivityObj = null;

    //Activity数量
    private int countActivity = 0;

    //应用是否处于后台
    private boolean isAppBackground = false;

    //App切换监听接口
    private OnAppSwithListener onAppSwithListener;

    public static ActivityTool getInstance() {
        if (instance == null) {
            instance = new ActivityTool();
        }

        return instance;
    }

    /**
     * 初始化
     *
     * @param application 应用对象
     */
    public void init(Application application) {
        if (this.application != null) {
            return;
        }

        this.application = application;
        this.countActivity = 0;
        this.isAppBackground = false;

        this.application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                ActivityTool.this.add(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                countActivity++;
                if (countActivity == 1 && isAppBackground) {
                    //说明应用重新进入了前台
                    ActivityTool.this.isAppBackground = false;
                    //发送前台监听
                    ActivityTool.this.sendForegroundListener();
                }
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                setTopActivity(activity);
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                ActivityTool.this.countActivity--;
                if (ActivityTool.this.countActivity <= 0 && !ActivityTool.this.isAppBackground) {
                    //说明应用进入了后台
                    ActivityTool.this.isAppBackground = true;
                    //发送后台监听
                    ActivityTool.this.sendBackgroundListener();
                }
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                //移除Activity
                ActivityTool.this.removeActivity(activity);
            }
        });
    }

    /**
     * 获取当前展示的Activity对象
     *
     * @return Activity对象
     */
    public Activity getTopActivity() {
        return this.topActivityObj;
    }

    /**
     * 关闭并移除Activity
     *
     * @param classObj 类对象
     */
    public void closeActivity(Class classObj) {
        for (int i = this.activityObjList.size() - 1; i >= 0; i--) {
            Activity activity = this.activityObjList.get(i);
            //判断是否是当前Activity
            if (activity.getClass().getName().equals(classObj.getName())) {
                //关闭Activity
                if (!activity.isFinishing()) {
                    activity.finish();
                }

                break;
            }
        }
    }

    /**
     * 验证Activity是否已打开
     *
     * @param classObj 类对象
     * @return true/false
     */
    public boolean isOpen(Class classObj) {
        for (Activity activity : this.activityObjList) {
            if (activity.getClass().getName().equals(classObj.getName())) {
                if (!activity.isFinishing()) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 返回至指定Activity
     *
     * @param classObj 类对象
     */
    public void backToActivity(Class classObj) {
        //指定Activity是否打开
        if (!isOpen(classObj)) {
            return;
        }

        for (int i = this.activityObjList.size() - 1; i >= 0; i--) {
            Activity activity = this.activityObjList.get(i);
            //判断是否是指定Activity
            if (activity.getClass().getName().equals(classObj.getName())) {
                break;
            }

            //关闭Activity
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 增加Activity对象
     *
     * @param activity Activity对象
     */
    private void add(Activity activity) {
        this.activityObjList.add(activity);
    }

    /**
     * 设置当前展示的activity对象
     *
     * @param activity Activity对象
     */
    private void setTopActivity(Activity activity) {
        this.topActivityObj = activity;
    }

    /**
     * 移除Activity
     *
     * @param activity Activity对象
     */
    private void removeActivity(Activity activity) {
        if (activity != null && this.activityObjList.contains(activity)) {
            this.activityObjList.remove(activity);
        }
    }

    /**
     * 发送前台监听
     */
    private void sendForegroundListener() {
        if (this.onAppSwithListener != null) {
            this.onAppSwithListener.onForeground();
        }
    }

    /**
     * 发送后台监听
     */
    private void sendBackgroundListener() {
        if (this.onAppSwithListener != null) {
            this.onAppSwithListener.onBackground();
        }
    }

    /**
     * 验证应用是否处于后台
     *
     * @return true/false
     */
    public boolean isAppBackground() {
        return this.isAppBackground;
    }

    /**
     * 获取Activity数量
     *
     * @return Activity数量
     */
    public int getCountActivity() {
        return this.countActivity;
    }

    /**
     * 获取当前展示的Activity对象
     *
     * @return Activity对象
     */
    public Activity getTopActivityObj() {
        return this.topActivityObj;
    }

    /**
     * 设置应用切换监听接口
     *
     * @param onAppSwithListener 应用切换监听接口
     */
    public void setOnAppSwithListener(OnAppSwithListener onAppSwithListener) {
        this.onAppSwithListener = onAppSwithListener;
    }
}