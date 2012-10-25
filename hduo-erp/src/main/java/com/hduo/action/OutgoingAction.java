package com.hduo.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hduo.manager.ClientManager;
import com.hduo.manager.OutgoingItemManager;
import com.hduo.manager.ProductManager;
import com.hduo.pojo.Client;
import com.hduo.pojo.OutgoingItemVO;
import com.hduo.pojo.Product;
import com.opensymphony.xwork2.ActionSupport;

public class OutgoingAction extends ActionSupport {
	private final static Logger logger = Logger.getLogger(OutgoingAction.class);
	private ClientManager clientManager;
	private List<Client> clients;
	private List<Product> products;
	private ProductManager productManager;
	private OutgoingItemManager outgoingItemManager;
	List<OutgoingItemVO> items;
	private String clientId;

	public String outgoing() {

		clients = clientManager.getAllClients();
		if (clientId == null || clientId.equals("")) {
			logger.info("--------------------------send product to clientid "
					+ clientId);
			items = new ArrayList<OutgoingItemVO>();
		} else {
			logger.info("--------------------------send product to clientid "
					+ clientId);
			items = outgoingItemManager.getOutgoingTemplate(clientId);
			for (OutgoingItemVO itemVO : items) {
				logger.info("-------------------------------" + itemVO);
			}
		}
		return SUCCESS;
	}

	public void setClientManager(ClientManager clientManager) {
		this.clientManager = clientManager;
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public List<Client> getClients() {
		return clients;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setOutgoingItemManager(OutgoingItemManager outgoingItemManager) {
		this.outgoingItemManager = outgoingItemManager;
	}

	public List<OutgoingItemVO> getItems() {
		return items;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
