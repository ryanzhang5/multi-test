package com.hduo.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "cost")
@NamedQuery(name = "cost.selectAll", query = "select n from Cost n ")
public class Cost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "cost_item")
	private String costItem;

	@Column(name = "cost_date")
	private Date costDate;

	@Column(name = "cost_amount")
	private float costAmount;

	@Column(name = "comment")
	private String comment;

	public Cost() {
	};

	public Cost(String costItem, Date costDate, float costAmount, String comment) {
		this.costItem = costItem;
		this.costDate = costDate;
		this.costAmount = costAmount;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCostItem() {
		return costItem;
	}

	public void setCostItem(String costItem) {
		this.costItem = costItem;
	}

	public Date getCostDate() {
		return costDate;
	}

	public void setCostDate(Date costDate) {
		this.costDate = costDate;
	}

	public float getCostAmount() {
		return costAmount;
	}

	public void setCostAmount(float costAmount) {
		this.costAmount = costAmount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
