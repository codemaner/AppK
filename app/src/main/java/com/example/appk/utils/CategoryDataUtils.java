package com.example.appk.utils;

import com.example.appk.entity.CategoriesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 那个谁 on 2018/1/20.
 * 奥特曼打小怪兽
 * 作用：
 */

public class CategoryDataUtils {
    public static List<CategoriesBean> getCategoryBeans(){
        List<CategoriesBean>  beans=new ArrayList<>();
        beans.add(new CategoriesBean("全部","http://www.wanandroid.com/tools/mockapi/2124/news","全部"));
        beans.add(new CategoriesBean("前沿科技","http://www.wanandroid.com/tools/mockapi/2124/qianyankeji","前沿科技"));
        beans.add(new CategoriesBean("人工智能","http://www.wanandroid.com/tools/mockapi/2124/rengongzhineng","人工智能"));
        beans.add(new CategoriesBean("物联网","http://www.wanandroid.com/tools/mockapi/2124/wulianwang","物联网"));
        beans.add(new CategoriesBean("大公司","http://www.wanandroid.com/tools/mockapi/2124/dagongsi","大公司"));
        beans.add(new CategoriesBean("文娱","http://www.wanandroid.com/tools/mockapi/2124/wenyu","文娱"));
        beans.add(new CategoriesBean("汽车","http://www.wanandroid.com/tools/mockapi/2124/qiche","汽车"));
        beans.add(new CategoriesBean("餐饮","http://www.wanandroid.com/tools/mockapi/2124/canyin","餐饮"));
        beans.add(new CategoriesBean("城市","http://www.wanandroid.com/tools/mockapi/2124/chengshi","城市"));
        beans.add(new CategoriesBean("金融","http://www.wanandroid.com/tools/mockapi/2124/jinrong","金融"));
        beans.add(new CategoriesBean("企业服务","http://www.wanandroid.com/tools/mockapi/2124/qiyefuwu","企业服务"));
        return beans;
    }
}
