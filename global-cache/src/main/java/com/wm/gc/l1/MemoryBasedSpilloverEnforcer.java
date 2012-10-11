package com.wm.gc.l1;

import org.apache.log4j.Logger;

public class MemoryBasedSpilloverEnforcer extends AbstractEnforcer {
	static Logger logger = Logger.getLogger(MemoryBasedSpilloverEnforcer.class);

	public MemoryBasedSpilloverEnforcer(double targetUtilization,
			long minAccessCount, long maxIdleMillis) {
		super(targetUtilization, minAccessCount, maxIdleMillis);
	}

	public void impEventPut(CacheKey key, CacheEntry entry) {
		CacheStat stat = getCache().getCacheStat();
		long size = stat.getSize();
		long maxSize = stat.getMaxSize();
		logger.info("before clean --------current size " + size
				+ "  maxSize = " + maxSize);
		if (size > maxSize) {
			new Thread(new Sweeper()).start();
		}
		logger.debug("after clean --------current size " + size
				+ "  maxSize = " + maxSize);
	}

	public void clean(long targetSize) {
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

		for (int i = 0; i < 10 && (cs.getSize() > targetSize); i++) {

			logger.info("----------------------++++++++++++++++++++++++now in for to clean current is "
					+ cs.getSize() + "   target is " + targetSize);
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

	class Sweeper implements Runnable {

		public void run() {
			logger.info("Sweeper will start to clean ");
			clean((long) (getCache().getCacheStat().getSize() * utilization));
		}
	}
}
