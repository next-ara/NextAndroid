package com.next.framework.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.next.framework.factory.BaseFactory;
import com.next.framework.tool.ToastTool;

/**
 * ClassName:框架FragmentActivity基类
 *
 * @author Afton
 * @time 2023/10/27
 * @auditor
 */
abstract public class FrameFragmentActivity extends FragmentActivity {

    //工厂对象
    protected BaseFactory factory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注册工厂
        this.registerFactory();
        //初始化工厂
        this.initFactory(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (this.factory != null) {
            this.factory.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (this.factory != null) {
            this.factory.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (this.factory != null) {
            this.factory.onDestroy();
        }

        if (this.factory != null) {
            this.factory.recycle();
            this.factory = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (this.factory != null) {
            this.factory.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (this.factory != null) {
            this.factory.onResume();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean isHandle = false;

        if (this.factory != null) {
            isHandle = this.factory.onKeyUp(keyCode, event);
        }

        return isHandle ? true : super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (this.factory != null) {
            this.factory.onSaveInstanceState(outState);
        }
    }

    /**
     * 注册工厂
     */
    abstract protected void registerFactory();

    /**
     * 创建工厂对象
     *
     * @param c 类对象
     */
    protected void creatFactory(Class c) {
        try {
            this.factory = (BaseFactory) c.newInstance();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 初始化工厂
     */
    private void initFactory(@Nullable Bundle savedInstanceState) {
        if (this.factory != null) {
            this.factory.setActivity(this);
            this.factory.setSavedInstanceState(savedInstanceState);
            this.factory.init();
        }
    }

    /**
     * 显示提示
     *
     * @param content 内容
     */
    protected void showTips(String content) {
        ToastTool.show(content);
    }

    /**
     * 显示提示
     *
     * @param content  内容
     * @param duration 时长
     */
    protected void showTips(String content, int duration) {
        ToastTool.show(content, duration);
    }
}