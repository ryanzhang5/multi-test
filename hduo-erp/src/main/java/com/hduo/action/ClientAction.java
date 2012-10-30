package com.hduo.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hduo.manager.ClientManager;
import com.hduo.pojo.Client;
import com.hduo.util.Utils;
import com.opensymphony.xwork2.ActionSupport;

public class ClientAction extends ActionSupport {
	private final static Logger logger = Logger.getLogger(ClientAction.class);
	private ClientManager clientManager;
	private String clientId;
	private List<Client> clients;
	private int clientNum;
	private InputStream inputStream;

	public String getAllClients() {

		clients = clientManager.getAllClients();
		this.clientNum = clients.size();
		logger.info("---------------------" + clients.size());
		return SUCCESS;
	}

	public String checkClient() {
		boolean exist = false;
		HttpServletRequest request = ServletActionContext.getRequest();
		String clientName = request.getParameter("clientName");
		clients = clientManager.getAllClients();
		for (Client client : clients) {
			if (client.getClientName().equals(clientName)) {
				exist = true;
				break;
			}
		}
		logger.info("-----------++++++++++++++++++++++++++++++++----------"
				+ clientName);
		if (exist) {
			inputStream = new BufferedInputStream(new ByteArrayInputStream("1".getBytes()));
		}else {
			inputStream = new BufferedInputStream(new ByteArrayInputStream("0".getBytes()));
		}
		return SUCCESS;
	}

	public String saveClients() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("client_id");
		String[] status = request.getParameterValues("status");

		String[] allClientName = request.getParameterValues("clientName");
		String[] allStoreName = request.getParameterValues("storeName");
		String[] allAddresse = request.getParameterValues("address");
		String[] allMobilePhone = request.getParameterValues("mobilePhone");
		String[] allDeskPhone = request.getParameterValues("deskPhone");
		String[] allComments = request.getParameterValues("comments");

		clientManager.saveUpdateDeleteUsers(ids, status, allClientName,
				allStoreName, allAddresse, allMobilePhone, allDeskPhone,
				allComments);

		return SUCCESS;
	}

	public void setClientManager(ClientManager clientManager) {
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

	public int getClientNum() {
		return clientNum;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
}
