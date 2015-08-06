package org.jesery;

import java.io.Serializable;

/** 
 * @author zhangyanhui(mail:yanhui.zhang@pactera.com)
 * @version create time：2014-9-18
 * 
 */
public class Refund implements Serializable{
private String changeID;
private float actualRefundAmount;

public Refund(){}



public Refund(String changeID, float actualRefundAmount) {
	super();
	this.changeID = changeID;
	this.actualRefundAmount = actualRefundAmount;
}



public String getChangeID() {
	return changeID;
}
public void setChangeID(String changeID) {
	this.changeID = changeID;
}
public float getActualRefundAmount() {
	return actualRefundAmount;
}
public void setActualRefundAmount(float actualRefundAmount) {
	this.actualRefundAmount = actualRefundAmount;
}


}
