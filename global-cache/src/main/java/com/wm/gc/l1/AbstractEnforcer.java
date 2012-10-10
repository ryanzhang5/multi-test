package com.wm.gc.l1;

import org.apache.log4j.Logger;

public abstract class AbstractEnforcer extends Janitor{
	public static final Logger logger = Logger.getLogger(AbstractEnforcer.class);
	protected double utilization;
	protected long minAccessCount;
	protected long maxIdleMillis;
	public AbstractEnforcer (double targetUtilization,long minAccessCount,long maxIdleMillis){
		this.utilization = targetUtilization;
		this.minAccessCount = minAccessCount;
		this.maxIdleMillis = maxIdleMillis;
	}
	
	 protected void clean( long targetSize )
	  {
	    IStore    store          = cache.getStore();
	    CacheStat cs             = cache.getCacheStat();
	    long      oldSize        = cs.getSize();
	    long      now            = System.currentTimeMillis();
	    long      minAccessCount = this.minAccessCount;
	    long      maxIdleTime    = maxIdleMillis;
	    long      minAccessDelta = (long)(minAccessCount * 0.1);
	    long      maxIdleDelta   = (long)(maxIdleTime    * 0.1);
	    long      minAccessStop  = minAccessCount + (minAccessDelta * 10); //10 times max
	    long      maxIdleStop    = 0;

	    CacheKey keys[] = cache.getAllKeys();
	    long reclaimedCount = 0;
	    long expiredCount   = 0;
	    long idleCount      = 0;
	    long accessCount    = 0;
	    long originalSize = cs.getSize();

	    while ( cs.getSize() > targetSize )
	    {
	      for ( int keyIndex = 0; keyIndex < keys.length; keyIndex++ )
	      {
	        CacheKey   key   = keys[keyIndex];
	        //FIXME
	        /* Passing version as null is OK,
	         Since version is already available with key (in memory)
	         we do not have to pass version
	         */
	        CacheEntry entry = store.get( key,null );

	        if ( entry == null ) continue;

	        CacheEntryStat stat  = entry.getCacheEntryStat();

	        /*if ( entry.isReclaimed() )
	        {
	          cache.removeCacheEntry( key, null );
	          reclaimedCount++;
	        }*/
	        if ( stat.isExpired( now ) )
	        {
	          cache.removeCacheEntry(key);
	          expiredCount++;
	        }
	        // remove entry if its access count is less than minAccessCount
	        else if ( stat.getAccessCount() < minAccessCount && cs.getSize() > targetSize )
	        {
	        	 cache.removeCacheEntry(key);
	          accessCount++;
	        }
	        // remove entry if its idle time is greater than maxIdleTime
	        else if ( (now - stat.getAccessTime()) > maxIdleTime && cs.getSize() > targetSize ) 
	        {
	        	 cache.removeCacheEntry(key);
	          idleCount++;
	        }
	      }

	      if ( minAccessCount >= minAccessStop && maxIdleTime <= maxIdleStop ) 
	        break; // we've done all that's possible

	      minAccessCount += minAccessDelta;
	      maxIdleTime    -= maxIdleDelta;
	    }


	    lastCleanDtm   = now  ;
	    lastReclaimedCount = reclaimedCount;
	    lastExpiredCount   = expiredCount  ;
	    lastIdleCount      = idleCount     ;
	    lastAccessCount    = accessCount   ;
	 
	  }
	
	
	
	
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
