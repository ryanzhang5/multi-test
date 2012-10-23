package com.hduo.manager;

import java.util.List;

import com.hduo.pojo.Product;

public interface ProductManager {

	List<Product> getAllProducts();

	Product getProduct(long id);

	void addProduct(Product product);

	void deleteProduct(long id);

	void updateProduct(Product product);

	void saveUpdateDeleteProducts(String[] ids, String[] status,
			String[] allProductName,
			String[] allUnit, String[] allComments);
}
