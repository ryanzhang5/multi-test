package com.hduo.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.hduo.dao.IncomeItemDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.IncomeItem;

public class IncomeItemManagerImpl implements IncomeItemManager {
	private final static Logger logger = Logger
			.getLogger(IncomeItemManagerImpl.class);
	private IncomeItemDao incomeItemDao;

	@Transactional
	public List<IncomeItem> getAllIncomeItems() {
		return this.incomeItemDao.getAllIncomeItems();
	}

	@Transactional
	public IncomeItem getIncomeItem(long id) {
		return this.incomeItemDao.getIncomeItem(id);
	}

	@Transactional
	public void addIncomeItem(IncomeItem incomeItem) {
		this.incomeItemDao.addIncomeItem(incomeItem);
	}

	@Transactional
	public void deleteIncomeItem(long id) {
		this.incomeItemDao.deleteIncomeItem(getIncomeItem(id));
	}

	@Transactional
	public void updateIncomeItem(IncomeItem incomeItem) {
		this.incomeItemDao.updateIncomeItem(incomeItem);
	}

	public void setIncomeItemDao(IncomeItemDao incomeItemDao) {
		this.incomeItemDao = incomeItemDao;
	}

}
