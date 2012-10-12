package com.hduo.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.hduo.manager.ClientManager;
import com.hduo.pojo.Client;
import com.opensymphony.xwork2.ActionSupport;

public class ClientAction extends ActionSupport {
	private final static Logger logger = Logger.getLogger(ClientAction.class);
	private ClientManager clientManager;
	private String clientId;
	private List<Client> clients;

	public String getAllClients() {
		logger.info("---------------------" + (clientManager==null));
		clients = clientManager.getAllClients();
		return SUCCESS;
	}

	public void setClientManager(ClientManager clientManager) {
		logger.info("++++++++++++++++++++++++++++++++++++  client got set " + clientManager );
		this.clientManager = clientManager;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public List<Client> getClients() {
		return clients;
	}
}
