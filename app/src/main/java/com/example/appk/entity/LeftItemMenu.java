package com.example.appk.entity;

/**
 * Created by 那个谁 on 2018/1/18.
 * 奥特曼打小怪兽
 * 作用：左侧功能菜单 item实体类
 */

public class LeftItemMenu {
    private int leftIcon;
    private String title;
    public LeftItemMenu() {
    }

    public LeftItemMenu(int leftIcon, String title) {
        this.leftIcon = leftIcon;
        this.title = title;
    }

    public int getLeftIcon() {
        return leftIcon;
    }

    public void setLeftIcon(int leftIcon) {
        this.leftIcon = leftIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "LeftItemMenu{" +
                "leftIcon=" + leftIcon +
                ", title='" + title + '\'' +
                '}';
    }
}
