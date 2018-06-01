package com.hongkong.demo.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import java.io.Serializable;
import java.util.Date;

/**
 * the PtpMsmTask model.
 *
 * @author qianhangkang
 * @since 2018/05/31 14:47
 */
@Data
@Builder
@AllArgsConstructor
public class PtpMsmTask implements Serializable {

  private static final long serialVersionUID = 8010333569766569179L;

  @Tolerate
  public PtpMsmTask(){}

  /**
   * 任务id.
   */
  private Integer id;

  /**
   * cron表达式.
   */
  private String cron;

  /**
   * 任务执行类型，0--立即执行，1--延时执行一次，2--利用cron表达式执行.
   */
  private Integer type;

  /**
   * 执行状态，0--未执行，1--执行成功，2--执行失败.
   */
  private Integer status;

  /**
   * 任务名.
   */
  private String jobName;

  /**
   * 短信参数.
   */
  private String parameter;

  /**
   * 执行日期.
   */
  private Date executeDate;


}