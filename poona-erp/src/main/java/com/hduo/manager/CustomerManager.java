package com.hduo.manager;

import java.util.List;

import com.hduo.pojo.Customer;
import com.hduo.pojo.PracticeRecord;
import com.hduo.pojo.TrackItem;

public interface CustomerManager {

	List<Customer> getPaidCustomers();

	List<Customer> getNonPaidCustomers();

	List<Customer> getAllCustomers();

	void addCustomer(Customer customer);

	void updateCustomer(Customer customer);

	Customer getCustomer(long id);

	void deleteCustomer(long id);

	List<TrackItem> getTrackItemByCustomerId(long customerId);

	List<PracticeRecord> getPracticeRecordByCustomerId(long customerId);

}
