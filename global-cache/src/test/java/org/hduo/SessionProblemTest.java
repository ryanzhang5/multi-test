package org.hduo;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.apache.log4j.Logger;

import junit.framework.TestCase;

import com.wm.gc.l1.Cache;
import com.wm.gc.l1.CacheEntry;
import com.wm.gc.l1.CacheKey;
import com.wm.gc.l1.FileStore;
import com.wm.gc.l1.IStore;
import com.wm.gc.l1.Janitor;
import com.wm.gc.l1.Namespace;
import com.wm.gc.query.bc.BinaryCache;

public class SessionProblemTest  extends TestCase {
    private static Namespace namespace = null;
    private static final int MB = 1024 * 1024;
    private static final int KB = 1024 ;
    private static final String cName = "SearchQuery";
    private static final String strandDir = "/home/cshah/strand/";
    private static boolean isStrand =  true;
    public static final Logger logger = Logger.getLogger(SessionProblemTest.class);
     static{
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
 */             
        	

     }
    
    public void testGet(){
    	Namespace namespace2 = new Namespace("catalog", null, true, true, 0);
    	
    	 try {
    		 logger.debug("start");
    		
    	    for (int i = 0; i < 45; i++) {
    	    	CacheEntry entry = BinaryCache.get(null, namespace2, "mycachename", CacheKey.getInstance(""+i), null);
       		 byte[] bytes = (byte[])(entry.getData());
       	      ObjectInputStream objis;
       			try {
       				objis = new ObjectInputStream(new ByteArrayInputStream(bytes));
       				  byte[] bytes2 = (byte[])objis.readObject();
       			        String s = new String(bytes2);
       			        System.out.println("############################################" + s);
       			} catch (Exception e1) {
       				e1.printStackTrace();
       			}
       	      
			}
    	      
    		logger.debug("end");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	/*Namespace namespace2 = new Namespace("catalog", null, true, true, 0);
    	
    	 try {
    		 
    		 for (int k=0; k<30; k++){
                 try{
                	 CacheEntry entry = BinaryCache.get(null, namespace2, "mycachename", CacheKey.getInstance(""+k), null);
                	 byte[] bytes = (byte[])(entry.getData());
         	        System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTt" +bytes.length);
         	        ObjectInputStream objis;
         			try {
         				objis = new ObjectInputStream(new ByteArrayInputStream(bytes));
         				  byte[] bytes2 = (byte[])objis.readObject();
         			        String s = new String(bytes2);
         			        System.out.println("############################################" + s);
         			} catch (Exception e1) {
         				e1.printStackTrace();
         			}
         	        
         	      
         		
                 } catch (Exception exp) {
                     exp.printStackTrace();
                   }
                 }
    		 
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}*/
    }
    
    
    
    /*  
    
    
    public static void cacheGet(String key) {
       

        try{
        	 Object result = null;
             CacheKey ck = CacheKey.getInstance(key); 	
        	Cache cache = new Cache();
        	Namespace namespace = new Namespace("catalog", "jdbcpool_catalog", true, true, 0);
    		FileStore store = new FileStore(namespace, "mycachename");
        	cache.setStore(store);
        	
	        CacheEntry ce = BinaryCache.getFromL1Only(cache,ck);

	        byte[] bytes = (byte[])(ce.getData());
	        
	        ObjectInputStream objis = new ObjectInputStream(new ByteArrayInputStream(bytes));
	        
	        byte[] bytes2 = (byte[])objis.readObject();
	        String s = new String(bytes2);
	        System.out.println("###" + s);
	        
	        for (int i = 0; i < bytes2.length; i++) {
				
			}
	        
	        
	     //   System.out.println("----------"+new String((byte[])(ce.getData())));
        
        } catch (Exception e) {e.printStackTrace();}
    }

 
    public static void cachePut(String key,  byte[] r) {
        CacheKey ck = CacheKey.getInstance(key);
        try{
            //BinaryCache.put(namespace, cName, ck, r, 86400L, null);
        	Cache cache = new Cache();
        	Namespace namespace = new Namespace("catalog", "jdbcpool_catalog", true, true, 0);
    		FileStore store = new FileStore(namespace, "mycachename");
        	cache.setStore(store);
        	BinaryCache.putIntoL1(cache, ck, r, 86400L, null);
        	
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
          
          /*
          totalThreads = 5;
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
                                   String key = String.valueOf(k);
                                   System.out.println("write into file with key " + key);
                                   SessionProblem.cachePut(key, sb.toString().getBytes());
                                   Thread.sleep(10);
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

         
          
       
          
          
    //      int size = MB * 3;
          int size = 10 * 3;
          StringBuffer sb = new StringBuffer(size);
          for (int b=0; b < size; b++) {
              sb.append("a");
          }
          
          for (int k=0; k<30; k++){
              try{
                String key = String.valueOf(k);
                System.out.println("write into file with key " + key);
                SessionProblem.cachePut(key, sb.toString().getBytes());
                if(k==25){
                	System.out.println("###"+sb.toString());
                }
                Thread.sleep(10);
                sb.append("aaaaaaaa"+k);

              } catch (Exception exp) {
                  exp.printStackTrace();
                }
              }
         
          try {
        	  
              Thread.sleep(3*1000);
              } catch (Exception e) { e.printStackTrace();}

           
          
          SessionProblem.cacheGet("25");
        } catch (Exception e) {e.printStackTrace();}
        
    }
*/  
}
     