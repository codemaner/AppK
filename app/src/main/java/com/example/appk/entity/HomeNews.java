package com.example.appk.entity;

/**
 * Created by 那个谁 on 2018/1/18.
 * 奥特曼打小怪兽
 * 作用：首页顶部广告条信息实体类
 */

public class HomeNews {
    /**
     * title : 36氪独家｜ 体 育 青训品牌「宾宇 体 育 」获近千万元Pre－A轮融资，「校园足球」红利才刚刚开始
     * imgurl : https://pic.36krcnd.com/201801/11182111/i6f4ui9a37qqwefp
     * href : http://36kr.com/p/5112605.html
     * content : Pre－A轮近千万元，杭州巢生领投。
     * mask : 早期项目
     * avthor : 徐宁
     * time : 32分钟前
     */

    private String title;
    private String imgurl;
    private String href;
    private String content;
    private String mask;
    private String avthor;
    private String time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getAvthor() {
        return avthor;
    }

    public void setAvthor(String avthor) {
        this.avthor = avthor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
