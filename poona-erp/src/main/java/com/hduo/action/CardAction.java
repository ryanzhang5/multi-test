package com.hduo.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hduo.manager.CardManager;
import com.hduo.pojo.Card;
import com.hduo.util.SystemConstant;
import com.opensymphony.xwork2.ActionSupport;

public class CardAction extends ActionSupport {
	
	private final static Logger logger = Logger.getLogger(CardAction.class);
	private CardManager cardManager;
	private String cardId;
	private List<Card> cards;
	private int cardNum;
	private InputStream inputStream;
	private Card card;
	public String getAllCards() {
		cards = cardManager.getAllCards();
		this.cardNum = cards.size();
		logger.info("---------------------" + cards.size());
		return SUCCESS;
	}

	public String toAddCard() {
		return SUCCESS;
	}

	public String checkCard() {
		boolean exist = false;
		HttpServletRequest request = ServletActionContext.getRequest();
		String cardName = request.getParameter("cardName");
		logger.info("-----------------------------------checkCard------------");
		cards = cardManager.getAllCards();
		for (Card card : cards) {
			if (card.getCardName().equals(cardName)) {
				exist = true;
				break;
			}
		}
		if (exist) {
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		} else {
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"0".getBytes()));
		}
		return SUCCESS;
	}
	
	
	public String checkCardType() {
		
		if(cardId.equals("0")){
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"0".getBytes()));
		}else {
			card = cardManager.getCard(Long.valueOf(cardId));
			String cardType = card.getCardType()+"";
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					cardType.getBytes()));
		}
		
		return SUCCESS;
	}
	
	public String saveCards() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String cardName = request.getParameter("cardName");
			String cardFee = request.getParameter("cardFee");
			String comment = request.getParameter("comments");
			String cardType = request.getParameter("cardType");
			String cardTimes = request.getParameter("cardTimes");
			logger.info("----------------------------------------saveCards--------");
			Card card = new Card(cardName, Float.valueOf(cardFee), comment);
			if(!cardTimes.equals("")){
				card.setCardTimes(Integer.valueOf(cardTimes));
			}
			if(Integer.valueOf(cardType) ==SystemConstant.TIMES_CARD){
				card.setCardType(SystemConstant.TIMES_CARD);
			}else if(Integer.valueOf(cardType) ==SystemConstant.PRIVATE_CLASS_CARD){
				card.setCardType(SystemConstant.PRIVATE_CLASS_CARD);
			}else if(Integer.valueOf(cardType) ==SystemConstant.PERIOD_CARD){
				card.setCardType(SystemConstant.PERIOD_CARD);
			}
			cardManager.addCard(card);
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		}
		return SUCCESS;
	}
	public String toUpdateCard() {
		try {
			card = cardManager.getCard(Long.valueOf(cardId));
			logger.info("--------------toUpdateCard--------"+cardId + "   "+card);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String deleteCard() {

		try {
			logger.info("-----------------------delete card--------"+ cardId);
			cardManager.deleteCard(Long.valueOf(cardId));
			inputStream = new BufferedInputStream(new ByteArrayInputStream("0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		}
		return SUCCESS;
	}
	public String updateCard() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String cardName = request.getParameter("cardName");
			String cardFee = request.getParameter("cardFee");
			String comment = request.getParameter("comments");
			Card card = cardManager.getCard(Long.valueOf(cardId));
			card.setCardFee(Float.valueOf(cardFee));
			card.setCardName(cardName);
			card.setComments(comment);
			logger.info("----------------------------------------update card--------"+card);
			cardManager.updateCard(card);
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		}
		return SUCCESS;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardId() {
		return cardId;
	}

	public List<Card> getCards() {
		return cards;
	}

	public int getCardNum() {
		return cardNum;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setCardManager(CardManager cardManager) {
		this.cardManager = cardManager;
	}
	public Card getCard() {
		return card;
	}
}
