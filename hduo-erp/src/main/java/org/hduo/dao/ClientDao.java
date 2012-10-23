package org.hduo.dao;

import java.util.List;

import com.hduo.pojo.Client;

public class ClientDao extends Dao {

	public void addClient(Client client) {
		getSession().save(client);
	}

	public Client getClient(Long id) {
		return (Client) getSession().get(Client.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<Client> getAllClients() {
		List<Client> clients = (List<Client>) getSession()
				.getNamedQuery("client.selectAll").list();
		return clients;
	}

	public void deleteClient(Client client) {
		getSession().delete(client);
	}

	public void updateClient(Client client) {
		getSession().update(client);
	}
}
