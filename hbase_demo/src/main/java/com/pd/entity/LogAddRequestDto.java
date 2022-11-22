package com.pd.entity;

/**
 * @author YCWB0382
 * @date 2022-01-14 19:07
 */
public class LogAddRequestDto {
  /** 接口名称 */
  private String title;

  /** 接口 uri */
  private String uri;

  /** 客户端 ip */
  private String ip;

  /** 内容 */
  private String msg;

  /** 耗时 单位：毫秒 */
  private Long time;

  /** 类型 1: 正确 2：失败 3：需要手动执行 */
  private Integer type;

  /** 请求参数 */
  private String requestData;

  private String operatorId;

  private String operatorName;

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

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getRequestData() {
    return requestData;
  }

  public void setRequestData(String requestData) {
    this.requestData = requestData;
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
