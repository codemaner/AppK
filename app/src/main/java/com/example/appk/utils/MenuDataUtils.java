package com.example.appk.utils;

import com.example.appk.R;
import com.example.appk.entity.LeftItemMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 那个谁 on 2018/1/19.
 * 奥特曼打小怪兽
 * 作用：
 */

public class MenuDataUtils {
    public static List<LeftItemMenu> getItemMenus(){
        List<LeftItemMenu> menus=new ArrayList<LeftItemMenu>();
        menus.add(new LeftItemMenu(R.drawable.icon_zhanghaoxinxi,"账号信息"));
        menus.add(new LeftItemMenu(R.drawable.icon_wodeguanzhu,"我的关注"));
        menus.add(new LeftItemMenu(R.drawable.icon_shoucang,"我的收藏"));
        menus.add(new LeftItemMenu(R.drawable.icon_yijianfankui,"意见反馈"));
        menus.add(new LeftItemMenu(R.drawable.icon_shezhi,"设置"));
        return  menus;
    }
}
