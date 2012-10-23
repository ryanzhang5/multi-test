package com.hduo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@NamedQuery(name = "product.selectAll", query = "select n from Product n ")
public class Product {
	private Long id;
	private String productName;
	private String unit;
	private String comments;

	public Product() {
	}

	public Product(String productName, String unit, String comments) {
		this.productName = productName;
		this.unit = unit;
		this.comments = comments;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "product_name")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "unit")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "comments")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", unit="
				+ unit + ", comments=" + comments + "]";
	}

}
