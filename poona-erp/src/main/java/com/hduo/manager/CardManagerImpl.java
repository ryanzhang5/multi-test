package com.hduo.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.hduo.dao.CardDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.Card;
import com.hduo.util.Utils;

public class CardManagerImpl implements CardManager {
	private final static Logger logger = Logger
			.getLogger(CardManagerImpl.class);
	private CardDao cardDao;

	public void setCardDao(CardDao cardDao) {
		this.cardDao = cardDao;
	}

	@Transactional
	public List<Card> getAllCards() {
		return cardDao.getAllCards();
	}

	@Transactional
	public Card getCard(long id) {
		return cardDao.getCard(id);
	}

	@Transactional
	public void addCard(Card card) {
		cardDao.addCard(card);

	}

	@Transactional
	public void deleteCard(long id) {
		cardDao.deleteCard(getCard(id));

	}

	@Transactional
	public void updateCard(Card card) {
		cardDao.updateCard(card);

	}

	
	
}
