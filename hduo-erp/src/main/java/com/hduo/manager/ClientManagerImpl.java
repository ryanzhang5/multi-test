package com.hduo.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.hduo.dao.ClientDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.action.ClientAction;
import com.hduo.pojo.Client;
import com.hduo.util.Utils;

public class ClientManagerImpl implements ClientManager {
	private final static Logger logger = Logger
			.getLogger(ClientManagerImpl.class);
	private ClientDao clientDao;

	@Transactional
	public List<Client> getAllClients() {
		return clientDao.getAllClients();
	}

	public Client getClient(long id) {
		return clientDao.getClient(id);
	}

	@Transactional
	public void addClient(Client client) {
		if (client.getClientName().contains(";;"))
			throw new RuntimeException("not good");
		clientDao.addClient(client);
	}

	@Transactional
	public void deleteClient(long id) {
		Client client = getClient(id);
		clientDao.deleteClient(client);
	}

	@Transactional
	public void updateClient(Client client) {
		clientDao.updateClient(client);

	}

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	@Transactional
	public void saveUpdateDeleteUsers(String[] ids, String[] status,
			String[] allClientName, String[] allStoreName,
			String[] allAddresse, String[] allMobilePhone,
			String[] allDeskPhone, String[] allComments) {
		for (int i = 0; i < ids.length; i++) {
			Client client = null;
			String client_id = ids[i];
			String state = status[i];
			String clientName = allClientName[i];
			String storeName = allStoreName[i];
			String address = allAddresse[i];
			String mobilePhone = allMobilePhone[i];
			String deskPhone = allDeskPhone[i];
			String comments = allComments[i];

			if (client_id != null && !client_id.equals("")) {
				if (state != null && state.equals(Utils.UPDATED)) {
					client = getClient(Long.valueOf(client_id));
					client.setClientName(clientName);
					client.setStoreName(storeName);
					client.setAddress(address);
					client.setMobilePhone(mobilePhone);
					client.setDeskPhone(deskPhone);
					client.setComments(comments);
					logger.info("-----------------tying to update client "
							+ client);
					updateClient(client);
				} else if (state != null && state.equals(Utils.DELETED)) {
					logger.info("-----------------tying to delete client "
							+ client);
					deleteClient(Long.valueOf(client_id));
				}
			} else if (client_id.equals("") && state.equals(Utils.NEW_UPDATED)) {
				client = new Client(clientName, storeName, address,
						mobilePhone, deskPhone, comments);
				logger.info("-----------------tying to add client " + client);
				addClient(client);
			}
		}
	}
}
