package org.hduo.dao;

import java.util.ArrayList;
import java.util.List;

import com.hduo.pojo.Cost;
import com.hduo.pojo.PracticeRecord;
import com.hduo.util.Utils;

public class CostDao extends Dao {

	public void addCost(Cost cost) {
		getSession().save(cost);
	}

	public Cost getCost(Long id) {
		return (Cost) getSession().get(Cost.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cost> getAllCosts() {
		List<Cost> costs = (List<Cost>) getSession().getNamedQuery(
				"cost.selectAll").list();
		return costs;
	}

	@SuppressWarnings("unchecked")
	public List<Cost> getAllCosts(String costFrom, String costTo)  {
		String sql = "select id,cost_amount,cost_date,cost_item,comment from cost where"
	            +" cost_date <= '"+costTo+"' and cost_date >= '"+costFrom+"' order by cost_date asc";
		System.out.println("+++++++++++++++++++++++++++++++++++" + sql);
		
		List<Object[]> objs=(List<Object[]>)(getSession().createSQLQuery(sql).list());
		List<Cost>  items = new ArrayList<Cost>();
		for (Object[] myobjs : objs) {
			Cost item = new Cost();
			if(myobjs[0] != null){
				item.setId(Long.valueOf(myobjs[0].toString()));
			}
			if(myobjs[1] != null){
				item.setCostAmount((Float)myobjs[1]);
			}
			if(myobjs[2] != null){
				item.setCostDate(Utils.stringToDate(myobjs[2].toString()));
			}
			if(myobjs[3] != null){
				item.setCostItem((String)myobjs[3]);
			}
			if(myobjs[4] != null){
				item.setComment((String)myobjs[4]);
			}
			items.add(item);
		}
		return items;
	}
	
	
	public void deleteCost(Cost cost) {
		getSession().delete(cost);
	}

	public void updateCost(Cost cost) {
		getSession().update(cost);
	}
}
