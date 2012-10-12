package com.hduo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="client")
@NamedQuery(name="client.selectAll", query="select n from Client n ")
public class Client {
	private Long id;
	private String clientName;
	private String storeName;
	private String address;
	private String mobilePhone;
	private String deskPhone;
	private String comments;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="client_name")
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Column(name="store_name")
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="mobile_phone")
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name="desk_phone")
	public String getDeskPhone() {
		return deskPhone;
	}

	public void setDeskPhone(String deskPhone) {
		this.deskPhone = deskPhone;
	}
	@Column(name="comments")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", clientName=" + clientName + ", storeName="
				+ storeName + ", address=" + address + ", mobilePhone="
				+ mobilePhone + ", deskPhone=" + deskPhone + "]";
	}



}
