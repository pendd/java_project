package com.pd.entity;

/**
 * @author YCWB0382
 * @date 2022-01-13 11:22
 */
public class LogRequestDto {

  private Integer pageSize = 1;

  /** 开始的rowKey */
  private String startRowKeyWords;

  /** 类型 1: 正确 2：失败 3：需要手动执行 */
  private Integer type;

  /** 开始时间 */
  private Long startTime;

  /** 结束时间 */
  private Long endTime;

  /** 操作人id */
  private String operatorId;

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Long getStartTime() {
    return startTime;
  }

  public void setStartTime(Long startTime) {
    this.startTime = startTime;
  }

  public Long getEndTime() {
    return endTime;
  }

  public void setEndTime(Long endTime) {
    this.endTime = endTime;
  }

  public String getStartRowKeyWords() {
    return startRowKeyWords;
  }

  public void setStartRowKeyWords(String startRowKeyWords) {
    this.startRowKeyWords = startRowKeyWords;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public String getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(String operatorId) {
    this.operatorId = operatorId;
  }
}
