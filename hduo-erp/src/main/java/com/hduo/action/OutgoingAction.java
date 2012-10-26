package com.hduo.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hduo.manager.ClientManager;
import com.hduo.manager.OutgoingItemManager;
import com.hduo.manager.ProductManager;
import com.hduo.pojo.Client;
import com.hduo.pojo.OutgoingItem;
import com.hduo.pojo.OutgoingItemVO;
import com.hduo.pojo.Product;
import com.hduo.util.Utils;
import com.opensymphony.xwork2.ActionSupport;

public class OutgoingAction extends ActionSupport {
	private final static Logger logger = Logger.getLogger(OutgoingAction.class);
	private ClientManager clientManager;
	private List<Client> clients;
	private List<Product> products;
	private ProductManager productManager;
	private OutgoingItemManager outgoingItemManager;
	private List<OutgoingItemVO> items;
	private List<OutgoingItem> printItems = new ArrayList<OutgoingItem>();
	private String clientId;
	private Client client;
	private String outDate;
	private String totalNum;
	private String totalPrice;
	private String printComment;

	public String outgoing() {

		clients = clientManager.getAllClients();
		if (clientId == null || clientId.equals("")) {
			logger.info("--------------------------send product to clientid "
					+ clientId);
			items = new ArrayList<OutgoingItemVO>();
		} else {
			logger.info("--------------------------send product to clientid "
					+ clientId);
			items = outgoingItemManager.getOutgoingTemplate(clientId);
			for (OutgoingItemVO itemVO : items) {
				logger.info("-------------------------------" + itemVO);
			}
		}
		return SUCCESS;
	}

	public String outgoingPrint() {

		HttpServletRequest request = ServletActionContext.getRequest();

		String clientId = request.getParameter("clientName");
		String[] ids = request.getParameterValues("product_id");
		String[] status = request.getParameterValues("status");

		String[] allPrice = request.getParameterValues("price");
		String[] allSum = request.getParameterValues("sum");
		String[] allItemPrice = request.getParameterValues("item_price");
		String[] allComments = request.getParameterValues("comments");

		client = clientManager.getClient(Long.parseLong(clientId));
		outDate = request.getParameter("outgoingDate");
		totalNum = request.getParameter("total_num");
		totalPrice= request.getParameter("total_price");
		printComment = request.getParameter("print_comment");
		for (int i = 0; i < ids.length; i++) {
			String product_id = ids[i];
			String state = status[i];
			String price = allPrice[i];
			String sum = allSum[i];
			String itemPrice = allItemPrice[i];
			String comments = allComments[i];
			Product product = productManager.getProduct(Long
					.parseLong(product_id));

			if (product_id != null && !product_id.equals("")
					&& Integer.parseInt(sum) > 0
					&& !state.equals(Utils.DELETED)) {
				OutgoingItem item = new OutgoingItem();
				item.setProduct(product);
				item.setSum(Integer.parseInt(sum));
				item.setPrice(Float.parseFloat(price));
				item.setItemPrice(Float.parseFloat(itemPrice));
				item.setComments(comments);
				printItems.add(item);
			}
		}

		return SUCCESS;
	}

	public String saveoutgoingItem() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String[] ids = request.getParameterValues("product_id");
		String[] status = request.getParameterValues("status");

		String[] allPrice = request.getParameterValues("price");
		String[] allSum = request.getParameterValues("sum");
		String[] allItemPrice = request.getParameterValues("item_price");
		String[] allComments = request.getParameterValues("comments");

		String clientId = request.getParameter("clientName");
		String outgoingDate = request.getParameter("outgoingDate");

		outgoingItemManager.saveUpdateDeleteOutgoingItems(clientId,
				outgoingDate, ids, status, allPrice, allSum, allItemPrice,
				allComments);

		return SUCCESS;
	}

	public void setClientManager(ClientManager clientManager) {
		this.clientManager = clientManager;
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public List<Client> getClients() {
		return clients;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setOutgoingItemManager(OutgoingItemManager outgoingItemManager) {
		this.outgoingItemManager = outgoingItemManager;
	}

	public List<OutgoingItemVO> getItems() {
		return items;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getOutDate() {
		return outDate;
	}

	public Client getClient() {
		return client;
	}

	public List<OutgoingItem> getPrintItems() {
		return printItems;
	}
	public String getTotalNum() {
		return totalNum;
	}
	
	public String getTotalPrice() {
		return totalPrice;
	}
	public String getPrintComment() {
		return printComment;
	}
}
