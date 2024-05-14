package com.next.framework.tool;

import android.content.Context;
import android.widget.Toast;

import com.next.framework.FrameManager;

/**
 * ClassName:Toast工具类
 *
 * @author Afton
 * @time 2023/8/29
 * @auditor
 */
public class ToastTool {

    /**
     * 显示 Toast
     *
     * @param content 内容
     */
    public static void show(String content) {
        ThreadTool.runOnUIThread(() -> {
            ToastTool.getInstance().show(FrameManager.getApplication(), content);
        });
    }

    /**
     * 显示 Toast
     *
     * @param content  内容
     * @param duration 时长
     */
    public static void show(String content, int duration) {
        ThreadTool.runOnUIThread(() -> {
            ToastTool.getInstance().show(FrameManager.getApplication(), content, duration);
        });
    }

    private static ToastTool instance;

    //Toast对象
    private Toast toast;

    public static ToastTool getInstance() {
        if (instance == null) {
            instance = new ToastTool();
        }

        return instance;
    }

    /**
     * 显示 Toast
     *
     * @param context 上下文
     * @param content 内容
     */
    public void show(Context context, String content) {
        if (this.toast != null) {
            this.toast.cancel();
        }

        this.toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        this.toast.show();
    }

    /**
     * 显示 Toast
     *
     * @param context  上下文
     * @param content  内容
     * @param duration 时长
     */
    public void show(Context context, String content, int duration) {
        if (this.toast != null) {
            this.toast.cancel();
        }

        this.toast = Toast.makeText(context, content, duration);
        this.toast.show();
    }
}