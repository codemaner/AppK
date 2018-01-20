package com.example.appk.application;

import android.app.Application;

/**
 * Created by 那个谁 on 2018/1/19.
 * 奥特曼打小怪兽
 * 作用：全局Application类,作为全局数据的配置以及相关参数数据初始化工作
 */
public class CNKApplication  extends Application{
    private static CNKApplication instance=null;
    @Override
    public void onCreate() {
        super.onCreate();
        this.instance=this;
    }
    public static CNKApplication getInstance(){
        return instance;
    }
}
