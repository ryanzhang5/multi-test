package com.hduo.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hduo.manager.InventoryItemManager;
import com.hduo.pojo.InventoryItem;
import com.opensymphony.xwork2.ActionSupport;

public class InventoryItemAction extends ActionSupport {
	private final static Logger logger = Logger
			.getLogger(InventoryItemAction.class);
	private InventoryItemManager inventoryItemManager;
	private List<InventoryItem> inventoryItems = null;

	public String getAllInventoryItems() {
		logger.info("---------------++++++++++++++++++++++++++++++++++++++++++++");
		inventoryItems = inventoryItemManager.getAllInventoryItems();
		return SUCCESS;
	}

	public String saveInventoryItemsStatistic() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("inventoryItem_id");
		String[] status = request.getParameterValues("status");

		String[] allRealNum = request.getParameterValues("real_num");
		String[] allComments = request.getParameterValues("comments");
		logger.info("---------------++++++++++++++++++++saveInventoryItemsStatistic+++++++++++++++++++++++");
		inventoryItemManager.saveUpdateInventoryItem(ids, status, allRealNum,
				allComments);

		return SUCCESS;
	}

	public void setInventoryItemManager(
			InventoryItemManager inventoryItemManager) {
		this.inventoryItemManager = inventoryItemManager;
	}

	public List<InventoryItem> getInventoryItems() {
		return inventoryItems;
	}

}
