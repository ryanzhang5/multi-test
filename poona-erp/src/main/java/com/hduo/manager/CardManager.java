package com.hduo.manager;

import java.util.List;

import com.hduo.pojo.Card;

public interface CardManager {

	List<Card> getAllCards();

	Card getCard(long id);

	void addCard(Card card);

	void deleteCard(long id);

	void updateCard(Card card);
}
