package org.hduo.dao;

import java.util.List;

import com.hduo.pojo.Client;
import com.hduo.pojo.IncomeItem;

public class IncomeItemDao extends Dao {

	public void addIncomeItem(IncomeItem incomeItem) {
		getSession().save(incomeItem);
	}

	public IncomeItem getIncomeItem(Long id) {
		return (IncomeItem) getSession().get(IncomeItem.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<IncomeItem> getAllIncomeItems() {
		List<IncomeItem> incomeItems = (List<IncomeItem>) getSession()
				.getNamedQuery("income_item.selectAll").list();
		return incomeItems;
	}

	public void deleteIncomeItem(IncomeItem incomeItem) {
		getSession().delete(incomeItem);
	}

	public void updateIncomeItem(IncomeItem incomeItem) {
		getSession().update(incomeItem);
	}
}
