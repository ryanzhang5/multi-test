package com.hduo.manager;

import java.util.List;

import org.hduo.dao.ClientDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.Client;

public class ClientManagerImpl implements ClientManager {
	private ClientDao clientDao;
	
	@Transactional
	public List<Client> getAllClients() {
		return clientDao.getAllClients();
	}

	@Transactional
	public Client getClient(long id) {
		return clientDao.getClient(id);
	}

	
	@Transactional
	public void addClient(Client client) {
		clientDao.addClient(client);
	}
	
	
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
}
