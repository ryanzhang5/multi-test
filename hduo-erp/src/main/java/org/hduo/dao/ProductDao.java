package org.hduo.dao;

import java.util.List;

import com.hduo.pojo.Product;

public class ProductDao extends Dao {

	public void addProduct(Product product) {
		getSession().save(product);
	}

	public Product getProduct(Long id) {
		return (Product) getSession().get(Product.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		List<Product> products = (List<Product>) getSession().getNamedQuery(
				"product.selectAll").list();
		return products;
	}

	public void deleteProduct(Product product) {
		getSession().delete(product);
	}

	public void updateProduct(Product product) {
		getSession().update(product);
	}
}
