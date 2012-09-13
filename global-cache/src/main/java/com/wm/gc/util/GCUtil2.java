////////////////////////////////////////////////////////////////////////
//
//            W I R E P R O T O C O L U T I L S  C L A S S
//            ============================================
//
//  This class contains common constants and utils for calculating
//  checksum.
//
//
////////////////////////////////////////////////////////////////////////

package com.wm.gc.util;

import com.wm.gc.l1.Cache;
import com.wm.gc.l1.CacheKey;
import com.wm.gc.l1.Namespace;

import com.wm.gc.l2.L2Worker;
import com.wm.gc.log.WMQLLogger;

import java.io.*;

import java.net.Socket;

import java.nio.ByteBuffer;

import java.util.zip.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

public class GCUtil {

  public static final int  MODE       =0x01;
  public static final int  QUERY_TYPE = 0x02;
  public static final int  POLICY     = 0x03;
  public static final int  NAMESPACE_NAME = 0x04;
  public static final int  CACHE_NAME     = 0x05;
  public static final int  CACHE_KEY      = 0x06;
  public static final int  VERSION  = 0x07;
  public static final int  TRANSACTION_ID     = 0x08;
  

  public static final int  SOH           = 0x01;
  public static final int  STX           = 0x02;
  public static final int  ETX           = 0x03;
  public static final int  LF            = 0x0A;
  public static final int  CR            = 0x0D;
  public static final char SEPARATOR     = ':';
  private static final char KEY_SEP      = '\u0009';
  public static final String CACHEKEY_SEPERATOR = String.valueOf(KEY_SEP);
  public static final Pattern PATTERN = Pattern.compile(CACHEKEY_SEPERATOR);
  
  // Version 0.2 supports read/write to cache.
  public static final int   MAJOR_VER     = 1;
  public static final int   MINOR_VER     = 0;
  public static final int   MAGIC_NUMBER  = 0x574D514C; // "WMQL"

  public static final int   NO_ENCODING   = 0;
  public static final int   GZIP_ENCODE   = 1;
  public static final int   ZLIB_ENCODE   = 2;
  public static final int   BZIP2_ENCODE  = 3;

  // Type of query request.
  public static final byte  WMQL_DB_QUERY_DATA  = 1;
  public static final byte  WMQL_URL_QUERY_DATA = 2;

  // Mode type.
  public static final byte WMQL_REQUEST_TYPE_GET            = 0;
  public static final byte WMQL_REQUEST_TYPE_PUT            = 1;
  public static final byte WMQL_REQUEST_TYPE_REFRESH        = 2;
  public static final byte WMQL_REQUEST_TYPE_REMOVE         = 3;
  public static final byte WMQL_REQUEST_TYPE_STATS          = 4;
  public static final byte WMQL_ADMIN_GLOBAL_EXPIRY         = 5;
  public static final byte WMQL_ADMIN_EXPIRE_CACHE_BY_NAME  = 6;
  public static final byte WMQL_ADMIN_FLUSH_CONNECTIONS     = 7;
  public static final byte WMQL_ADMIN_LOCK_CONNECTIONS      = 8;
  public static final byte WMQL_REQUEST_TYPE_CLEAR_L1       = 9; // needed by dbclient
  public static final byte WMQL_REQUEST_TYPE_APPEND         = 10;
  
  private static final String modeNames[] = { "WMQL_REQUEST_TYPE_GET",
                                              "WMQL_REQUEST_TYPE_PUT",
                                              "WMQL_REQUEST_TYPE_REFRESH",
                                              "WMQL_REQUEST_TYPE_REMOVE",
                                              "WMQL_REQUEST_TYPE_STATS",
                                              "WMQL_ADMIN_GLOBAL_EXPIRY",
                                              "WMQL_ADMIN_EXPIRE_CACHE_BY_NAME",
                                              "WMQL_ADMIN_FLUSH_CONNECTIONS",
                                              "WMQL_ADMIN_LOCK_CONNECTIONS",
                                              "WMQL_REQUEST_TYPE_CLEAR_L1",
                                              "WMQL_REQUEST_TYPE_APPEND"};
	//Added for L2 Admin Management Bean
	public static final String L2ADMIN_MBEAN_NAME = "L2Admin:name=L2AdminMBean";

