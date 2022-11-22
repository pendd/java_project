package com.pd.entity;

/**
 * @author YCWB0382
 * @date 2022-01-13 09:50
 */
public class LogResponseDto {

  /** 行健 */
  private String rowKey;

  /** 接口名称 */
  private String title;

  /** 接口 uri */
  private String uri;

  /** 客户端 ip */
  private String ip;

  /** 内容 */
  private String msg;

  /** 耗时 单位：毫秒 */
  private String time;

  /** 类型 1: 正确 2：失败 3：需要手动执行 */
  private String type;

  /** 请求参数 */
  private String requestData;

  /** 创建时间 */
  private String createTime;

  /** 操作人id */
  private String operatorId;

  /** 操作人name */
  private String operatorName;

  public String getRowKey() {
    return rowKey;
  }

  public void setRowKey(String rowKey) {
    this.rowKey = rowKey;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getRequestData() {
    return requestData;
  }

  public void setRequestData(String requestData) {
    this.requestData = requestData;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(String operatorId) {
    this.operatorId = operatorId;
  }

  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }
}
