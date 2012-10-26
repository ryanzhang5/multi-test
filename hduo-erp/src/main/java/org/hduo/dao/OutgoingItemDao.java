package org.hduo.dao;

import com.hduo.pojo.OutgoingItem;

public class OutgoingItemDao extends Dao {

	public Object[] executeSQL(String sql) {
		return (Object[]) getSession().createSQLQuery(sql).uniqueResult();
	}

	public void addOutgoingItem(OutgoingItem outgoingItem) {
		getSession().save(outgoingItem);
	}
}
