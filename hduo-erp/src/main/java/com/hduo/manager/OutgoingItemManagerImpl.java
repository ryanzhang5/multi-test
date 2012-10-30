package com.hduo.manager;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hduo.dao.ClientDao;
import org.hduo.dao.IncomeItemDao;
import org.hduo.dao.InventoryItemDao;
import org.hduo.dao.OutgoingItemDao;
import org.hduo.dao.ProductDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.Client;
import com.hduo.pojo.InventoryItem;
import com.hduo.pojo.OutgoingItem;
import com.hduo.pojo.OutgoingItemVO;
import com.hduo.pojo.Product;
import com.hduo.util.Utils;

public class OutgoingItemManagerImpl implements OutgoingItemManager {
	private final static Logger logger = Logger
			.getLogger(OutgoingItemManagerImpl.class);
	private OutgoingItemDao outgoingItemDao;
	private ClientDao clientDao;
	private ProductDao productDao;
	private InventoryItemDao inventoryItemDao;
	private IncomeItemDao incomeItemDao;
	private InventoryItemManager inventoryItemManager;

	@Transactional
	public List<OutgoingItemVO> getOutgoingTemplate(String clientId) {
		try {
			Client client = clientDao.getClient(Long.parseLong(clientId));
			List<Product> products = productDao.getAllProducts();
			List<OutgoingItemVO> items = new ArrayList<OutgoingItemVO>();
			for (Product product : products) {
				String sql = "SELECT price,sum FROM outgoing_item where product_id = "
						+ product.getId()
						+ " and client_id = "
						+ clientId
						+ " order by outgoing_date desc limit 1";
				Object[] objs = outgoingItemDao.executeSQL(sql);
				Float price = 0f;
				Integer sum = 0;
				if (objs != null) {
					price = (Float) (objs[0]);
					sum = (Integer) (objs[1]);
				}
				logger.info("-------price is " + price + "  and sum is " + sum);

				sql = "SELECT price FROM income_item where product_id = "
						+ product.getId()
						+ " order by income_date desc limit 1";

				Object obj = incomeItemDao.executeSQL(sql);

				Float latestIncomePrice = 0f;
				if (obj != null) {
					latestIncomePrice = (Float) (obj);
				}

				InventoryItem inventory = inventoryItemDao
						.getInventoryItemByProduct(product);
				OutgoingItemVO vo = new OutgoingItemVO();
				vo.setId(product.getId());
				vo.setPrice(price);
				vo.setProductName(product.getProductName());
				vo.setInventoryNum(inventory.getSum() + "");
				vo.setLatestIncomePrice(latestIncomePrice);
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

	public void setIncomeItemDao(IncomeItemDao incomeItemDao) {
		this.incomeItemDao = incomeItemDao;
	}

	@Transactional
	public void saveUpdateDeleteOutgoingItems(String clientId,
			String outgoingDate, String[] ids, String[] status,
			String[] allPrice, String[] allSum, String[] allItemPrice,
			String[] allComments) {
		Client client = clientDao.getClient(Long.parseLong(clientId));
		Date outdate = Utils.stringToDate(outgoingDate);
		for (int i = 0; i < ids.length; i++) {

			String product_id = ids[i];
			String state = status[i];
			String price = allPrice[i];
			String sum = allSum[i];
			String itemPrice = allItemPrice[i];
			String comments = allComments[i];
			Product product = productDao.getProduct(Long.parseLong(product_id));

			if (product_id != null && !product_id.equals("")
					&& Integer.parseInt(sum) > 0
					&& !state.equals(Utils.DELETED)) {
				OutgoingItem item = new OutgoingItem();
				item.setProduct(product);
				item.setClient(client);
				item.setSum(Integer.parseInt(sum));
				item.setPrice(Float.parseFloat(price));
				item.setItemPrice(Float.parseFloat(itemPrice));
				item.setComments(comments);
				item.setDate(outdate);
				outgoingItemDao.addOutgoingItem(item);
			}

			InventoryItem item = inventoryItemManager
					.getInventoryItemByProduct(product);

			item.setSum(item.getSum() - Integer.valueOf(sum));
			inventoryItemManager.saveOrUpdateInventoryItem(item);

		}

	}

	@Transactional
	public List<OutgoingItem> outgoingItemsStatistic(String from, String to,
			String clientId, String productId) {

		return outgoingItemDao.getOutgoingItems(from, to, clientId, productId);
	}

	public void setInventoryItemManager(
			InventoryItemManager inventoryItemManager) {
		this.inventoryItemManager = inventoryItemManager;
	}
}
