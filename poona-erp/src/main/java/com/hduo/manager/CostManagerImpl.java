package com.hduo.manager;

import java.util.List;

import org.hduo.dao.CostDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.Cost;

public class CostManagerImpl implements CostManager {
	private CostDao costDao;
	
	@Transactional
	public List<Cost> getAllCosts() {
		return costDao.getAllCosts();
	}
	
	@Transactional
	public Cost getCost(long id) {
		return costDao.getCost(id);
	}
	@Transactional
	public void addCost(Cost cost) {
		costDao.addCost(cost);
	}
	@Transactional
	public void deleteCost(long id) {
		costDao.deleteCost(getCost(id));
	}
	@Transactional
	public void updateCost(Cost cost) {
		costDao.updateCost(cost);
	}
	@Transactional
	public List<Cost> getAllCosts(String costFrom, String costTo) {
		return costDao.getAllCosts(costFrom, costTo);
	}
	public CostDao getCostDao() {
		return costDao;
	}

	public void setCostDao(CostDao costDao) {
		this.costDao = costDao;
	}

	

   
	
	
}
