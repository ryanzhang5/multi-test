package org.hduo.dao;

public class OutgoingItemDao extends Dao {

	public Object[] executeSQL(String sql) {
		return (Object[]) getSession().createSQLQuery(sql).uniqueResult();
	}
}
