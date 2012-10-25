package com.hduo.manager;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hduo.dao.InventoryItemDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.InventoryItem;
import com.hduo.pojo.Product;
import com.hduo.util.Utils;

public class InventoryItemManagerImpl implements InventoryItemManager {
	private final static Logger logger = Logger
			.getLogger(InventoryItemManagerImpl.class);
	private InventoryItemDao inventoryItemDao;

	@Transactional
	public List<InventoryItem> getAllInventoryItems() {
		return inventoryItemDao.getAllInventoryItems();
	}

	@Transactional
	public InventoryItem getInventoryItem(long id) {
		return inventoryItemDao.getInventoryItem(id);
	}

	@Transactional
	public void saveOrUpdateInventoryItem(InventoryItem inventoryItem) {
		inventoryItemDao.saveOrUpdateInventoryItem(inventoryItem);
	}

	@Transactional
	public InventoryItem getInventoryItemByProduct(Product product) {
		return inventoryItemDao.getInventoryItemByProduct(product);
	}

	public void setInventoryItemDao(InventoryItemDao inventoryItemDao) {
		this.inventoryItemDao = inventoryItemDao;
	}

	@Transactional
	public void saveUpdateInventoryItem(String[] ids, String[] status,
			String[] allreal_num, String[] allComments) {
		for (int i = 0; i < ids.length; i++) {
			InventoryItem item = null;
			String item_id = ids[i];
			String state = status[i];
			String real_num = allreal_num[i];
			String comments = allComments[i];
			
			if (item_id != null && !item_id.equals("")) {
				if (state != null && state.equals(Utils.UPDATED)) {
					item = getInventoryItem(Long.valueOf(item_id));
					item.setComments(comments);
					item.setSum(Integer.valueOf(real_num));
					item.setDate(Utils.stringToDate(Utils
							.dateToString(new Date())));
					logger.info("-----------------tying to update inventory_item "
							+ item);
					saveOrUpdateInventoryItem(item);
				} else if (state != null && state.equals(Utils.DELETED)) {
				}
			} else if (item_id.equals("") && state.equals(Utils.NEW_UPDATED)) {
			}
		}

	}

}