	//Added for L1 Admin Management Bean
	public static final String L1ADMIN_MBEAN_NAME = "L1Admin:name=L1AdminMBean";

  public static final String EXP_OWNERTHREAD = "Owner Thread processing this key, Waiting for Key ";
  public static final String SERVER_BUSY = "Server too busy..closing client Socket";
  private static final WMQLLogger _logger = WMQLLogger.getInstance();        
  private static final ConcurrentMap<String, String> _hostNameLookupTable = new ConcurrentHashMap<String, String>();
  public static final String PROTOCOL_EXCEPTION = "Protocol Violation.";
  
  public static String modeToString( int mode ) {
    if ( mode < 0 || mode > 10 ) return "Unknown";
    return modeNames[mode];
  }


  // Calculates the checksum using a CRC algorithm. 
  // Returns the checksum, or -1 if an error occurs.
  public static long calcChecksum( byte[] blob ) {
    int i, j;
    long crc_accum = 0;

    if ( !_tableIsGenerated )
      genCRCTable();

    if ( blob == null )
    {
      return -1;
    } // if ( blob == null )
    long len = blob.length;
    for ( j = 0;  j < len; j++ ) {
      i = ( (int) (crc_accum >> 24) ^ ( (int) blob[j] ) )  & 0xff;
      crc_accum = ( crc_accum << 8 ) ^ _crcTable[i];
      if ( debug ) {
        _logger.log(WMQLLogger.LEVEL_INFO, "(crc_accum[" + crc_accum + "]" +
          " >> 24) ^ blob[" + j + "](" + blob[j] + ") & 0xff = " + i );
        _logger.log(WMQLLogger.LEVEL_INFO, "(crc_accum [" + crc_accum +
          " ] << 8) ^ _crcTable[" + i + "] (" + _crcTable[i] +
          ") = " + crc_accum );
      }
    } // for

    if ( debug )
      _logger.log(WMQLLogger.LEVEL_INFO,"Checksum is: " + crc_accum);

    return crc_accum;
  } // calcChocksum()


  /**
   * Converts a hex string (presumably from bytesToHexString())
   * to a raw byte array.
   * <p>
   * Precondition: input string is non-null.<br>
   * Postcondition: return value is non-null (but may be zero length).
   */
  public static byte[] hexStringToBytes( String in ) {

    if ( in == null ) return null;

    int  len   = in.length();
    byte out[] = new byte[len/2];
    int i,y;
    for( i = 0, y = 0; i < len; i+=2, ++y ) {
      out[y] = (byte) ( Integer.parseInt( in.substring( i, i + 2 ), 16 ) - 128 ); /* byte into signed byte*/
    }
    return ( out );
  }

  /**
   * Wrapper for bytesToHexString( byte[], int, int).
   * <p>
   * Precondition: input is non-null.
   */
  public static String bytesToHexString( byte[] in ) {
    if ( in == null ) return null;
    return ( bytesToHexString( in, 0, in.length ) );
  }

