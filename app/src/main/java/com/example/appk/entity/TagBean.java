package com.example.appk.entity;

/**
 * Created by 那个谁 on 2018/1/18.
 * 奥特曼打小怪兽
 * 作用：文章标签信息实体类
 */
public class TagBean {
	private String href;
	private String tagname;

	public TagBean() {
		super();
	}

	public TagBean(String href, String tagname) {
		super();
		this.href = href;
		this.tagname = tagname;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	@Override
	public String toString() {
		return "TagBean [href=" + href + ", tagname=" + tagname + "]";
	}

}
