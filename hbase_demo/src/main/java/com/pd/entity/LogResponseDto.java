package com.pd.entity.dto;

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
  private Long time;

  /** 类型 1: 正确 2：失败 3：需要手动执行 */
  private Integer type;

  /** 请求参数 */
  private String requestData;

  /** 创建时间 */
  private Long createTime;
}
