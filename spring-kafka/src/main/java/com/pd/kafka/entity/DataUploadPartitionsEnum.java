package com.pd.kafka.entity;

public enum DataUploadPartitionsEnum {

  /** 楼栋 */
  BUILDING_INFO("building_info", "1"),
  /** 社区教育，房屋，人屋，注册人员，老人 */
  MORE_TYPE("more_type", "2"),
  /** 设备 */
  DEVICE_INFO("device_info", "3"),
  /** 人员出入 */
  PERSON_OUT_IN_RECORD("person_out_in_record", "4"),
  /** 智能分析 */
  SMART_ANALYSIS("smart_analysis", "5"),
  /** 未注册人员 */
  UNREGISTER_PERSON("unregister_person", "6"),
  /** 小区 */
  VILLAGE_RECORD("village_record", "7");

  DataUploadPartitionsEnum(String message, String code) {
    this.code = code;
    this.message = message;
  }

  private final String message;
  private final String code;

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
