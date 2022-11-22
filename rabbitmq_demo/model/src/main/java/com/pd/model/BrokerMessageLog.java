package com.pd.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BrokerMessageLog {
  private String messageId;

  private String message;

  private Integer tryCount;

  private String status;

  private LocalDateTime nextRetry;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;
}
