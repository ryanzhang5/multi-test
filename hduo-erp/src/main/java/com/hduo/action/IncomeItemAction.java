package com.hduo.action;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hduo.manager.ClientManager;
import com.hduo.manager.IncomeItemManager;
import com.hduo.manager.ProductManager;
import com.hduo.pojo.Client;
import com.hduo.pojo.IncomeItem;
import com.hduo.pojo.Product;
import com.hduo.util.Utils;
import com.opensymphony.xwork2.ActionSupport;

public class IncomeItemAction extends ActionSupport {
	private final static Logger logger = Logger
			.getLogger(IncomeItemAction.class);
	private IncomeItemManager incomeItemManager;
	private ProductManager productManager;
	private List<IncomeItem> incomeItems = null;
	private List<Product> products;
	private String from;
	private String to;

	public String saveIncomeItems() {
		logger.info("---------------++++++++++++++++++++++++++++++++++++++++++++");
		try {
			HttpServletRequest request = ServletActionContext.getRequest();

			String[] ids = request.getParameterValues("incomeItem_id");
			String[] status = request.getParameterValues("status");
			String[] allProductName = request.getParameterValues("productName");
			String[] allNum = request.getParameterValues("num");
			String[] allPrice = request.getParameterValues("price");
			String[] allItemPrice = request.getParameterValues("item_price");
			String[] allComments = request.getParameterValues("comments");
			String incomeDate = request.getParameter("incomeDate");
			logger.info("---------------" + incomeDate);
			incomeItemManager.saveUpdateDeleteIncomeItems(ids, status,
					allProductName, allNum, allPrice,allItemPrice, allComments, incomeDate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String incomeItemsStatistic() {
		HttpServletRequest request = ServletActionContext.getRequest();
		 from = request.getParameter("from");
		 to = request.getParameter("to");
		Date toDate = null;
		logger.info("from is " + from+ " to is " + to);
		if(to == null || to.equals("")){
			toDate = new Date();
			to = Utils.dateToString(toDate);
			from = Utils.dateOneMonthAgo(toDate);
			logger.info("from is " + from+ " to is " + to);
		}
		logger.info("-------------------static incomeitems from " + from
				+ " to " + to);
		this.products = productManager.getAllProducts();
		this.incomeItems = incomeItemManager.incomeItemsStatistic(from, to);
		return SUCCESS;
	}

	public String getAllIncomeItems() {

		this.incomeItems = incomeItemManager.getAllIncomeItems();
		return SUCCESS;
	}

	public List<IncomeItem> getIncomeItems() {
		return incomeItems;
	}

	public void setIncomeItemManager(IncomeItemManager incomeItemManager) {
		this.incomeItemManager = incomeItemManager;
	}

	public String getFrom() {
		return from;
	}
	
	public String getTo() {
		return to;
	}
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
	public List<Product> getProducts() {
		return products;
	}
}
