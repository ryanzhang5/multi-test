package com.hduo.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hduo.manager.CostManager;
import com.hduo.pojo.Cost;
import com.hduo.util.Utils;
import com.opensymphony.xwork2.ActionSupport;

public class CostAction extends ActionSupport {

	private final static Logger logger = Logger.getLogger(CostAction.class);
	private CostManager costManager;
	private InputStream inputStream;
	private List<Cost>  costList ;
	private String costId;
	private String costFrom ;
	private String costTo ;
	private float totalCost;
	public String getAllCosts() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String costFrom = request.getParameter("costFrom");
		String costTo = request.getParameter("costTo");
		if(costFrom != null && !costFrom.equals("") && costTo != null && !costTo.equals("")){
			costList = costManager.getAllCosts(costFrom,costTo);
		}else {
			costList = costManager.getAllCosts();
		}
		for (Cost  myCost : costList) {
			totalCost += myCost.getCostAmount();
		}
		logger.info("---------------------" + costList.size());
		return SUCCESS;
	}

	public String toAddCost() {
		return SUCCESS;
	}
	
	public String saveCost() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String costItem = request.getParameter("costItem");
			String costDate = request.getParameter("costDate");
			String costAmount = request.getParameter("costAmount");
			String comment = request.getParameter("comment");
			
			logger.info("----------------------------------------saveCost--------");
			Cost cost = new Cost(costItem,Utils.stringToDate(costDate), Float.valueOf(costAmount),comment);
			costManager.addCost(cost);
			inputStream = new BufferedInputStream(new ByteArrayInputStream("0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream("1".getBytes()));
		}
		return SUCCESS;
	}
	
	public String deleteCost() {

		try {
			logger.info("-----------------------delete cost--------"+ costId);
			costManager.deleteCost(Long.valueOf(costId));
			inputStream = new BufferedInputStream(new ByteArrayInputStream("0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		}
		return SUCCESS;
	}
	
	
	public List<Cost> getCostList() {
		return costList;
	}
	
	public void setCostManager(CostManager costManager) {
		this.costManager = costManager;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getCostId() {
		return costId;
	}

	public void setCostId(String costId) {
		this.costId = costId;
	}

	public String getCostFrom() {
		return costFrom;
	}

	public String getCostTo() {
		return costTo;
	}

	public void setCostFrom(String costFrom) {
		this.costFrom = costFrom;
	}

	public void setCostTo(String costTo) {
		this.costTo = costTo;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	
	
}
