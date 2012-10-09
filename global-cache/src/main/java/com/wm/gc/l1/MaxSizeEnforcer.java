package com.wm.gc.l1;

import org.apache.log4j.Logger;

public class MaxSizeEnforcer extends AbstractEnforcer {
	static Logger logger = Logger.getLogger(MaxSizeEnforcer.class);
	public MaxSizeEnforcer (double targetUtilization,long minAccessCount,long maxIdleMillis){
		super(targetUtilization, minAccessCount, maxIdleMillis);
	}
	public void impEventPut(CacheKey key,CacheEntry entry){
		CacheStat stat = getCache().getCacheStat();
		long size = stat.getSize();
		long maxSize = stat.getMaxSize();
		logger.info("MaxSizeEnforcer.impEventPut before clean --------current size "+ size + "  maxSize = " + maxSize);
		if(size > maxSize){
			super.clean((long)(maxSize*utilization));
		}
		logger.info("MaxSizeEnforcer.impEventPut after clean --------current size "+ size + "  maxSize = " + maxSize);
	}
}
