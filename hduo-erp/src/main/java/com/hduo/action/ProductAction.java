package com.hduo.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hduo.manager.ClientManager;
import com.hduo.manager.ProductManager;
import com.hduo.pojo.Client;
import com.hduo.pojo.Product;
import com.hduo.util.Utils;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport {
	private final static Logger logger = Logger.getLogger(ProductAction.class);
	private ProductManager productManager;
	private String productId;
	private List<Product> products;
	private int productNum;
	private InputStream inputStream;

	public String getAllProducts() {

		products = productManager.getAllProducts();
		logger.info("---------------------" + products.size());
		this.productNum = products.size();
		logger.info("---------------------" + products.size());
		return SUCCESS;
	}

	public String saveProducts() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("product_id");
		String[] status = request.getParameterValues("status");

		String[] allProductName = request.getParameterValues("productName");
		String[] allUnit = request.getParameterValues("unit");
		String[] allComments = request.getParameterValues("comments");

		productManager.saveUpdateDeleteProducts(ids, status, allProductName,
				allUnit, allComments);

		return SUCCESS;
	}

	public String checkProduct() {
		boolean exist = false;
		HttpServletRequest request = ServletActionContext.getRequest();
		String productName = request.getParameter("productName");
		products = productManager.getAllProducts();
		for (Product product : products) {
			if (product.getProductName().equals(productName)) {
				exist = true;
				break;
			}
		}
		logger.info("-----------++++++++++++++++++++++++++++++++----------"
				+ productName);
		if (exist) {
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		} else {
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"0".getBytes()));
		}
		return SUCCESS;
	}

	public String toAddIncomeItem() {
		logger.info("-----------------------------befor here ");
		products = productManager.getAllProducts();
		logger.info("-----------------------------lenght is " + products.size());
		for (Product product : products) {
			logger.info("---------------" + product.getProductName());

		}
		return SUCCESS;
	}

	public String getProductId() {
		return productId;
	}

	public List<Product> getProducts() {
		return products;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
}
