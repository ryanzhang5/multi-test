package com.hduo.manager;

import java.util.List;

import com.hduo.pojo.Client;

public interface ClientManager {

	List<Client> getAllClients();

	Client getClient(long id);
	
	void addClient(Client client);
	
}
