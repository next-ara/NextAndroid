package com.next.framework.tool;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Process;

import com.next.framework.FrameManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName:系统工具类
 *
 * @author Afton
 * @time 2023/7/23
 * @auditor
 */
public class SystemTool {

    /**
     * 获取系统版本号
     *
     * @return 版本号
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取版本名称
     *
     * @param packageName 包名
     * @return 版本名称
     */
    public static String getVersionName(String packageName) {
        PackageManager packageManager = FrameManager.getApplication().getPackageManager();
        try {
            PackageInfo Info = packageManager.getPackageInfo(packageName, 0);
            String oldVerName = Info.versionName;
            return oldVerName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 获取版本号
     *
     * @param packageName 包名
     * @return 版本号
     */
    public static int getVersionCode(String packageName) {
        PackageManager packageManager = FrameManager.getApplication().getPackageManager();
        try {
            PackageInfo Info = packageManager.getPackageInfo(packageName, 0);
            int oldVerCode = Info.versionCode;
            return oldVerCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 1;
    }

    /**
     * 复制到剪切板
     *
     * @param content 内容
     */
    public static void clipData(String content) {
        ClipboardManager cm = (ClipboardManager) FrameManager.getApplication().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", content);
        cm.setPrimaryClip(mClipData);
    }

    /**
     * 检测是否是正确的IP地址
     *
     * @param ip
     * @return
     */
    public static boolean isIPAddress(String ip) {
        String pattern = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(ip);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 跳转网址
     *
     * @param context 上下文
     * @param url     网址
     */
    public static boolean openUrl(Context context, String url) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri content_url = Uri.parse(url);
            intent.setData(content_url);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 返回手机桌面
     *
     * @param context 上下文
     */
    public static void backToHome(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(intent);
    }

    /**
     * 重启应用
     *
     * @param context 上下文
     */
    public static void restartApp(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent launchIntent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        context.startActivity(launchIntent);
        Process.killProcess(android.os.Process.myPid());
    }
}