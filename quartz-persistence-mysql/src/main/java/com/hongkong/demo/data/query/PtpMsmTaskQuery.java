package com.hongkong.demo.data.query;

import lombok.Builder;
import lombok.Getter;
import java.util.Date;

/**
 * the PtpMsmTask Query model.
 *
 * @author qianhangkang
 * @since 2018/05/31 14:47
 */
@Getter
@Builder
public class PtpMsmTaskQuery {

  private Integer id;
  private String cron;
  private Integer type;
  private Integer status;
  private String jobName;
  private String parameter;
  private Date executeDate;

}