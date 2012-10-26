package org.hduo.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.hduo.manager.IncomeItemManagerImpl;
import com.hduo.pojo.IncomeItem;

public class IncomeItemDao extends Dao {
	private final static Logger logger = Logger.getLogger(IncomeItemDao.class);

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

	@SuppressWarnings("unchecked")
	public List<IncomeItem> getIncomeItems(String from, String to) {

		String query = "from IncomeItem where date >='" + from
				+ "' and date <= '" + to + "' order by date desc";
		logger.info("----------query is " + query);
		List<IncomeItem> incomeItems = (List<IncomeItem>) getSession()
				.createQuery(query).list();
		return incomeItems;
	}
	
	public Object executeSQL(String sql) {
		return (Object) getSession().createSQLQuery(sql).uniqueResult();
	}

	public void deleteIncomeItem(IncomeItem incomeItem) {
		getSession().delete(incomeItem);
	}

	public void updateIncomeItem(IncomeItem incomeItem) {
		getSession().update(incomeItem);
	}
}
