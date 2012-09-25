package rmi.client;

import java.io.Serializable;
import java.math.BigDecimal;

import rmi.compute.Task;

public class Pi implements Task<String>, Serializable {

	public String execute() {
		return "this is what we wantssssssssssssssssssssssssss";
	}

	
}