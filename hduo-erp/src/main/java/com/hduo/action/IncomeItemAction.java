package com.hduo.action;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hduo.manager.ClientManager;
import com.hduo.manager.IncomeItemManager;
import com.hduo.pojo.Client;
import com.hduo.pojo.IncomeItem;
import com.hduo.util.Utils;
import com.opensymphony.xwork2.ActionSupport;

public class IncomeItemAction extends ActionSupport {
	private final static Logger logger = Logger
			.getLogger(IncomeItemAction.class);
	private IncomeItemManager incomeItemManager;
	List<IncomeItem> incomeItems = null;

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

}
