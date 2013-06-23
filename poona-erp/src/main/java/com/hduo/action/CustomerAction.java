package com.hduo.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hduo.manager.CardManager;
import com.hduo.manager.CustomerManager;
import com.hduo.pojo.Card;
import com.hduo.pojo.Customer;
import com.hduo.pojo.NonPaiedCustomerVO;
import com.hduo.pojo.PaiedCustomerVO;
import com.hduo.pojo.PracticeRecord;
import com.hduo.pojo.TrackItem;
import com.hduo.util.Utils;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerAction extends ActionSupport {
	private final static Logger logger = Logger.getLogger(CustomerAction.class);
	private CustomerManager customerManager;
	private String customerId;
	private List<Customer> customers;
	private List<NonPaiedCustomerVO> nonPaidCustomers;
	private List<PaiedCustomerVO> paidCustomers;
	private InputStream inputStream;
	private Customer customer;
	private List<TrackItem> trackItemList;
	private CardManager cardManager;
	private List<Card> cards;
	private List<PracticeRecord> practiceRecords;

	public String getPaiedCustomer() {
		try {
			customers = customerManager.getPaidCustomers();
			paidCustomers = new ArrayList<PaiedCustomerVO>();
			PaiedCustomerVO paiedCustomerVO = null;
			for (Customer customer : customers) {
				
				paiedCustomerVO = new PaiedCustomerVO();
				
				paiedCustomerVO.setId(customer.getId());
				paiedCustomerVO.setName(customer.getName());
				paiedCustomerVO.setAddress(customer.getAddress());
				paiedCustomerVO.setMobilePhone(customer.getMobilePhone());
				paiedCustomerVO.setComments(customer.getComments());
				paiedCustomerVO.setFrom(customer.getFrom());
				paiedCustomerVO.setTo(customer.getTo());
				paiedCustomerVO.setLeftTimes(customer.getLeftTimes());
				paiedCustomerVO.setBuyTimes(customer.getBuyTimes());
				paiedCustomerVO.setRealPay(customer.getRealPay());
				paiedCustomerVO.setCard(customer.getCard());
				
				paiedCustomerVO.setSex(customer.getSex());
				paiedCustomerVO.setNationality(customer.getNationality());
				paiedCustomerVO.setCompany(customer.getCompany());
				paiedCustomerVO.setCareer(customer.getCareer());
				paiedCustomerVO.setDeskPhone(customer.getDeskPhone());
				
				paidCustomers.add(paiedCustomerVO);
			}
			cards = cardManager.getAllCards();
			logger.info("----------getPaiedCustomer-----------" + paidCustomers.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getNonPaidCustomer() {
		customers = customerManager.getNonPaidCustomers();
		nonPaidCustomers = new ArrayList<NonPaiedCustomerVO>();
		NonPaiedCustomerVO nonPaiedCustomerVO = null;
		for (Customer customer : customers) {
			
			nonPaiedCustomerVO = new NonPaiedCustomerVO();
			
			nonPaiedCustomerVO.setId(customer.getId());
			nonPaiedCustomerVO.setName(customer.getName());
			nonPaiedCustomerVO.setAddress(customer.getAddress());
			nonPaiedCustomerVO.setMobilePhone(customer.getMobilePhone());
			nonPaiedCustomerVO.setComments(customer.getComments());
			nonPaiedCustomerVO.setTrackTimes(customer.getTrackItems().size());
			
			nonPaiedCustomerVO.setSex(customer.getSex());
			nonPaiedCustomerVO.setNationality(customer.getNationality());
			nonPaiedCustomerVO.setCompany(customer.getCompany());
			nonPaiedCustomerVO.setCareer(customer.getCareer());
			nonPaiedCustomerVO.setDeskPhone(customer.getDeskPhone());
			
			nonPaidCustomers.add(nonPaiedCustomerVO);
		}
		logger.info("---------------------" + nonPaidCustomers.size());
		return SUCCESS;
	}

	public String checkCustomerPractice() {
		practiceRecords = customerManager.getPracticeRecordByCustomerId(Long.valueOf(customerId));
		logger.info("----------checkCustomerPractice-----------" + practiceRecords);
		return SUCCESS;
	}
	public String toAddPracticeRecord() {
		logger.info("----------------------toAddPracticeRecord-----------" + customerId);
		return SUCCESS;
	}
	
	
	
	public String toAddCustomer() {
		return SUCCESS;
	}
	public String toAddTrack() {
		return SUCCESS;
	}
	public String toBuyCard() {
		customer=customerManager.getCustomer(Long.valueOf(customerId));
		cards = cardManager.getAllCards();
		return SUCCESS;
	}
	
	
	public String checkCustomer() {
		boolean exist = false;
		HttpServletRequest request = ServletActionContext.getRequest();
		String customerName = request.getParameter("name");
		logger.info("-----------------------------------customerName------------"
				+ customerName);
		customers = customerManager.getAllCustomers();
		for (Customer customer : customers) {
			if (customer.getName().equals(customerName)) {
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

	public String saveCustomer() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String mobilePhone = request.getParameter("mobilePhone");
			String comments = request.getParameter("comments");
			
			String sex = request.getParameter("sex");
			String nationality = request.getParameter("nationality");
			String company = request.getParameter("company");
			String career = request.getParameter("career");
			String deskPhone = request.getParameter("deskPhone");
			
			Customer customer = new Customer();
			
			customer.setName(name);
			customer.setAddress(address);
			customer.setMobilePhone(mobilePhone);
			customer.setComments(comments);
			customer.setPaied(false);
			
			customer.setCareer(career);
			customer.setDeskPhone(deskPhone);
			customer.setCompany(company);
			customer.setNationality(nationality);
			customer.setSex(sex);
			
			TrackItem item = new TrackItem();
			item.setTrackDate(new Date());
			customer.getTrackItems().add(item);
			
			
			logger.info("----------------------------------------saveCustomer--------"+customer);
			customerManager.addCustomer(customer);
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		}
		return SUCCESS;
	}

	public String toUpdateNonPaidCustomer() {
		try {
			customer = customerManager.getCustomer(Long.valueOf(customerId));
			
			logger.info("--------------toUpdateCustomer--------" + customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String toUpdatePaidCustomer() {
		try {
			customer = customerManager.getCustomer(Long.valueOf(customerId));
			cards = cardManager.getAllCards();
			logger.info("--------------toUpdatePaidCustomer--------" + customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String getTrackItems() {
		try {
			trackItemList =  customerManager.getTrackItemByCustomerId(Long.valueOf(customerId));
			logger.info("--------------getTrackItems--------" + trackItemList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String addTrack() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String datepicker = request.getParameter("datepicker");
			String comments = request.getParameter("comments");
			
			customer = customerManager.getCustomer(Long.valueOf(customerId));
			
			TrackItem item = new TrackItem();
			item.setTrackDate(Utils.stringToDate(datepicker));
			item.setComment(comments);
			
			customer.getTrackItems().add(item);
			customerManager.updateCustomer(customer);
			logger.info("--------------addTrack--------" );
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"0".getBytes()));
		} catch (Exception e) {
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String addPracticeRecord() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String practiceDatepicker = request.getParameter("practiceDatepicker");
			String className = request.getParameter("className");
			String comments = request.getParameter("comments");
			
			customer = customerManager.getCustomer(Long.valueOf(customerId));
			
			PracticeRecord record = new PracticeRecord();
			
			record.setPracticeDate(Utils.stringToDate(practiceDatepicker));
			record.setClassName(className);
			record.setComment(comments);
			
			customer.getPracticeRecords().add(record);
			customerManager.updateCustomer(customer);
			logger.info("--------------addPracticeRecord--------"+ record);
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"0".getBytes()));
		} catch (Exception e) {
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	
	public String buyCard() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String cardType = request.getParameter("myCardType");
			String cardNumber = request.getParameter("cardNumber");
			String realPay = request.getParameter("realPay");
			String buyTimes = request.getParameter("buyTimes");
			String from = request.getParameter("from");
			String to = request.getParameter("to");
			String comments = request.getParameter("comments");
			
			Card card = cardManager.getCard(Long.valueOf(cardType));
			customer = customerManager.getCustomer(Long.valueOf(customerId));
			System.out.println(card+"                   "+cardType);
			customer.setCard(card);
			customer.setLeftTimes(card.getCardTimes());
			customer.setCardNumber(cardNumber);
			if(!realPay.equals("")){
				customer.setRealPay(Float.valueOf(realPay));
			}
			if(card.getCardTimes()>0){
				customer.setLeftTimes(Integer.valueOf(card.getCardTimes()));
				customer.setBuyTimes(Integer.valueOf(card.getCardTimes()));
			}
			if(!from.equals("")){
				customer.setFrom(Utils.stringToDate(from));
			}
			if(!to.equals("")){
				customer.setTo(Utils.stringToDate(to));
			}
			
			
			
			customer.setComments(comments);
			customer.setPaied(true);
			
			customerManager.updateCustomer(customer);

			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"0".getBytes()));
		} catch (Exception e) {
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String deleteCustomer() {

		try {
			logger.info("-----------------------delete customer--------"
					+ customerId);
			customerManager.deleteCustomer(Long.valueOf(customerId));
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		}
		return SUCCESS;
	}

	public String updateCustomer() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String mobilePhone = request.getParameter("mobilePhone");
			String comments = request.getParameter("comments");
			
			
			String sex = request.getParameter("sex");
			String career = request.getParameter("career");
			String deskPhone = request.getParameter("deskPhone");
			String nationality = request.getParameter("nationality");
			String company = request.getParameter("company");
			
			logger.info("----------------------------------------update customer--------"
					+ customerId);

			Customer customer = customerManager.getCustomer(Long
					.valueOf(customerId));
			customer.setName(name);
			customer.setMobilePhone(mobilePhone);
			customer.setAddress(address);
			customer.setComments(comments);
			
			customer.setSex(sex);
			customer.setNationality(nationality);
			customer.setCareer(career);
			customer.setDeskPhone(deskPhone);
			customer.setCompany(company);
			
			customerManager.updateCustomer(customer);
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		}
		return SUCCESS;
	}

	
	
	public String updatePaidCustomer() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String leftTimes = request.getParameter("leftTimes");
			String comments = request.getParameter("comments");
			
			
			customer = customerManager.getCustomer(Long.valueOf(customerId));
			customer.setLeftTimes(Integer.valueOf(leftTimes));
			customer.setComments(comments);
			
			customerManager.updateCustomer(customer);
			
			
			logger.info("----------------------------------------updatePaidCustomer--------"
					+ customerId);

			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	
	public void setCustomerManager(CustomerManager customerManager) {
		this.customerManager = customerManager;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<NonPaiedCustomerVO> getNonPaidCustomers() {
		return nonPaidCustomers;
	}

	public List<TrackItem> getTrackItemList() {
		return trackItemList;
	}
	public void setCardManager(CardManager cardManager) {
		this.cardManager = cardManager;
	}
	public List<Card> getCards() {
		return cards;
	}
	public List<PaiedCustomerVO> getPaidCustomers() {
		return paidCustomers;
	}
	public List<PracticeRecord> getPracticeRecords() {
		return practiceRecords;
	}
}
