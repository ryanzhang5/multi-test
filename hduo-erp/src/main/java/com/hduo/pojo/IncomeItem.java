package com.hduo.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "income_item")
@NamedQuery(name = "income_item.selectAll", query = "select n from IncomeItem n ")
public class IncomeItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "income_date")
	private Date date;
	
	@Column(name = "comments")
	private String comments;

	public IncomeItem() {
	}

	public IncomeItem(Product product, float price, Date date, String comments) {
		this.price = price;
		this.product = product;
		this.date = date;
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	@Override
	public String toString() {
		return "IncomeItem [id=" + id + ", product=" + product + ", price="
				+ price + ", date=" + date + ", comments=" + comments + "]";
	}

}
