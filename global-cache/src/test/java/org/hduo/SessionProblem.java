package com.wm.gc.test.l1l2;

import java.util.Date;

import com.wm.gc.bc.*;
import com.wm.gc.l1.*;
import com.wm.gc.l1.Namespace;
import com.wm.gc.l1.SpilloverStrandCache;
import com.wm.gc.l1.Janitor;
import com.wm.gc.query.WMQLBCBatchQuery;
import com.wm.gc.l1.MemoryBasedSpilloverEnforcer;
import com.wm.gc.util.*;
import com.wm.gc.query.bc.*;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.concurrent.ScheduledThreadPoolExecutor;
  
import com.wm.corelib.config.AppConfig;

public class SessionProblem {
    private static Namespace namespace = null;
    private static final int MB = 1024 * 1024;
    private static final int KB = 1024 ;
    private static final String cName = "SearchQuery";
    private static final String strandDir = "/home/cshah/strand/";
    private static WMQLBCBatchQuery batchQuery;
    private static boolean isStrand =  true;
    static{
        try{
/* 
          AppConfig.getInstance().setProperty("com.wm.isNettyEnabled", "false");
          AppConfig.getInstance().setProperty("com.wm.l2.server.active", "true");
          AppConfig.getInstance().setProperty("com.wm.l2.l1.active", "true");
          AppConfig.getInstance().setProperty("com.wm.l2.server.host","sdc-session1-e1.walmart.com");
          AppConfig.getInstance().setProperty("com.wm.l2.server.port", "9765");
          AppConfig.getInstance().setProperty("com.wm.l2.server.multihost.host.search","sdc-l2cache1-e1.walmart.com");
          AppConfig.getInstance().setProperty("com.wm.l2.server.multihost.port.search","9765,9765");
          AppConfig.getInstance().setProperty("com.wm.l2.server.multihost.host.search.secondary","sdc-l2cache2-e1.walmart.com");
          AppConfig.getInstance().setProperty("com.wm.l2.server.multihost.port.search.secondary","9765,9765");
          AppConfig.getInstance().setProperty("com.wm.l2.server.multihost.active.search","false");
          AppConfig.getInstance().setProperty("com.wm.l2.get_timeout.search", "30000");          
          AppConfig.getInstance().setProperty("com.wm.l2.logLevel", "4");
          AppConfig.getInstance().setProperty("com.wm.l2.server.adminport", "9766");
          AppConfig.getInstance().setProperty("com.wm.l2.ttl", "86400");            
          AppConfig.getInstance().setProperty("com.wm.l2.get_timeout.search","3000");
          AppConfig.getInstance().setProperty("com.wm.l2.connection_timeout", "1000");
          AppConfig.getInstance().setProperty("com.wm.l2.failover_timeout", "10000");
          AppConfig.getInstance().setProperty("com.wm.l2.socket_connection_timeout", "100");
          AppConfig.getInstance().setProperty("com.wm.l1.use_seperate_threadpool", "true");
 */             
        	
        	AppConfig.getInstance().setProperty("com.wm.isNettyEnabled", "false");
            AppConfig.getInstance().setProperty("com.wm.l2.server.active", "true");
            AppConfig.getInstance().setProperty("com.wm.l2.l1.active", "true");
            AppConfig.getInstance().setProperty("com.wm.l2.server.host","sdc-session1-e1.walmart.com");
            AppConfig.getInstance().setProperty("com.wm.l2.server.port", "9765");
            AppConfig.getInstance().setProperty("com.wm.l2.server.multihost.host.search","sdc-l2cache1-e1.walmart.com");
            AppConfig.getInstance().setProperty("com.wm.l2.server.multihost.port.search","9765,9765");
            AppConfig.getInstance().setProperty("com.wm.l2.server.multihost.host.search.secondary","sdc-l2cache2-e1.walmart.com");
            AppConfig.getInstance().setProperty("com.wm.l2.server.multihost.port.search.secondary","9765,9765");
            AppConfig.getInstance().setProperty("com.wm.l2.server.multihost.active.search","false");
            AppConfig.getInstance().setProperty("com.wm.l2.get_timeout.search", "30000");          
            AppConfig.getInstance().setProperty("com.wm.l2.logLevel", "4");
            AppConfig.getInstance().setProperty("com.wm.l2.server.adminport", "9766");
            AppConfig.getInstance().setProperty("com.wm.l2.ttl", "86400");            
            AppConfig.getInstance().setProperty("com.wm.l2.get_timeout.search","3000");
            AppConfig.getInstance().setProperty("com.wm.l2.connection_timeout", "1000");
            AppConfig.getInstance().setProperty("com.wm.l2.failover_timeout", "10000");
            AppConfig.getInstance().setProperty("com.wm.l2.socket_connection_timeout", "100");
            AppConfig.getInstance().setProperty("com.wm.l1.use_seperate_threadpool", "true");
        	
          // for disabling JMX
          AppConfig.getInstance().setProperty("com.wm.management.disabled", "true");
          AppConfig.getInstance().setProperty("com.wm.l1.max_threadpool", "30");
          AppConfig.getInstance().setProperty("com.wm.l1.use_seperate_threadpool", "true");
          AppConfig.getInstance().setProperty("com.wm.l1.thread_count_per_threadpool", "5");
          AppConfig.getInstance().setProperty("com.wm.corelib.concurrent.threadpool.count","2");
          AppConfig.getInstance().setProperty("com.wm.corelib.concurrent.threadpool.1.type","IO_BOUND");
          AppConfig.getInstance().setProperty("com.wm.corelib.concurrent.threadpool.1.coresize","100");
          AppConfig.getInstance().setProperty("com.wm.corelib.concurrent.threadpool.1.maxsize","700");
          AppConfig.getInstance().setProperty("com.wm.corelib.concurrent.threadpool.1.keepalive","300");
          AppConfig.getInstance().setProperty("com.wm.corelib.concurrent.threadpool.1.queuesize","10000");
          AppConfig.getInstance().setProperty("com.wm.corelib.concurrent.threadpool.2.type","CPU_BOUND");
          AppConfig.getInstance().setProperty("com.wm.corelib.concurrent.threadpool.2.coresize","100");
          AppConfig.getInstance().setProperty("com.wm.corelib.concurrent.threadpool.2.maxsize","200");
          AppConfig.getInstance().setProperty("com.wm.corelib.concurrent.threadpool.2.keepalive","300");
          AppConfig.getInstance().setProperty("com.wm.corelib.concurrent.threadpool.2.queuesize","10000");
          AppConfig.getInstance().setProperty("com.wm.l1.use_seperate_threadpool", "true");
          AppConfig.getInstance().setProperty("com.wm.l1.thread_count_per_threadpool", "5");
          AppConfig.getInstance().setProperty("com.wm.l2.namespaces", "search");          
          AppConfig.getInstance().setProperty("com.wm.l2.l1.active", "true");

            namespace = Namespace.findNamespace("search");
            if(namespace == null){
                System.err.println("Unable to initialize Search L2 namespace It is null");

            }
          
            batchQuery = new WMQLBCBatchQuery(namespace, cName, 86400L);

        }catch(Exception t){
            t.printStackTrace();
        }
    }

