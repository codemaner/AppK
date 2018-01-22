package com.example.appk.entity;

/**
 * Created by 那个谁 on 2018/1/18.
 * 奥特曼打小怪兽
 * 作用：文章详情数据bean ProjectName
 */
public class NewsBean {


	/**
	 * title : 投资热度持续升温，半年增4倍 | 36氪 前 沿 科 技 投资热度报告
	 * imgurl : https://pic.36krcnd.com/avatar/201707/27234217/0yn3n5f01sf0ckt0.jpg
	 * href : http://36kr.com/p/5084913.html
	 * content : 还原2017上半年投资人真实的投资心理
	 * mask : 新风向
	 * avthor : 石亚琼 ·
	 * time : 2017-07-28
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
