package com.wm.gc.l1;

public class Namespace {
	private String name;
	private String jdbcalias;
	private boolean isSecure;
	private boolean isL1Active;
	private int size;
	public Namespace(String name, String jdbcalias, boolean isSecure,
			boolean isL1Active, int size) {
		super();
		this.name = name;
		this.jdbcalias = jdbcalias;
		this.isSecure = isSecure;
		this.isL1Active = isL1Active;
		this.size = size;
	}
	
	
	
	public String getName(){
		return this.name;
	}
	
}
