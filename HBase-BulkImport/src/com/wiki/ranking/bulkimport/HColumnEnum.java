package com.wiki.ranking.bulkimport;

/**
 * HBase table columns for the 'srv' column family
 */
public enum HColumnEnum {
  SRV_COL_RANK ("rank".getBytes()),
  SRV_COL_PAGE ("page".getBytes());
  private final byte[] columnName;
  
  HColumnEnum (byte[] column) {
    this.columnName = column;
  }

  public byte[] getColumnName() {
    return this.columnName;
  }
}
