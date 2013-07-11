package org.hduo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;

import com.hduo.pojo.Customer;
import com.hduo.pojo.PracticeRecord;
import com.hduo.pojo.TrackItem;
import com.hduo.util.Utils;

public class CustomerDao extends Dao {
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomer() {
		List<Customer> customers = (List<Customer>) getSession().getNamedQuery(
				"customer.selectAll").list();
		return customers;
	}

	public void addCustomer(Customer customer) {
		getSession().save(customer);
	}

	public void updateCustomer(Customer customer) {
		getSession().update(customer);
	}

	public Customer getCustomer(long id) {
		return (Customer) getSession().get(Customer.class, id);
	}

	public void deleteCustomer(long id) {
		getSession().delete(getCustomer(id));
	}
	@SuppressWarnings("unchecked")
	public List<TrackItem> getTrackItemByCustomerId(long customerId) {
		String sql = "select track_date,comment from track_item where customer_id = "
				+ customerId + " order by track_date desc";
		List<Object[]> objs=(List<Object[]>)(getSession().createSQLQuery(sql).list());
		List<TrackItem>  items = new ArrayList<TrackItem>();
		for (Object[] myobjs : objs) {
			TrackItem item = new TrackItem();
			if(myobjs[0] != null){
				item.setTrackDate(Utils.stringToDate(myobjs[0].toString()));
			}
			if(myobjs[1] != null){
				item.setComment((String)myobjs[1]);
			}
			items.add(item);
		}
		return items;
	}
	@SuppressWarnings("unchecked")
	public List<PracticeRecord> getPracticeRecordByCustomerId(long customerId) {
		String sql = "select practice_date,class_name,comment from practice_record where customer_id = "
				+ customerId + " order by practice_date desc";
		List<Object[]> objs=(List<Object[]>)(getSession().createSQLQuery(sql).list());
		List<PracticeRecord>  items = new ArrayList<PracticeRecord>();
		for (Object[] myobjs : objs) {
			PracticeRecord item = new PracticeRecord();
			System.out.println("+++++++++++++++++++++++++++"+myobjs[0] + " +++++++++"+myobjs[1]);
			if(myobjs[0] != null){
				item.setPracticeDate(Utils.stringToDate(myobjs[0].toString()));
			}
			if(myobjs[1] != null){
				item.setClassName((String)myobjs[1]);
			}
			if(myobjs[2] != null){
				item.setComment((String)myobjs[2]);
			}
			items.add(item);
		}
		return items;
	}
	
}
