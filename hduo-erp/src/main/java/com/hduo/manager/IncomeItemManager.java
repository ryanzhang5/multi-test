package com.hduo.manager;

import java.util.List;

import com.hduo.pojo.IncomeItem;

public interface IncomeItemManager {

	List<IncomeItem> getAllIncomeItems();

	IncomeItem getIncomeItem(long id);

	void addIncomeItem(IncomeItem incomeItem);

	void deleteIncomeItem(long id);

	void updateIncomeItem(IncomeItem incomeItem);

}
