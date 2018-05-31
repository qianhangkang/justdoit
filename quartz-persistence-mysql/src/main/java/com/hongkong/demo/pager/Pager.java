package com.hongkong.demo.pager;

/**
 * 分页信息.
 *
 * @author qianhangkang
 * @since 2018/05/30 16:16
 */
public class Pager {

  private int size;
  private int index;

  public Pager(int size, int index) {
    this.size = size;
    this.index = index;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public int getStart() {
    int startIndex = (this.index - 1) * size;
    return startIndex > 0 ? startIndex : 0;
  }

}