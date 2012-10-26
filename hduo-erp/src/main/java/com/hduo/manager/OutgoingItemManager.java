package com.hduo.manager;

import java.util.List;

import com.hduo.pojo.OutgoingItemVO;

public interface OutgoingItemManager {

	List<OutgoingItemVO> getOutgoingTemplate(String clientId);

	void saveUpdateDeleteOutgoingItems(String clientId, String outgoingDate,
			String[] ids, String[] status, String[] allPrice, String[] allSum,
			String[] allItemPrice, String[] allComments);

}
