package com.hduo.manager;

import java.util.ArrayList;
import java.util.List;

import org.hduo.dao.CustomerDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.Customer;
import com.hduo.pojo.NonPaiedCustomerVO;
import com.hduo.pojo.PaiedCustomerVO;
import com.hduo.pojo.PracticeRecord;
import com.hduo.pojo.TrackItem;

public class CustomerManagerImpl implements CustomerManager {
	private CustomerDao customerDao;

	@Transactional
	public List<Customer> getPaidCustomers() {
		List<Customer> all = this.customerDao.getAllCustomer();
		List<Customer> paidCustomers = new ArrayList<Customer>();
		for (Customer customer : all) {
			if (customer.isPaied()) {
				paidCustomers.add(customer);
			}
		}
		return paidCustomers;
	}

	@Transactional
	public List<Customer> getNonPaidCustomers() {
		List<Customer> all = this.customerDao.getAllCustomer();
		List<Customer> nonPaidCustomers = new ArrayList<Customer>();
		for (Customer customer : all) {
			if (!customer.isPaied()) {
				nonPaidCustomers.add(customer);
			}
		}
		return nonPaidCustomers;
	}
	
	@Transactional
	public List<NonPaiedCustomerVO> getNonPaidCustomers(String from,String to) {
	return this.customerDao.getNonPaiedCustomer(from,to);
	}
	
	@Transactional
	public	List<PaiedCustomerVO> getPaidCustomers(String from, String to){
		return this.customerDao.getPaidCustomers(from,to);
	}

	@Transactional
	public void addCustomer(Customer customer) {
		this.customerDao.addCustomer(customer);
	}

	@Transactional
	public void updateCustomer(Customer customer) {
		this.customerDao.updateCustomer(customer);
	}

	@Transactional
	public Customer getCustomer(long id) {
		return customerDao.getCustomer(id);
	}
	@Transactional
	public List<Customer> getAllCustomers(){
		return customerDao.getAllCustomer();
	}
	@Transactional
	public void deleteCustomer(long id){
		customerDao.deleteCustomer(id);
	}
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	@Transactional
	public List<TrackItem> getTrackItemByCustomerId(long customerId) {
		return customerDao.getTrackItemByCustomerId(customerId);
	}
	@Transactional
	public List<PracticeRecord> getPracticeRecordByCustomerId(long customerId) {
		return customerDao.getPracticeRecordByCustomerId(customerId);
	}

}
