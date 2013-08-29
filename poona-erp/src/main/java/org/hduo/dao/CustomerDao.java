package org.hduo.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hduo.pojo.Customer;
import com.hduo.pojo.NonPaiedCustomerVO;
import com.hduo.pojo.PaiedCustomerVO;
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
	
	@SuppressWarnings("unchecked")
	public List<NonPaiedCustomerVO> getNonPaiedCustomer(String from,String to) {
		String sql = "select a.id,a.name,a.sex,a.mobilePhone,a.desk_phone,a.address,a.create_date,a.comments,count(b.id) as track_count"
	                +" from customer a left join track_item b on a.id = b.customer_id"
					+" where a.paied = 0  and a.create_date <= '"+to + "' and a.create_date >= '"+ from 
					+"' group by a.id,a.name,a.sex,a.mobilePhone,a.desk_phone,a.address,a.create_date,a.comments "
					+" order by a.create_date desc";
		System.out.println("sql is " + sql);
		
		List<Object[]> objs=(List<Object[]>)(getSession().createSQLQuery(sql).list());
		List<NonPaiedCustomerVO>  items = new ArrayList<NonPaiedCustomerVO>();
		for (Object[] myobjs : objs) {
			NonPaiedCustomerVO item = new NonPaiedCustomerVO();
			item.setId(((BigInteger)myobjs[0]).longValue());
			item.setName((String)myobjs[1]);
			item.setSex((String)myobjs[2]);
			if(myobjs[3] != null ){
				item.setMobilePhone((String)myobjs[3]);
			}
			if(myobjs[4] != null ){
				item.setDeskPhone((String)myobjs[4]);
			}
			if(myobjs[5] != null ){
				item.setAddress((String)myobjs[5]);
			}
			if(myobjs[6] != null ){
				item.setCreateDate(Utils.dateToString((Date)myobjs[6]));
			}
			if(myobjs[7] != null ){
				item.setComments((String)myobjs[7]);
			}
			item.setTrackTimes(((BigInteger)myobjs[8]).intValue());

			items.add(item);
		}
		return items;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaiedCustomerVO> getPaidCustomers(String from,String to) {
		String sql ="select a.id,a.name,a.cardNumber,a.realPay,a.leftTimes,a.from_date,a.to_date,a.mobilePhone,a.desk_phone,a.comments,b.cardType,b.cardName from customer a left join card b on a.card_id = b.id "
			+" where a.paied = 1  and a.from_date <= '"+to + "' and a.from_date >= '"+ from  + "' " 
		    + " order  by a.from_date desc ";

		System.out.println("sql is " + sql);
		
		List<Object[]> objs=(List<Object[]>)(getSession().createSQLQuery(sql).list());
		List<PaiedCustomerVO>  items = new ArrayList<PaiedCustomerVO>();
		for (Object[] myobjs : objs) {
			PaiedCustomerVO item = new PaiedCustomerVO();
			item.setId(((BigInteger)myobjs[0]).longValue());
			item.setName((String)myobjs[1]);
			if(myobjs[2] != null ){
			item.setCardNumber((String)myobjs[2]);
			}
			if(myobjs[3] != null ){
				item.setRealPay((Float)myobjs[3]);
			}
			if(myobjs[4] != null ){
				item.setLeftTimes((Integer)myobjs[4]);
			}
			if(myobjs[5] != null ){
				item.setFrom((Date)myobjs[5]);
			}
			if(myobjs[6] != null ){
				item.setTo((Date)myobjs[6]);
			}
			if(myobjs[7] != null ){
				item.setMobilePhone((String)myobjs[7]);
			}
			if(myobjs[8] != null ){
				item.setDeskPhone((String)myobjs[8]);
			}
			if(myobjs[9] != null ){
				item.setComments((String)myobjs[9]);
			}
			if(myobjs[10] != null ){
				item.setCardType((Integer)myobjs[10]);
			}
			if(myobjs[11] != null ){
				item.setCardName((String)myobjs[11]);
			}
			items.add(item);
		}
		return items;
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
