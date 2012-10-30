package org.hduo.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.hduo.pojo.OutgoingItem;

public class OutgoingItemDao extends Dao {
	private final static Logger logger = Logger
			.getLogger(OutgoingItemDao.class);

	public Object[] executeSQL(String sql) {
		return (Object[]) getSession().createSQLQuery(sql).uniqueResult();
	}

	public void addOutgoingItem(OutgoingItem outgoingItem) {
		getSession().save(outgoingItem);
	}

	@SuppressWarnings("unchecked")
	public List<OutgoingItem> getOutgoingItems(String from, String to,
			String clientId, String productId) {

		StringBuilder sb = new StringBuilder();
		sb.append("from OutgoingItem where outgoing_date >= '");
		sb.append(from);
		sb.append("' and outgoing_date <= '");
		sb.append(to);
		sb.append("'");

		if (clientId != null && !clientId.equals("") && !clientId.equals("-1")) {
			sb.append(" and client_id = ");
			sb.append(clientId);
		}
		if (productId != null && !productId.equals("") && !productId.equals("-1")) {
			sb.append(" and product_id = ");
			sb.append(productId);
		}

		sb.append(" order by date desc");

		String query = sb.toString();

		logger.info("----------outgoing static query is " + query);
		List<OutgoingItem> items = (List<OutgoingItem>) getSession()
				.createQuery(query).list();
		return items;
	}

}