  public static byte[] toBytes( Long v ) {
    if ( v == null ) return new byte[0];
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      DataOutputStream      dos = new DataOutputStream( bos );
      dos.writeLong( v.longValue() );
      dos.flush();
      return bos.toByteArray();
    } catch ( IOException e ){
      throw new RuntimeException( e.toString() );
    }
  }

  public static byte[] toBytes( Float v ) {
    if ( v == null ) return new byte[0];
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      DataOutputStream      dos = new DataOutputStream( bos );
      dos.writeFloat( v.floatValue() );
      dos.flush();
      return bos.toByteArray();
    } catch ( IOException e ) {
      throw new RuntimeException( e.toString() );
    }
  }

  public static byte[] toBytes( Double v ) {
    if ( v == null ) return new byte[0];
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      DataOutputStream      dos = new DataOutputStream( bos );
      dos.writeDouble( v.doubleValue() );
      dos.flush();
      return bos.toByteArray();
    } catch ( IOException e ) {
      throw new RuntimeException( e.toString() );
    }
  }

  public static byte[] toBytes( Integer v ) {
    if ( v == null ) return new byte[0];
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      DataOutputStream      dos = new DataOutputStream( bos );
      dos.writeInt( v.intValue() );
      dos.flush();
      return bos.toByteArray();
    } catch ( IOException e ) {
      throw new RuntimeException( e.toString() );
    }
  }

  public static byte[] toBytes( String v ) {
    if ( v == null ) return new byte[0];
    return v.getBytes();
  }



  /**
   * Converts raw bytes into a printable string.
   * <p>
   * Precondition: input is non-null.<br>
   * Precondition: index is greater than or equal to zero.<br>
   * Precondition: (index + length) is less than or equal to input.length.<br>
   * Postcondition: return value is non-null.
   */
  public static String bytesToHexString( byte[] in, int index, int length ) {
    if ( in == null ) return null;

    StringBuffer sb = new StringBuffer();
    for( int i = index; i < index + length; ++i ) {
      int val = (int) in[i] + 128; /* make it unsigned */
      if( val <= 15 ) {
        sb.append("0" + Integer.toHexString( val ) );
      } else {
        sb.append( Integer.toHexString( val ) );
      }
    }
    return ( sb.toString() );
  }

  public static String readString( DataInputStream in ) throws IOException {
    byte[] ba = readBytes( in );
    String rv = null;
    if( ba != null ) {
      rv = new String( ba );
    }
    
    return rv;
  }
  
  public static void readBytes( byte buff[], int count, InputStream in ) throws IOException {
    if ( count > 0 ) {
      int rc = in.read( buff );
      if ( rc != count ) {
        throw new IOException( PROTOCOL_EXCEPTION + " Read " + rc + " bytes when " + count + " is expected" );
      }
    } // if ( count > 0 )
  }

  public static byte[] readBytes( int count, InputStream in ) throws IOException {
    if ( count == -1 ) {
        return null;
    }
    byte rv[] = new byte[count];
    readBytes( rv, count, in );
    return rv;
  }

  public static byte[] readBytes( DataInputStream in ) throws IOException {
    int count = in.readInt();
    return readBytes( count, in );
  }

  public static void writeString( String s, DataOutputStream out ) throws IOException {
    if( s == null ) {
      writeBytes( null, out );
    } else {
      writeBytes( s.getBytes(), out );
    }
  }

  public static void writeBytes( byte[] data, DataOutputStream out ) throws IOException {
    if ( data == null ) {
      out.writeInt( -1 );
      return;
    }
    out.writeInt( data.length );
    out.write   ( data );
  }

  public static void writeObject( Object data, DataOutputStream out ) throws IOException {
    ByteArrayOutputStream bo  = new ByteArrayOutputStream(Conf.getDefaultByteArraySize());
    ObjectOutputStream    oos = new ObjectOutputStream(bo);
    oos.writeObject( data );
    oos.close();
    byte ba[] = bo.toByteArray();
    GCUtil.writeBytes( ba, out );
  }
  
  public static Object readObject( DataInputStream in ) throws IOException, ClassNotFoundException {
    int count = in.readInt();
    byte ba[] = GCUtil.readBytes( count, in );
    ByteArrayInputStream bais = new ByteArrayInputStream( ba   );
    ObjectInputStream    ois  = new ObjectInputStream   ( bais );
    Object rv = ois.readObject();
    return rv;
  }
 
  public static void main( String args[] ) {
    try {
      _logger.log(WMQLLogger.LEVEL_INFO, "US-ASCII bytes.length = " + args[0].getBytes( "US-ASCII" ).length );
      _logger.log(WMQLLogger.LEVEL_INFO, "bytes.length = " + args[0].getBytes().length );
      _logger.log(WMQLLogger.LEVEL_INFO, "bytes = " ); print( args[0].getBytes() );
      _logger.log(WMQLLogger.LEVEL_INFO, "encodeString.bytes.length = " + encodeString( args[0] ) .length );
      _logger.log(WMQLLogger.LEVEL_INFO, "encodeString.bytes.length = " + String.valueOf( encodeString( args[0] ) ) );
      _logger.log(WMQLLogger.LEVEL_INFO, "encodeString.bytes = " ); print( encodeString( args[0] ) );
    } catch ( Exception e ) {
      _logger.log(WMQLLogger.LEVEL_INFO, e.getMessage(),e );
    }
  }

  public static void print( byte v[] ) {
    for ( int i = 0; i < v.length; i++ ) {
      _logger.log(WMQLLogger.LEVEL_INFO, "|" + String.valueOf( v[i] ) + "|" );
    }
    _logger.log(WMQLLogger.LEVEL_INFO, "\n" );
  }

  /**
   * Wrapper to first hex-encode a string, then convert that
   * to ASCII bytes to be safely and easily transported and
   * converted back again into the original string.  Returns
   * a zero-length byte[] if <code>in</code> is null.
   */
  public static byte[] encodeString( String in ) {
    if ( in == null ) return new byte[0];
    byte[] rv = null;
    try {
      rv = bytesToHexString( in.getBytes( "US-ASCII" ) ).getBytes( "US-ASCII" );
    } catch ( UnsupportedEncodingException uee ) {
      throw new RuntimeException( "Caught encoding exception converting to US-ASCII encoding. "
          + "The java2 docs explicitly say that this encoding is ALWAYS "
          + "supported.  What the hell?" );
    }
    return rv;
  }

  /**
   * Wrapper to undo encodeString, returns null if
   * in is zero length or null.
   */
  public static String decodeString( byte[] in ) {
    if ( in == null || in.length == 0 ) return null;
    String rv = null;
    try {
      rv = new String( hexStringToBytes( new String( in, "US-ASCII" ) ), "US-ASCII" );
    } catch ( UnsupportedEncodingException uee ) {
      throw new RuntimeException( "Caught encoding exception converting to US-ASCII encoding. "
          + "The java2 docs explicitly say that this encoding is ALWAYS "
          + "supported.  What the hell?" );
    }
    return rv;
  }

  //////////////////////////////////////////////////////////
  // Begin compression stuff
  //////////////////////////////////////////////////////////

  /**
   * Compresses a string, returns null if given null as input,
   * otherwise returns non-null.  This is only good for LONG
   * strings, since it has to hex encode the output (thus
   * making the resulting string twice as long as the number
   * of compressed bytes) to avoid annoying java
   * too-many-string-encodings problems when trying to deal
   * with bytes and strings.
   */
  public static String ZLIBCompress( String input ) {
    if ( input == null ) {
      return ( null );
    }
    byte[] inBytes = input.getBytes();
    byte[] outBytes = new byte[1024];
    StringBuffer rv = new StringBuffer();

    // Use the java.util.zip.Deflater class to compress our data
    Deflater def = new Deflater( java.util.zip.Deflater.BEST_COMPRESSION );
    def.setInput( inBytes );

    int bytesCompressed = 0;
    while ( ! def.finished() ) {
      bytesCompressed = def.deflate( outBytes );
      rv.append( bytesToHexString( outBytes, 0, bytesCompressed ) );
      if ( def.needsInput() ) {
        def.finish();
      }
    }

    return ( rv.toString() );
  }

  /**
   * Uncompresses a string, returns null if given null as input,
   * otherwise returns non-null.  This assumes that they input
   * came from the compress() method, if not you are on your own.
   */
  public static String ZLIBUncompress( String input ) throws DataFormatException {
      if ( input == null ) {
        return ( null );
      }
      byte[] inBytes = hexStringToBytes( input );
      byte[] outBytes = new byte[1024];
      StringBuffer rv = new StringBuffer();

      // Use the java.util.zip.Inflater class to uncompress our data
      Inflater inf = new Inflater();
      inf.setInput( inBytes );

      int bytesUnCompressed = 0;
      while ( ! inf.finished() ) {
        bytesUnCompressed = inf.inflate( outBytes );
        rv.append( new String( outBytes, 0, bytesUnCompressed ) );
      }

      return ( rv.toString() );
    }

  // GZIPCompress 

  /**
   * Compresses an array of bytes using GZIP format, returns null if
   * given null as input, otherwise returns non-null.  This is only
   * good for LONG strings, since it has to hex encode the output (thus
   * making the resulting string twice as long as the number
   * of compressed bytes) to avoid annoying java
   * too-many-string-encodings problems when trying to deal
   * with bytes and strings.
   */
  public static byte[] GZIPCompress( byte[] inBytes ) {
    if ( inBytes == null ) {
      return ( null );
    }
    byte[] outBuff  = new byte[1024];
    StringBuffer rv = new StringBuffer();
    byte[] outBytes = null;

    // Use the java.util.zip.Deflater class to compress our data
    Deflater def = new Deflater( java.util.zip.Deflater.BEST_COMPRESSION, true );
    def.setInput( inBytes );

    int bytesCompressed = 0;
    int totBytes        = 0;
    while ( ! def.finished() ) {
      bytesCompressed = def.deflate( outBuff );
      outBytes = new byte[totBytes+bytesCompressed];
      System.arraycopy( outBuff, 0, outBytes, totBytes, totBytes+bytesCompressed );
      totBytes += bytesCompressed;
      if ( def.needsInput() ) {
        def.finish();
      }
    }

    return ( outBytes );
  }

  /**
   * Uncompresses an array of bytes, returns null if given null as input,
   * otherwise returns non-null.  This assumes that the input
   * came from the compress() method, if not you are on your own.
   */
  public static byte[] GZIPUncompress( byte[] inBytes ) throws DataFormatException {
      if ( inBytes == null ) {
        return ( null );
      }
      //byte[] inBytes = hexStringToBytes( input.toString() );
      byte[] outBuff  = new byte[1024];
      byte[] outBytes = null;
      //StringBuffer rv = new StringBuffer();

      // Use the java.util.zip.Inflater class to uncompress our data
      Inflater inf = new Inflater( true );
      inf.setInput( inBytes );

      int bytesUnCompressed = 0;
      int totBytes          = 0;
      while ( ! inf.finished() ) {
        bytesUnCompressed = inf.inflate( outBuff );
        outBytes = new byte[totBytes+bytesUnCompressed];
        System.arraycopy( outBuff, 0, outBytes, totBytes, totBytes+bytesUnCompressed );
        totBytes += bytesUnCompressed;
      }

      return ( outBytes );
    }

  //////////////////////////////////////////////////////////
  // End compression stuff
  //////////////////////////////////////////////////////////

  /**
   * Wrapper for readToByte.
   */
  public static byte[] readToByte( InputStream inStream, char stopByte ) {
    return readToByte( inStream, (byte) stopByte );
  }

  /**
   * @return byte array of all bytes in the InputStream stream
   *         leading up to but not including stopByte -- the
   *         stopByte is NOT pushed back into the InputStream
   *         stream but is discarded.  If inStream is null or
   *         otherwise unreadable, null is returned.  If the first
   *         byte of the stream is stopByte, a zero-length array is
   *         returned.
   */
  public static byte[] readToByte( InputStream inStream, byte stopByte ) {
    byte[] rv = new byte[0];
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream(Conf.getDefaultByteArraySize());
      int  i = (byte) inStream.read();
      if ( i == -1 ) throw new Exception( "End of stream" );
      byte b = (byte) i;
      while ( i != -1 && b != stopByte ) {
        baos.write( b );
        i = inStream.read();
        b = (byte) i;
      }
      baos.flush();
      rv = baos.toByteArray();
    } catch ( Exception e ) {
      // Per our contract, return null on error
      rv = null;
    }
    return rv;
  }

  public static final String BLANK_STRING = "";
  /** If s != null returns s otherwise returns BLANK_STRING */
  public static String getNonNull(String s) {
    return (s == null) ? BLANK_STRING : s.trim();
  }

    public static CacheKey generateMultipleCacheKeys(List<CacheKey> list) {
      if (list == null || list.isEmpty())
          return null;

      StringBuffer sb = new StringBuffer();
      for (CacheKey key : list) {
          sb.append(key.getKey()).append(GCUtil.CACHEKEY_SEPERATOR);
      }
      return CacheKey.getInstance(sb.toString());
    }

    public static List<CacheKey> getMultipleCacheKeys(CacheKey cacheKey) {
        String keyArray[] = new String[0];
        List<CacheKey> keyList = new ArrayList<CacheKey>();
        if (cacheKey != null && cacheKey.getKey() != null && cacheKey.getKey().contains(CACHEKEY_SEPERATOR)) {
            try {
                keyArray = GCUtil.PATTERN.split(cacheKey.getKey());
            } catch (Exception ignore) {
                return Collections.unmodifiableList(keyList);
            }
        }//if

        if (keyArray.length > 0) {
            for (String skey : keyArray) 
                keyList.add(CacheKey.getInstance(skey));
        }
        
        return Collections.unmodifiableList(keyList);
    }

  /**
   * Splits out a string into tokens, convenience wrapper for
   * java.util.StringTokenizer.  To use the default delimiter set,
   * set delimiter to <code>null</code> or an emtpy string.
   *
   * @see java.util.StringTokenizer
   */
  public static String[] getTokens( String val, String delimiter ) {
    ArrayList tokenList = new ArrayList();

    String[] tokens = null;

    if ( getNonNull( val ).length() > 0 ) {
      StringTokenizer st = null;
      if ( getNonNull( delimiter ).length() > 0 ) {
        st = new StringTokenizer( val, delimiter );
      } else {
        st = new StringTokenizer( val );
      }
      while ( st.hasMoreTokens() ) {
        tokenList.add( st.nextToken() );
      }

      tokens = new String[ tokenList.size() ];
      tokenList.toArray( tokens );
    } // length > 0

    return ( tokens );
  }

    public static Object getObjectFromByteStream(byte []data) {
        ObjectInputStream os = null;
        Object returnObject = null;
        if(data != null){
            try {
                os = new ObjectInputStream(new ByteArrayInputStream(data));
                returnObject = os.readObject();
            } catch (Exception exp) {
                _logger.log(_logger.LEVEL_SEVERE, exp.getMessage());
            } finally {
                if (os != null)
                    try { os.close(); } catch (Exception ignore) {;}
            }
        }
        return returnObject;
    }
  /////////////////////////////////////////////////////////////////////
  // Private methods
  /////////////////////////////////////////////////////////////////////

  // Generates the static CRC table used by calcChecksum().
  private static void genCRCTable( ) {
    int   i, j;
    long   crc_accum;

    for ( i = 0;  i < 256;  i++ ) {
      crc_accum = ( (long) i << 24 );
      for ( j = 0;  j < 8;  j++ ) {
        if ( ( crc_accum & 0x80000000L ) > 0 ) {
          crc_accum = (crc_accum << 1) ^ POLYNOMIAL;
        } else {
          crc_accum = crc_accum << 1;
        }
      } // for
      _crcTable[i] = crc_accum;
    }
    _tableIsGenerated = true;

    return;
  } // genCRCTable()


   /**
    * Method is used to return Host Name
    * @param _sock -- Socket
    * @return -- Host Name -- String
    */
   public static final String getHostNameFromSocket(Socket _sock) {
    String sHost = null;
    try {
      if (_sock == null)  
         return sHost;

      if (_hostNameLookupTable.containsKey(_sock.getInetAddress().getCanonicalHostName())) {
        return _hostNameLookupTable.get(_sock.getInetAddress().getCanonicalHostName());
      } else {   
        sHost = _sock.getInetAddress().getCanonicalHostName().toLowerCase().replaceAll(WALMARTDOTCOM,"");
          if (sHost != null) {
              sHost = sHost.replaceAll(WAL_MARTDOTCOM,"");
          }
        _hostNameLookupTable.put(_sock.getInetAddress().getCanonicalHostName(), sHost);
     }
    } catch (Exception exp) {
         WMQLLogger.getInstance().log( WMQLLogger.LEVEL_SEVERE, L2Worker.class.getName() + "/L2Worker(getHostNameFromSocket):" + exp.getMessage() );
      }//catch
     return sHost;
   }
    
    public static boolean isRequestComplete(ByteBuffer bb) {
        int p = bb.position() - 4;
        if (p < 0)
            return false;
        return (((bb.get(p + 0) == '\r') &&
                 (bb.get(p + 1) == '\n') &&
                 (bb.get(p + 2) == '\r') &&
                 (bb.get(p + 3) == '\n')));
    }
        
    /**
     * referring to the same class in com.wm.weblib.util.CacheUtil.java
     */
    
    public static void sortNamespaces(Namespace[] names, Comparator comparator) {
        Arrays.sort(names, comparator);
    }

    /**
     * Sort the given array of caches.
     * @param caches Array of Cache
     * @param comparator the comparator.
     */
    public static void sortCaches(Cache[] caches, Comparator comparator) {
        Arrays.sort(caches, comparator);
    }



    //-------------------------------------------------------------------------
    //-- Inner classes for sorting
    //--
    //-- By all rights these comparators belong in a util package under the gc
    //-- stuff.
    //-------------------------------------------------------------------------

    /**
     * This comparator can compare namespaces for ordering based on thier names.
     */
    public static class NamespaceComparator extends DirectionalComparator {
        /**
         * Constructor.
         * @see DirectionalComparator
         */
        public NamespaceComparator(boolean asc) {
            super(asc);
        }

        /**
         * Compares two Namespace objects by name. The result is consistent with the equals method.
         * @return int 1 if greater than, -1 if less than, 0 if equal
         */
        public int compare(Object obj1, Object obj2) {
            int dir = (isAscending() ? 1 : (-1));

            //First we deal with possible nulls.
            //We must deal with nulls since the 
            //Collection classes can contain null objects and
            //this will make the result consistent with equals.
            if ((obj1 == null) && (obj2 == null)) {
                return 0;
            }

            if (obj1 == null) {
                return -1 * dir;
            }

            if (obj2 == null) {
                return 1 * dir;
            }

            Namespace ns1 = (Namespace) obj1;
            Namespace ns2 = (Namespace) obj2;

            return (dir * ns1.getName().compareTo(ns2.getName()));
        }
    }

    /**
     * This comparator can compare caches for ordering based on various
     * attributes.
     */
    public static class CacheComparator extends DirectionalComparator {
        //Constants for the fields
        public static final int NAME = 1;
        public static final int ENTRY_COUNT = 2;
        public static final int SIZE = 3;
        public static final int PUT_COUNT = 4;
        public static final int GET_COUNT = 5;
        public static final int HITS = 6;
        public static final int HIT_RATE = 7;
        public static final int MAX_SIZE = 8;
        public static final int CREATED = 9;
        public static final int MODIFIED = 10; //same as cleared
        private int _field = NAME;

        /**
         * Construct with the information about what to sort and in what
         * direction.
         * @param asc the direction.
         * @param field the field to sort.
         */
        public CacheComparator(boolean asc, int field) {
            super(asc);
            _field = field;
        }

        /**
         * Construct with the information about what direction. Defaults to sort
         * by name.
         * @param asc the direction.
         */
        public CacheComparator(boolean asc) {
            this(asc, NAME);
        }

        /**
         * Compares two caches based on the information provided to the constructor.
         * The result is consistent with the equals method.
         * @return int 1 if greater than, -1 if less than, 0 if equal
         */
        public int compare(Object obj1, Object obj2) {
            int dir = (isAscending() ? 1 : (-1));

            //First we deal with possible nulls.
            //We must deal with nulls since the 
            //Collection classes can contain null objects and
            //this will make the result consistent with equals.
            if ((obj1 == null) && (obj2 == null)) {
                return 0;
            }

            if (obj1 == null) {
                return -1 * dir;
            }

            if (obj2 == null) {
                return 1 * dir;
            }

            Cache cache1 = (Cache) obj1;
            Cache cache2 = (Cache) obj2;

            Comparable f1;
            Comparable f2;

            switch (_field) {
            case NAME:
                f1 = cache1.getName();
                f2 = cache2.getName();

                break;

            case ENTRY_COUNT:
                f1 = new Long(cache1.getCacheStat().getEntryCount());
                f2 = new Long(cache2.getCacheStat().getEntryCount());

                break;

            case SIZE:
                f1 = new Long(cache1.getCacheStat().getSize());
                f2 = new Long(cache1.getCacheStat().getSize());

                break;

            case PUT_COUNT:
                f1 = new Long(cache1.getCacheStat().getPutCount());
                f2 = new Long(cache1.getCacheStat().getPutCount());

                break;

            case GET_COUNT:
                f1 = new Long(cache1.getCacheStat().getHitCount());
                f2 = new Long(cache1.getCacheStat().getHitCount());

                break;

            case HITS:
                f1 = new Long(cache1.getCacheStat().getHitSuccessCount());
                f2 = new Long(cache1.getCacheStat().getHitSuccessCount());

                break;

            case HIT_RATE:
                f1 = new Double(cache1.getCacheStat().getHitRate());
                f2 = new Double(cache1.getCacheStat().getHitRate());

                break;

            case MAX_SIZE:
                f1 = new Long(cache1.getCacheStat().getMaxSize());
                f2 = new Long(cache1.getCacheStat().getMaxSize());

                break;

            case CREATED:
                f1 = cache1.getCacheStat().getCreated();
                f2 = cache1.getCacheStat().getCreated();

                break;

            case MODIFIED:
                f1 = cache1.getCacheStat().getModified();
                f2 = cache1.getCacheStat().getModified();

                break;

            default:
                f1 = cache1.getName();
                f2 = cache2.getName();

                break;
            }

            return (dir * f1.compareTo(f2));
        }
    }

  /////////////////////////////////////////////////////////////////////
  // Private members
  /////////////////////////////////////////////////////////////////////
  private static final long POLYNOMIAL = 0x04C11DB7L;

  private static boolean debug = false;

  private static long[] _crcTable           = new long[256];
  private static boolean _tableIsGenerated  = false;

  /* TODO: Since we are doing only one query per transaction,
   * number of transactions is not included in version 1.
   */
  private static int gLengthOfReqHeader = 20;
  private static int gLengthOfResHeader = 20;
  private static final String WALMARTDOTCOM =".walmart.com"  ;
  private static final String WAL_MARTDOTCOM =".wal-mart.com"  ;
} // Util()
