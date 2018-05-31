package com.hongkong.demo.query;

import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.Date;

/**
 * the PtpMsmTask Query model.
 *
 * @author qianhangkang
 * @since 2018/05/30 16:16
 */
@Getter
@Builder
public class PtpMsmTaskQuery {

  private Integer id;
  private Integer status;
  private String jobName;
  private String jobGroup;
  private String parameter;
  private Date executeDate;

}