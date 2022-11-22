package com.pd.kafka.enums;

/**
 * @author: YCWB0382
 * @date: 2021-06-15 18:30
 */
public enum DataTypeEnum {
  TOPIC_TYPE(1, "topic_type");

  private Integer value;
  private String message;

  DataTypeEnum(Integer value, String message) {
    this.value = value;
    this.message = message;
  }

  public Integer getValue() {
    return value;
  }

  public String getMessage() {
    return message;
  }
}
