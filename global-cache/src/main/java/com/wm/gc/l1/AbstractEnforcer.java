package com.wm.gc.l1;

public abstract class AbstractEnforcer extends Janitor{
	protected double utilization;
	protected long minAccessCount;
	protected long maxIdleMillis;
	public AbstractEnforcer (double targetUtilization,long minAccessCount,long maxIdleMillis){
		this.utilization = targetUtilization;
		this.minAccessCount = minAccessCount;
		this.maxIdleMillis = maxIdleMillis;
	}
	
	public void clean (long targetSize){}
	
	
	
	
	public double getUtilization() {
		return utilization;
	}
	public void setUtilization(double utilization) {
		this.utilization = utilization;
	}
	public long getMinAccessCount() {
		return minAccessCount;
	}
	public void setMinAccessCount(long minAccessCount) {
		this.minAccessCount = minAccessCount;
	}
	public long getMaxIdleMillis() {
		return maxIdleMillis;
	}
	public void setMaxIdleMillis(long maxIdleMillis) {
		this.maxIdleMillis = maxIdleMillis;
	}
	
	
	
}
