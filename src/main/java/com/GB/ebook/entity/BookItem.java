package com.GB.ebook.entity;

public class BookItem {
	private String site;
	private String url;
	private String title;
	private Float price;
	
	public BookItem(){
		
	}
	
	public BookItem(String site, String url, String title, Float price) {
		super();
		this.site = site;
		this.url = url;
		this.title = title;
		this.price = price;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "BookItem [site=" + site + ", price=" + price+", title=" + title + ", url=" + url    + "]";
	}

}
