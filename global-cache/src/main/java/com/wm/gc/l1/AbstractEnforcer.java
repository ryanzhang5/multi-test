package com.wm.gc.l1;

import org.apache.log4j.Logger;

public abstract class AbstractEnforcer extends Janitor {
	public static final Logger logger = Logger
			.getLogger(AbstractEnforcer.class);
	protected double utilization;
	protected long minAccessCount;
	protected long maxIdleMillis;

	public AbstractEnforcer(double targetUtilization, long minAccessCount,
			long maxIdleMillis) {
		this.utilization = targetUtilization;
		this.minAccessCount = minAccessCount;
		this.maxIdleMillis = maxIdleMillis;
	}

	protected void clean(long targetSize) {
		IStore store = cache.getStore();
		CacheStat cs = cache.getCacheStat();
		long oldSize = cs.getSize();
		long now = System.currentTimeMillis();
		long minAccessCount = this.minAccessCount;
		long maxIdleTime = maxIdleMillis;
		long minAccessDelta = (long) (minAccessCount * 0.1);
		long maxIdleDelta = (long) (maxIdleTime * 0.1);
		long minAccessStop = minAccessCount + (minAccessDelta * 10);
		long maxIdleStop = 0;

		CacheKey keys[] = cache.getAllKeys();
		long reclaimedCount = 0;
		long expiredCount = 0;
		long idleCount = 0;
		long accessCount = 0;
		long originalSize = cs.getSize();
		
		while (cs.getSize() > targetSize) {
			logger.info("------------------------------now in while--trying to clean current is " + cs.getSize()+ "   target is " + targetSize);
			for (int keyIndex = 0; keyIndex < keys.length; keyIndex++) {
				CacheKey key = keys[keyIndex];
				// FIXME
				/*
				 * Passing version as null is OK, Since version is already
				 * available with key (in memory) we do not have to pass version
				 */
				CacheEntry entry = store.get(key, null);

				if (entry == null)
					continue;

				CacheEntryStat stat = entry.getCacheEntryStat();

				/*
				 * if ( entry.isReclaimed() ) { cache.removeCacheEntry( key,
				 * null ); reclaimedCount++; }
				 */

				logger.debug("----------stat.isExpired(now) "
						+ stat.isExpired(now)
						+ " stat.getAccessCount() < minAccessCount "
						+ (stat.getAccessCount() < minAccessCount)
						+ "  (now - stat.getAccessTime()) > maxIdleTime "
						+ ((now - stat.getAccessTime()) > maxIdleTime));
				if (stat.isExpired(now)) {
					cache.removeCacheEntry(key);
					expiredCount++;
				} else if (stat.getAccessCount() < minAccessCount
						&& cs.getSize() > targetSize) {
					cache.removeCacheEntry(key);
					accessCount++;
				} else if ((now - stat.getAccessTime()) > maxIdleTime
						&& cs.getSize() > targetSize) {
					cache.removeCacheEntry(key);
					idleCount++;
				}
			}

			if (minAccessCount >= minAccessStop && maxIdleTime <= maxIdleStop)
				break;
			minAccessCount += minAccessDelta;
			maxIdleTime -= maxIdleDelta;
		}

		lastCleanDtm = now;
		lastReclaimedCount = reclaimedCount;
		lastExpiredCount = expiredCount;
		lastIdleCount = idleCount;
		lastAccessCount = accessCount;

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
