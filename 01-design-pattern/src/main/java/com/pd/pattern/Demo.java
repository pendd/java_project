package com.pd.pattern;

/**
 * @author YCWB0382
 * @date 2022-07-01 14:13
 */
public class Demo {

  /** 微信openId */
  private String openId;

  /** 创建人机构+角色 */
  private String creator;

  /** 创建时间 */
  private String createTime;

  /** 转运类型名称 */
  private String transferTypeName;

  /** 转运状态名称 */
  private String transferStatusName;

  /** 转运任务id */
  private String transferFlowId;

  /** 描述 */
  private String remark;

  /** 是否是入境任务 true：是 false：不是 */
  private Boolean isEntryTask = false;

  /** 小程序是否需要跳转 */
  private Boolean needPage = true;

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getTransferTypeName() {
    return transferTypeName;
  }

  public void setTransferTypeName(String transferTypeName) {
    this.transferTypeName = transferTypeName;
  }

  public String getTransferStatusName() {
    return transferStatusName;
  }

  public void setTransferStatusName(String transferStatusName) {
    this.transferStatusName = transferStatusName;
  }

  public String getTransferFlowId() {
    return transferFlowId;
  }

  public void setTransferFlowId(String transferFlowId) {
    this.transferFlowId = transferFlowId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Boolean getEntryTask() {
    return isEntryTask;
  }

  public void setEntryTask(Boolean entryTask) {
    isEntryTask = entryTask;
  }

  public Boolean getNeedPage() {
    return needPage;
  }

  public void setNeedPage(Boolean needPage) {
    this.needPage = needPage;
  }

  @Override
  public String toString() {
    return "Demo{"
        + "openId='"
        + openId
        + '\''
        + ", creator='"
        + creator
        + '\''
        + ", createTime='"
        + createTime
        + '\''
        + ", transferTypeName='"
        + transferTypeName
        + '\''
        + ", transferStatusName='"
        + transferStatusName
        + '\''
        + ", transferFlowId='"
        + transferFlowId
        + '\''
        + ", remark='"
        + remark
        + '\''
        + ", isEntryTask="
        + isEntryTask
        + ", needPage="
        + needPage
        + '}';
  }
}
