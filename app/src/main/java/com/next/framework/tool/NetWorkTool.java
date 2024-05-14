package com.next.framework.tool;

import static android.content.Context.WIFI_SERVICE;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName:网络工具类
 *
 * @author Afton
 * @time 2024/4/1
 * @auditor
 */
public class NetWorkTool {

    /**
     * 检测是否是正确的IP地址
     *
     * @param ip IP地址
     * @return true/false
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
     * 获取IP地址
     *
     * @param context 上下文
     * @return IP地址
     */
    public static String getIp(Context context) {
        ArrayList<String> ipv4List = getIpv4s();
        if (!ipv4List.isEmpty()) {
            return ipv4List.get(0);
        } else {
            //获取wifi服务
            WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            return intToIp(ipAddress);
        }
    }

    /**
     * 获取IPv4地址列表
     *
     * @return IPv4地址列表
     */
    public static ArrayList<String> getIpv4s() {
        ArrayList<String> list = new ArrayList<>();

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    NetworkInterface anInterface = interfaces.nextElement();
                    if (anInterface.isLoopback()) continue;

                    Enumeration<InetAddress> addresses = anInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress address = addresses.nextElement();
                        if (address instanceof Inet4Address) {
                            list.add(address.getHostAddress());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 是否连接WiFi
     *
     * @param context 上下文
     * @return true/false
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (wifiNetworkInfo.isConnected() || getWifiApState(context)) {
            return true;
        }
        return false;
    }

    /**
     * 网络是否打开
     *
     * @param context 上下文
     * @return true/false
     */
    public static boolean isOpenNetwork(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }

        return false;
    }

    /**
     * 是否开启WiFi热点
     *
     * @param context 上下文
     * @return true/false
     */
    public static boolean getWifiApState(Context context) {
        try {
            WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            Method method = manager.getClass().getDeclaredMethod("getWifiApState");
            int state = (int) method.invoke(manager);
            Field field = manager.getClass().getDeclaredField("WIFI_AP_STATE_ENABLED");
            int value = (int) field.get(manager);

            if (state == value) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * IP地址转换
     *
     * @param i
     * @return IP地址
     */
    private static String intToIp(int i) {
        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }
}