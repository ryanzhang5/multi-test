package org.hibernate.hduo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hduo.manager.ClientManager;
import com.hduo.pojo.Client;

public class HibernateTest {

	public static void main(String[] args) {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
		 ClientManager clientManager = (ClientManager) ctx.getBean("clientManager");

	Client client = new Client();
	client.setClientName("李经理5");
	client.setStoreName("华清池5");
	client.setAddress("中山南路5");
	client.setMobilePhone("13817567788");
	client.setDeskPhone("4357679");
	client.setComments("抓紧催款5");
	
	clientManager.addClient(client);
	}

}
