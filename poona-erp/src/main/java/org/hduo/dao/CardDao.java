package org.hduo.dao;

import java.util.List;

import com.hduo.pojo.Card;

public class CardDao extends Dao {

	public void addCard(Card card) {
		getSession().save(card);
	}

	public Card getCard(Long id) {
		return (Card) getSession().get(Card.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Card> getAllCards() {
		List<Card> cards = (List<Card>) getSession().getNamedQuery(
				"card.selectAll").list();
		return cards;
	}

	public void deleteCard(Card card) {
		getSession().delete(card);
	}

	public void updateCard(Card card) {
		getSession().update(card);
	}
}
