package com.hduo.manager;

import java.util.List;

import com.hduo.pojo.InventoryItem;
import com.hduo.pojo.Product;

public interface InventoryItemManager {

	List<InventoryItem> getAllInventoryItems();

	InventoryItem getInventoryItem(long id);

	InventoryItem getInventoryItemByProduct(Product product);

	void saveOrUpdateInventoryItem(InventoryItem inventoryItem);

	void saveUpdateInventoryItem(String[] ids, String[] status,
			String[] allreal_num, String[] allComments);

}
