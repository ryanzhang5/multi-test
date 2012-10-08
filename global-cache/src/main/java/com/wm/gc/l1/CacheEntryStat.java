
package com.wm.gc.l1;

public interface CacheEntryStat
{
  /**
   * returns number of times this entry has been accessed
   */
  public long getAccessCount();

  /**
   * returns number of bytes it takes to store this enty (value only).
   * When the exact number is unknown (e.g. memory object store) returns 1.
   */
  public long getSize();

  public long getCreateTime();

  /**
   * returns timestamp of last access (milliseconds since epoch).
   */
  public long getAccessTime();

  /**
   * returns number of milliseconds since the epoch when this entry
   * expires
   */
  public long getExpireTime();

  public long getTTLMillis();

  public boolean isExpired();

  public boolean isExpired( long now );

}

