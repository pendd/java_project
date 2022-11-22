package com.pd.kafka.entity;

/**
 * @author: YCWB0382
 * @date: 2021-06-15 17:57
 */
public class UploadData {
  private String uuid;
  private String value;
  private Student student;
  private Long nextRetryTime;
  private Integer retryTimes;
  private Integer dataType;

  public UploadData(String uuid) {
    this.uuid = uuid;
  }

  public UploadData() {}

  public Student getStudent() {
    return student;
  }

  public UploadData setStudent(Student student) {
    this.student = student;
    return this;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Long getNextRetryTime() {
    return nextRetryTime;
  }

  public void setNextRetryTime(Long nextRetryTime) {
    this.nextRetryTime = nextRetryTime;
  }

  public Integer getRetryTimes() {
    return retryTimes;
  }

  public void setRetryTimes(Integer retryTimes) {
    this.retryTimes = retryTimes;
  }

  public Integer getDataType() {
    return dataType;
  }

  public void setDataType(Integer dataType) {
    this.dataType = dataType;
  }

  public UploadData(
      String uuid, String value, Long nextRetryTime, Integer retryTimes, Integer dataType) {
    this.uuid = uuid;
    this.value = value;
    this.nextRetryTime = nextRetryTime;
    this.retryTimes = retryTimes;
    this.dataType = dataType;
  }

  public UploadData(String uuid, String value) {
    this.uuid = uuid;
    this.value = value;
  }

  @Override
  public String toString() {
    return "UploadData{"
        + "uuid='"
        + uuid
        + '\''
        + ", value='"
        + value
        + '\''
        + ", nextRetryTime="
        + nextRetryTime
        + ", retryTimes="
        + retryTimes
        + ", dataType="
        + dataType
        + '}';
  }
}
