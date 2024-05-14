package com.next.framework.tool;

import android.util.Log;

import com.next.framework.FrameManager;

/**
 * ClassName:日志工具类
 *
 * @author Afton
 * @time 2024/4/9
 * @auditor
 */
public class LogTool {

    //日志输出级别
    public static class LogLevel {
        //日志输出级别NONE
        public static final int LEVEL_OFF = 0;
        //日志输出级别ALL
        public static final int LEVEL_ALL = 6;
        //日志输出级别V
        public static final int LEVEL_VERBOSE = 1;
        //日志输出级别D
        public static final int LEVEL_DEBUG = 2;
        //日志输出级别I
        public static final int LEVEL_INFO = 3;
        //日志输出级别W
        public static final int LEVEL_WARN = 4;
        //日志输出级别E
        public static final int LEVEL_ERROR = 5;
    }

    /**
     * 输出日志
     *
     * @param tag 标签
     * @param msg 内容
     */
    public static void v(String tag, String msg) {
        if (getLevel() >= LogLevel.LEVEL_VERBOSE) {
            Log.v(tag, msg);
        }
    }

    /**
     * 输出日志
     *
     * @param tag 标签
     * @param msg 内容
     */
    public static void d(String tag, String msg) {
        if (getLevel() >= LogLevel.LEVEL_DEBUG) {
            Log.d(tag, msg);
        }
    }

    /**
     * 输出日志
     *
     * @param tag 标签
     * @param msg 内容
     */
    public static void i(String tag, String msg) {
        if (getLevel() >= LogLevel.LEVEL_INFO) {
            Log.i(tag, msg);
        }
    }

    /**
     * 输出日志
     *
     * @param tag 标签
     * @param msg 内容
     */
    public static void w(String tag, String msg) {
        if (getLevel() >= LogLevel.LEVEL_WARN) {
            Log.w(tag, msg);
        }
    }

    /**
     * 输出日志
     *
     * @param tag 标签
     * @param msg 内容
     */
    public static void e(String tag, String msg) {
        if (getLevel() >= LogLevel.LEVEL_ERROR) {
            Log.e(tag, msg);
        }
    }

    /**
     * 获取当前日志级别
     *
     * @return 级别
     */
    private static int getLevel() {
        return FrameManager.getLogLevel();
    }
}