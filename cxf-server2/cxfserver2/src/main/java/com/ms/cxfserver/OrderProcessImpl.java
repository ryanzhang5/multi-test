package com.ms.cxfserver;

import javax.jws.WebService;

@WebService(endpointInterface = "com.ms.webservice.OrderProcess",serviceName="order")  
public class OrderProcessImpl implements OrderProcess {  
    public String processOrder(Order1 order) {  
        return "hello world"+order;  
    }  
}