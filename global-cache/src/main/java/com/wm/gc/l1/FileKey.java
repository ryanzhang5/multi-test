package com.wm.gc.l1;

import java.io.File;

import com.wm.gc.util.GCUtil;


public class FileKey {
  private static int maxDepth = 4;                      // maximum number of directories deep
  private static int chunkSize = 2;                     // characters in one "chunk" <--> directory name
  //private static String dirDelim = File.separator;      // directory delimiter
    
    
  public static String getFileName( String baseDir, CacheKey key, String version )  {
    StringBuffer sb = new StringBuffer( baseDir );
    String      hex = getHexString( key.getKey().getBytes() );

    int nDirs = Math.min( ( hex.length() - 1 ) / chunkSize, maxDepth );
    int start = 0;
    for ( int i = 0; i < nDirs; i++ )
    {
      sb.append( hex.substring( start, start+chunkSize ) );
      sb.append( File.separator );
      start += chunkSize;
    }
    sb.append( hex.substring( start ) );
    
   /* if (version != null && !version.isEmpty()) {
        sb.append(Conf.getVersion(version));
      }*/
    
    return sb.toString();
  }
    
  private static String getHexString( byte[] key )
  {
    if ( key == null ) return null;

    long crc = GCUtil.calcChecksum( key );
    String _crcHexFilename = Long.toHexString( crc );

    return _crcHexFilename.toUpperCase();
  }
  
  public static void main(String args[]){
	  CacheKey key = CacheKey.getInstance("1075869");
	  System.out.println(getFileName("/home/ryhzhang/", key,"1.0"));
  }
  
}
	
