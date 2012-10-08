package com.wm.gc.l1;

import com.wm.corelib.config.AppConfig;

import java.util.Date;

import com.wm.corelib.metric.Counter;
import com.wm.corelib.metric.Gauge;
import com.wm.corelib.metric.IMetricRegistry;
import com.wm.corelib.metric.MetricContainer;
import com.wm.corelib.metric.MetricRegistryFactory;
import com.wm.corelib.metric.jmx.MetricMBeanAdaptor;

public class CacheStat
{
    private static final IMetricRegistry _metricDepot = MetricRegistryFactory.get();
    private final MetricContainer _statWrapper;
    private final Counter _entryCount;
    private final Counter _putAllCount;
    private final Counter _hitAllCount;
    private final Counter _hitSuccessAllCount;
    private final Counter _putCount;
    private final Counter _hitCount;
    private final Counter _hitSuccessCount;
    private final Gauge _size;
    private volatile long _maxSize;
    private final Date _created = new Date();
    private Date _modified = new Date();

    CacheStat(Cache owner)
    {
        if (owner == null) throw new IllegalArgumentException("cache arg is null");
        String namespace = owner.getNamespace().getName();
        String appName = (!namespace.isEmpty() ? namespace + "/" : "") + owner.getName() + "."
                + owner.getClass().getSimpleName();
        _statWrapper = _metricDepot.get(Cache.class, null, appName, true);
        _entryCount = _statWrapper.getCounter("EntryCount");
        _hitCount = _statWrapper.getCounter("HitCount");
        _hitSuccessCount = _statWrapper.getCounter("HitSuccessCount");
        _hitSuccessAllCount = _statWrapper.getCounter("HitSuccessAllCount", true);
        _hitAllCount = _statWrapper.getCounter("HitAllCount", true);
        _putCount = _statWrapper.getCounter("PutCount");
        _putAllCount = _statWrapper.getCounter("PutAllCount", true);
        _size = _statWrapper.getGauge("Size");

        // VF: disable export to Hyperic by default -
        // to avoid excessive metric collections in production
        if (Boolean.valueOf(AppConfig.getInstance().getProperty("com.wm.gc.l1.CacheStat.enableExportToHyperic", "false"))) {
            MetricMBeanAdaptor.createModelMBean(_statWrapper);
        }
    }
    
    

    void releaseResources()
    {
    }

    void eventPut(CacheKey k, CacheEntry e, boolean isNewEntry) // synchronized
    {
        _putCount.increment();
        _putAllCount.increment();
        if (isNewEntry)
        {
            _entryCount.increment();
            _size.increment(e.getSize());
        }
    }

    void eventGet(CacheKey k, CacheEntry e) // synchronized
    {
        _hitCount.increment();
        _hitAllCount.increment();
        if (e != null)
        {
            _hitSuccessCount.increment();
            _hitSuccessAllCount.increment();
        }
    }

    void eventRemoveCacheEntry(CacheKey k, CacheEntry e) // synchronized
    {
        _hitCount.increment();
        _hitAllCount.increment();
        if (e != null)
        {
            _hitSuccessCount.increment();
            _hitSuccessAllCount.increment();
            _entryCount.decrement();
            _size.decrement(e.getSize());
        }
    }

    void eventRemoveAll() // synchronized
    {
        _statWrapper.resetMetrics();
        _modified = new Date();
    }

    public void setMaxSize(long s)
    {
        _maxSize = s;
    }

    public long getEntryCount()
    {
        return _entryCount.getValue();
    }

    public long getSize()
    {
        return _size.getValue();
    }

    public long getHitCount()
    {
        return _hitCount.getValue();
    }

    public long getHitAllCount()
    {
        return _hitAllCount.getValue();
    }

    public long getHitSuccessCount()
    {
        return _hitSuccessCount.getValue();
    }

    public long getHitSuccessAllCount()
    {
        return _hitSuccessAllCount.getValue();
    }

    public long getPutCount()
    {
        return _putCount.getValue();
    }

    public long getPutAllCount()
    {
        return _putAllCount.getValue();
    }

    public long getMaxSize()
    {
        return _maxSize;
    }

    public double getHitRate()
    {
        return ((getHitCount() == 0) ? 0.0 : (double)getHitSuccessCount() / (double)getHitCount());
    }

    public double getHitAllRate()
    {
        return ((getHitAllCount() == 0) ? 0.0 : (double)getHitSuccessAllCount()
                / (double)getHitAllCount());
    }

    public Date getCreated()
    {
        return _created;
    }

    public Date getModified()
    {
        return _modified;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName());
        sb.append("\n{");
        sb.append("\n\tMaxSize         = " + "(" + getMaxSize() + ") " + (getMaxSize() / 1024)
                + "KB");
        sb.append("\n\tSize            = " + "(" + getSize() + ") " + (getSize() / 1024) + "KB");
        sb.append("\n\tEntryCount      = " + getEntryCount());
        sb.append("\n    CUMULATIVE:");
        sb.append("\n\tPutCount        = " + getPutAllCount());
        sb.append("\n\tHitCount        = " + getHitAllCount());
        sb.append("\n\tHitSuccessCount = " + getHitSuccessAllCount());
        sb.append("\n\tHitRate         = " + getHitAllRate());
        sb.append("\n\tCreated Date    = " + getCreated());
        sb.append("\n    SINCE LAST BLOWN:");
        sb.append("\n\tPutCount        = " + getPutCount());
        sb.append("\n\tHitCount        = " + getHitCount());
        sb.append("\n\tHitSuccessCount = " + getHitSuccessCount());
        sb.append("\n\tHitRate         = " + getHitRate());
        sb.append("\n\tModified Date   = " + getModified());
        sb.append("\n}\n");
        return sb.toString();
    }

    public static void main(String args[])
    {
        CacheStat cs = new CacheStat(new ObjectCache(null, "testCache", new RamStore(),
                new Janitor()));
        CacheKey k = null;
        CacheEntry e = null;
        boolean isNewEntry = true;
        System.out.println(cs);
        try
        {
            cs.eventPut(k, e, isNewEntry);
        } catch (NullPointerException err)
        {
        }
        cs.eventGet(k, e);
        cs.eventGet(k, e);
        cs.eventGet(k, e);
        cs.eventGet(k, e);
        System.out.println(cs);
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException er)
        {
        }
        cs.eventRemoveAll();
        try
        {
            cs.eventPut(k, e, isNewEntry);
        } catch (NullPointerException err)
        {
        }
        cs.eventGet(k, e);
        cs.eventGet(k, e);
        cs.eventGet(k, e);
        System.out.println(cs);
    }
}
