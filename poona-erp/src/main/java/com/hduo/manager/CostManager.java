package com.hduo.manager;

import java.util.List;

import com.hduo.pojo.Cost;


public interface CostManager {
	List<Cost> getAllCosts();

	Cost getCost(long id);

	void addCost(Cost cost);

	void deleteCost(long id);

	void updateCost(Cost cost);

	List<Cost> getAllCosts(String costFrom, String costTo);
}
