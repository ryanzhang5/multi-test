package com.hduo.pojo;

import java.util.Date;

public class OutgoingItemVO {
	private Long id;

	private int sum;

	private String productName;

	private float price;

	private float latestIncomePrice;

	private Date date;

	private String comments;

	private String inventoryNum;

	public OutgoingItemVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getLatestIncomePrice() {
		return latestIncomePrice;
	}

	public void setLatestIncomePrice(float latestIncomePrice) {
		this.latestIncomePrice = latestIncomePrice;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getInventoryNum() {
		return inventoryNum;
	}

	public void setInventoryNum(String inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "OutgoingItemVO [id=" + id + ", sum=" + sum + ", productName="
				+ productName + ", price=" + price + ", date=" + date
				+ ", comments=" + comments + ", inventoryNum=" + inventoryNum
				+ "]";
	}

}
