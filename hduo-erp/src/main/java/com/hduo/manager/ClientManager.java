package com.hduo.manager;

import java.util.List;

import com.hduo.pojo.Client;

public interface ClientManager {

	List<Client> getAllClients();

	Client getClient(long id);

	void addClient(Client client);

	void deleteClient(long id);

	void updateClient(Client client);

	void saveUpdateDeleteUsers(String[] ids, String[] status,
			String[] allClientName, String[] allStoreName,
			String[] allAddresse, String[] allMobilePhone,
			String[] allDeskPhone, String[] allComments);
}
