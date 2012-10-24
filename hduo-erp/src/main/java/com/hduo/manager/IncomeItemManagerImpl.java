package com.hduo.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.hduo.dao.IncomeItemDao;
import org.hduo.dao.ProductDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.IncomeItem;
import com.hduo.pojo.Product;
import com.hduo.util.Utils;

public class IncomeItemManagerImpl implements IncomeItemManager {
	private final static Logger logger = Logger
			.getLogger(IncomeItemManagerImpl.class);
	private IncomeItemDao incomeItemDao;
	private ProductDao productDao;

	@Transactional
	public List<IncomeItem> getAllIncomeItems() {
		return this.incomeItemDao.getAllIncomeItems();
	}

	@Transactional
	public IncomeItem getIncomeItem(long id) {
		return this.incomeItemDao.getIncomeItem(id);
	}

	@Transactional
	public void addIncomeItem(IncomeItem incomeItem) {
		this.incomeItemDao.addIncomeItem(incomeItem);
	}

	@Transactional
	public void deleteIncomeItem(long id) {
		this.incomeItemDao.deleteIncomeItem(getIncomeItem(id));
	}

	@Transactional
	public void updateIncomeItem(IncomeItem incomeItem) {
		this.incomeItemDao.updateIncomeItem(incomeItem);
	}

      @Transactional
	public void saveUpdateDeleteIncomeItems(String[] ids, String[] status,
			String[] allProductName, String[] allNum, String[] allPrice,
			String[] allComments, String incomeDate) {
		logger.info("---------------------------------tying to add incomeItem ");
		for (int i = 0; i < ids.length; i++) {
			IncomeItem incomeItem = null;
			String incomeItem_id = ids[i];
			String state = status[i];
			String productId = allProductName[i];
			String num = allNum[i];
			String price = allPrice[i];
			String comments = allComments[i];

			if (incomeItem_id != null && !incomeItem_id.equals("")) {
				if (state != null && state.equals(Utils.UPDATED)) {
				} else if (state != null && state.equals(Utils.DELETED)) {
				}
			} else if (incomeItem_id.equals("")
					&& state.equals(Utils.NEW_UPDATED)) {
				Product product= productDao.getProduct(Long.parseLong(productId));
				incomeItem = new IncomeItem();
				incomeItem.setSum(Integer.valueOf(num));
				incomeItem.setPrice(Float.valueOf(price));
				incomeItem.setComments(comments);
				incomeItem.setDate(Utils.stringToDate(incomeDate));
				incomeItem.setProduct(product);
				logger.info("-----------------tying to add incomeItem "
						+ incomeItem);
				addIncomeItem(incomeItem);
			}
		}

	}
      @Transactional
      public List<IncomeItem> incomeItemsStatistic(String from, String to) {
  		List<IncomeItem> items = incomeItemDao.getIncomeItems(from, to);
  		logger.info("---------incomeitem from "+from +" to "+to+" size is " + items.size() );
    	  return items;
  	}
	public void setIncomeItemDao(IncomeItemDao incomeItemDao) {
		this.incomeItemDao = incomeItemDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	
}
