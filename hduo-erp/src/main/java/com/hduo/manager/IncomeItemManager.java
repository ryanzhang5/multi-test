package com.hduo.manager;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.IncomeItem;

public interface IncomeItemManager {

	List<IncomeItem> getAllIncomeItems();
	List<IncomeItem> incomeItemsStatistic(String from,String to);
	
	IncomeItem getIncomeItem(long id);

	void addIncomeItem(IncomeItem incomeItem);

	void deleteIncomeItem(long id);

	void updateIncomeItem(IncomeItem incomeItem);

	void saveUpdateDeleteIncomeItems(String[] ids, String[] status,
			String[] allProductName, String[] allNum,String[] allPrice, String[] allComments,String incomeDate);

}
