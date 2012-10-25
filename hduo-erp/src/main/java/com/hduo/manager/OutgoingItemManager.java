package com.hduo.manager;

import java.util.List;

import com.hduo.pojo.OutgoingItemVO;

public interface OutgoingItemManager {

	List<OutgoingItemVO> getOutgoingTemplate(String clientId);

}
