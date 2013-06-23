package com.hduo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "card")
@NamedQuery(name = "card.selectAll", query = "select n from Card n ")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="cardName")
	private String cardName;
	
	@Column(name="cardType")
	private int cardType;
	
	@Column(name="cardTimes")
	private int cardTimes;
	
	public int getCardTimes() {
		return cardTimes;
	}

	public void setCardTimes(int cardTimes) {
		this.cardTimes = cardTimes;
	}

	@Column(name="cardFee")
	private float cardFee;
	
	@Column(name="comments")
	private String comments;

	public Card() {
	};

	public Card(String cardName, float cardFee, String comments) {
		this.cardName = cardName;
		this.cardFee = cardFee;
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public float getCardFee() {
		return cardFee;
	}

	public void setCardFee(float cardFee) {
		this.cardFee = cardFee;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", cardName=" + cardName + ", cardFee="
				+ cardFee + ", comments=" + comments + "]";
	}

}