    public static Object cacheGet(String key) {
        Object result = null;
        CacheKey ck = CacheKey.getInstance(key);

        try{
	        CacheEntry ce = BinaryCache.get(namespace, cName, ck, null);

	     if ( ce!= null && ce.getData() != null)
        result =  GCUtil.getObjectFromByteStream((byte[])ce.getData());	
        
        
        // result = batchQuery.get(ck);
        } catch (Exception e) {e.printStackTrace();}
        return result;
    }

    /**
	 * helper method to put a result in cache, uses different TTL if Result has
	 * redirect URL (destinationUrl)
	 */
    public static void cachePut(String key,  byte[] r) {
        CacheKey ck = CacheKey.getInstance(key);
        try{
            BinaryCache.put(namespace, cName, ck, r, 86400L, null);
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void main(String[] args){ 
      int totalReq = 50;
      if (args.length >0 ) {
        try {
          totalReq = Integer.parseInt(args[0]);
          isStrand = Boolean.parseBoolean(args[1]);
        } catch (Exception e) {
            System.out.println("wrong arg " + args[0]);
            e.printStackTrace();
          }
        }
                try{
          
          int totalThreads = 10;

          
          /*
          ScheduledThreadPoolExecutor ste = new ScheduledThreadPoolExecutor(totalThreads) ;
          for (int threadnum=0; threadnum <= totalThreads;threadnum++ ) {
            ste.execute(
              new Runnable() {
                            public void run() {
                              // while (true) {
                              for (int kk=0; kk<=100;kk++) {
                               for (int k=0; k<30; k++){
                                try{
                                  String key = String.valueOf("Cache Key:" + k);
                                  Object oo = SessionProblem.cacheGet(key);
                                } catch (Exception exp) {
                                    exp.printStackTrace();
                                  }
                                }
                                
                              }
                              }
                            });
            }

          */
          
          
          totalThreads = 10;
          ScheduledThreadPoolExecutor ste1 = new ScheduledThreadPoolExecutor(totalThreads) ;

          for (int threadnum=0; threadnum <= totalThreads;threadnum++ ) {
            ste1.execute(
              new Runnable() {
                            public void run() {
                                int size = MB * 3;
                                StringBuffer sb = new StringBuffer(size);
                                for (int b=0; b < size; b++) {
                                    sb.append("a");
                                }
                              
                                for (int kk=0; kk<=100;kk++) {
                                // while (true) {
 
                                for (int k=0; k<30; k++){
                                 try{
                                   String key = String.valueOf("Cache Key:" + k);
                                   SessionProblem.cachePut(key, sb.toString().getBytes());
                                   Thread.sleep(100);
                                   sb.append("aaaaaaaa");

                                 } catch (Exception exp) {
                                     exp.printStackTrace();
                                   }
                                 }

                              }
                              }
                            });
            }
        
            try {
            Thread.sleep(Integer.MAX_VALUE);
            } catch (Exception e) { e.printStackTrace();}

        } catch (Exception e) {e.printStackTrace();}
        
    }

}
