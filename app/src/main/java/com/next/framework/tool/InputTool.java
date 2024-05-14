package com.next.framework.tool;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ClassName:输入工具类
 *
 * @author Afton
 * @time 2023/10/16
 * @auditor
 */
public class InputTool {

    /**
     * 打开输入键盘
     *
     * @param editText 输入控件
     * @param delay    延迟
     */
    public static void showSoftInput(EditText editText, int delay) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText, 0);
            }
        }, delay);
    }

    /**
     * 关闭输入键盘
     *
     * @param editText 输入控件
     */
    public static void closeSoftInput(EditText editText) {
        editText.clearFocus();
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}