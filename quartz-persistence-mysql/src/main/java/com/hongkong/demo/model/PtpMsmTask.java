package com.hongkong.demo.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * the PtpMsmTask model.
 *
 * @author qianhangkang
 * @since 2018/05/30 16:16
 */
@Data
@Builder
public class PtpMsmTask implements Serializable {

  private static final long serialVersionUID = 8010333569766569179L;

  @Tolerate
  public PtpMsmTask(){}

  /**
   * 任务id.
   */
  private Integer id;

  /**
   * 执行状态，0--未执行，1--执行成功，2--执行失败.
   */
  private Integer status;

  /**
   * 任务名.
   */
  private String jobName;

  /**
   * 任务组.
   */
  private String jobGroup;

  /**
   * 短信参数.
   */
  private String parameter;

  /**
   * 执行日期.
   */
  private Date executeDate;


}