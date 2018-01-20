package com.example.appk.biz;

import com.example.appk.entity.AdHeadBean;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 那个谁 on 2018/1/18.
 * 奥特曼打小怪兽
 * 作用：首页顶部广告数据抓取工具类
 */

public class HeadDataManager {
    public HeadDataManager(){

    }
    /**
     * 进行根据地址抓取顶部广告数据
     */
    public List<AdHeadBean> getHeadBeans(Document document){
        List<AdHeadBean> adHeadBeen = new ArrayList<AdHeadBean>();
        Elements elements = document.select("div.banner_cell-track");
        Elements links = elements.first().getElementsByTag("a");
        for (Element element : links){
            String href = element.attr("href");
            String title = element.select("div.info").select("div.abstract").text();
            //String imgurl = ImageUtils.getCutImageUrl(element.cssSelector("background-image").split())
        }
        System.out.print(links);
        return adHeadBeen;
    }

}
