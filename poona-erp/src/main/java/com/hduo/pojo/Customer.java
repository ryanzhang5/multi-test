package com.hduo.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@NamedQuery(name = "customer.selectAll", query = "select n from Customer n ")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;

	@Column(name = "mobilePhone")
	private String mobilePhone;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "card_id")
	private Card card;

	@Column(name = "cardNumber")
	private String cardNumber;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Set<TrackItem> trackItems = new HashSet<TrackItem>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Set<PracticeRecord> practiceRecords = new HashSet<PracticeRecord>();

	@Column(name = "comments")
	private String comments;

	@Column(name = "from_date")
	private Date from;

	@Column(name = "to_date")
	private Date to;

	@Column(name = "leftTimes")
	private int leftTimes;

	@Column(name = "buyTimes")
	private int buyTimes;
	
	@Column(name = "realPay")
	private float realPay;

	@Column(name = "paied")
	private boolean paied;
	
	
	@Column(name = "sex")
	private String sex;//female 0,male 1
	
	@Column(name = "nationality")
	private String nationality;
	
	
	@Column(name = "company")
	private String company;
	
	@Column(name = "career")
	private String career;
	
	
	@Column(name = "desk_phone")
	private String deskPhone;
	
	@Column(name = "creator")
	private String creator;
	
	
	@Column(name = "create_date")
	private Date createDate;

	public Customer() {
	}

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

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Set<TrackItem> getTrackItems() {
		return trackItems;
	}

	public void setTrackItems(Set<TrackItem> trackItems) {
		this.trackItems = trackItems;
	}

	public boolean isPaied() {
		return paied;
	}

	public void setPaied(boolean paied) {
		this.paied = paied;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PracticeRecord> getPracticeRecords() {
		return practiceRecords;
	}

	public void setPracticeRecords(Set<PracticeRecord> practiceRecords) {
		this.practiceRecords = practiceRecords;
	}

	public int getBuyTimes() {
		return buyTimes;
	}

	public void setBuyTimes(int buyTimes) {
		this.buyTimes = buyTimes;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	
}
