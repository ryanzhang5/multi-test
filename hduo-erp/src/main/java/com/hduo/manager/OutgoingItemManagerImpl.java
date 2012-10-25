package com.hduo.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hduo.dao.ClientDao;
import org.hduo.dao.InventoryItemDao;
import org.hduo.dao.OutgoingItemDao;
import org.hduo.dao.ProductDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.Client;
import com.hduo.pojo.InventoryItem;
import com.hduo.pojo.OutgoingItemVO;
import com.hduo.pojo.Product;

public class OutgoingItemManagerImpl implements OutgoingItemManager {
	private final static Logger logger = Logger
			.getLogger(OutgoingItemManagerImpl.class);
	private OutgoingItemDao outgoingItemDao;
	private ClientDao clientDao;
	private ProductDao productDao;
	private InventoryItemDao inventoryItemDao;

	@Transactional
	public List<OutgoingItemVO> getOutgoingTemplate(String clientId) {
		try {
			Client client = clientDao.getClient(Long.parseLong(clientId));
			List<Product> products = productDao.getAllProducts();
			List<OutgoingItemVO> items = new ArrayList<OutgoingItemVO>();
			for (Product product : products) {
				String sql = "SELECT price,sum FROM outgoing_item where product_id = "
						+ product.getId() + " and client_id = " + clientId
						+ " order by outgoing_date desc limit 1";
				Object[] objs = outgoingItemDao.executeSQL(sql);
				Float price = 0f;
				Integer sum = 0;
				if(objs!= null){
					price = (Float)(objs[0]);
					sum = (Integer)(objs[1]);
				}
				logger.info("-------price is " + price + "  and sum is " + sum);
				InventoryItem inventory = inventoryItemDao.getInventoryItemByProduct(product);
				OutgoingItemVO vo = new OutgoingItemVO();
				vo.setId(product.getId());
				vo.setPrice(price);
				vo.setProductName(product.getProductName());
				vo.setInventoryNum(inventory.getSum()+"");
				vo.setSum(sum);
				items.add(vo);
			}
			return items;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setOutgoingItemDao(OutgoingItemDao outgoingItemDao) {
		this.outgoingItemDao = outgoingItemDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	public void setInventoryItemDao(InventoryItemDao inventoryItemDao) {
		this.inventoryItemDao = inventoryItemDao;
	}
}
