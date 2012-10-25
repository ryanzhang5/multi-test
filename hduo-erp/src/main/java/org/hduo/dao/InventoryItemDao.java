package org.hduo.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.hduo.pojo.IncomeItem;
import com.hduo.pojo.InventoryItem;
import com.hduo.pojo.Product;

public class InventoryItemDao extends Dao {
	private final static Logger logger = Logger
			.getLogger(InventoryItemDao.class);

	public void addInventoryItem(InventoryItem inventoryItem) {
		getSession().save(inventoryItem);
	}

	public InventoryItem getInventoryItem(Long id) {
		return (InventoryItem) getSession().get(InventoryItem.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<InventoryItem> getAllInventoryItems() {
		List<InventoryItem> inventoryItems = (List<InventoryItem>) getSession()
				.getNamedQuery("inventory.selectAll").list();
		return inventoryItems;
	}
	
	public InventoryItem getInventoryItemByProduct(Product product) {
		List<InventoryItem> items = getAllInventoryItems();
		for (InventoryItem inventoryItem : items) {
			if (inventoryItem.getProduct().getProductName()
					.equals(product.getProductName())) {
				return inventoryItem;
			}
		}
		return null;
	}

	public void deleteInventoryItem(InventoryItem inventoryItem) {
		getSession().delete(inventoryItem);
	}

	public void updateInventoryItem(InventoryItem inventoryItem) {
		getSession().update(inventoryItem);
	}
	public void saveOrUpdateInventoryItem(InventoryItem inventoryItem) {
		getSession().saveOrUpdate(inventoryItem);
	}
}
