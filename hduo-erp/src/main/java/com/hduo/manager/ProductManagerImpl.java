package com.hduo.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.hduo.dao.ClientDao;
import org.hduo.dao.ProductDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.action.ClientAction;
import com.hduo.pojo.Client;
import com.hduo.pojo.Product;
import com.hduo.util.Utils;

public class ProductManagerImpl implements ProductManager {
	private final static Logger logger = Logger
			.getLogger(ProductManagerImpl.class);
	private ProductDao productDao;

	@Transactional
	public void saveUpdateDeleteUsers(String[] ids, String[] status,
			String[] allClientName, String[] allStoreName,
			String[] allAddresse, String[] allMobilePhone,
			String[] allDeskPhone, String[] allComments) {

	}

	@Transactional
	public List<Product> getAllProducts() {
		return this.productDao.getAllProducts();
	}

	@Transactional
	public Product getProduct(long id) {
		return this.productDao.getProduct(id);
	}

	@Transactional
	public void addProduct(Product product) {
		this.productDao.addProduct(product);

	}

	@Transactional
	public void deleteProduct(long id) {
		this.productDao.deleteProduct(getProduct(id));

	}

	@Transactional
	public void updateProduct(Product product) {
		this.productDao.updateProduct(product);

	}

	@Transactional
	public void saveUpdateDeleteProducts(String[] ids, String[] status,
			String[] allProductName, String[] allUnit, String[] allComments) {
		for (int i = 0; i < ids.length; i++) {
			Product product = null;
			String product_id = ids[i];
			String state = status[i];
			String productName = allProductName[i];
			String unit = allUnit[i];
			String comments = allComments[i];

			if (product_id != null && !product_id.equals("")) {
				if (state != null && state.equals(Utils.UPDATED)) {
					product = getProduct(Long.valueOf(product_id));
					product.setProductName(productName);
					product.setUnit(unit);
					product.setComments(comments);
					updateProduct(product);
				} else if (state != null && state.equals(Utils.DELETED)) {
					deleteProduct(Long.valueOf(product_id));
				}
			} else if (product_id.equals("") && state.equals(Utils.NEW_UPDATED)) {
				product = new Product(productName, unit, comments);
				addProduct(product);
			}
		}
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

}
