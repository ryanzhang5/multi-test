package com.hduo.pojo;

public class NonPaiedCustomerVO {
	private Long id;
	private String name;
	private String address;
	private String mobilePhone;
	private int trackTimes;

	private String sex;// female 0,male 1
	private String nationality;
	private String company;
	private String career;
	private String deskPhone;

	private String latestTrack;
	
	private String createDate;

	private String comments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getTrackTimes() {
		return trackTimes;
	}

	public void setTrackTimes(int trackTimes) {
		this.trackTimes = trackTimes;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getLatestTrack() {
		return latestTrack;
	}

	public void setLatestTrack(String latestTrack) {
		this.latestTrack = latestTrack;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
