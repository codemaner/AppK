package com.example.appk.common;

/**
 * Created by 那个谁 on 2018/1/18.
 * 奥特曼打小怪兽
 * 作用：
 */

public interface DefineView {
    public void initView();  //初始化界面元素
    public void initValidata();  //初始化变量
    public void initListener();  //初始化监听器
    public void bindData();       //绑定数据
}
