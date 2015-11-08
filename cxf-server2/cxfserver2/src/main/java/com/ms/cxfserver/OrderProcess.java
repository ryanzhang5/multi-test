package com.ms.cxfserver;

import javax.jws.WebService;

@WebService
public interface OrderProcess {
	String processOrder(Order1 order);
}