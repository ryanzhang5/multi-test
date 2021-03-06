package com.hduo.pojo;

import java.util.Date;

import org.springframework.web.bind.annotation.InitBinder;

public class PaiedCustomerVO {
	private Long id;
	private String name;
	private String address;
	private String mobilePhone;
	private Date from;
	private Date to;
	private int buyTimes;
	private int leftTimes;
	private float realPay;
	private String comments;

	private int cardType;
	private String cardNumber;
	private String cardName;

	private String sex;// female 0,male 1
	private String nationality;
	private String company;
	private String career;
	private String deskPhone;

	private String leftTimeColor= "";
	private String endDateColor= "";
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBuyTimes() {
		return buyTimes;
	}

	public void setBuyTimes(int buyTimes) {
		this.buyTimes = buyTimes;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public int getLeftTimes() {
		return leftTimes;
	}

	public void setLeftTimes(int leftTimes) {
		this.leftTimes = leftTimes;
	}

	public float getRealPay() {
		return realPay;
	}

	public void setRealPay(float realPay) {
		this.realPay = realPay;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getDeskPhone() {
		return deskPhone;
	}

	public void setDeskPhone(String deskPhone) {
		this.deskPhone = deskPhone;
	}

	public String getLeftTimeColor() {
		return leftTimeColor;
	}

	public void setLeftTimeColor(String leftTimeColor) {
		this.leftTimeColor = leftTimeColor;
	}

	public String getEndDateColor() {
		return endDateColor;
	}

	public void setEndDateColor(String endDateColor) {
		this.endDateColor = endDateColor;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

}
