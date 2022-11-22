package com.pd.service;

import com.google.common.collect.Maps;
import com.pd.entity.LogAddRequestDto;
import com.pd.entity.LogRequestDto;
import com.pd.entity.LogResponseDto;
import com.pd.utils.HBaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author YCWB0382
 * @date 2022-01-12 15:51
 */
@Service
public class LogService {

  @Autowired private HBaseUtils hBaseUtils;

  private final String TABLE_NAME = "logRecord1";
  private final String COLUMN_FAMILIES = "info1";

  public String addLog(LogAddRequestDto dto) throws Exception {
    final long currentTimeMillis = System.currentTimeMillis();

    Map<String, String> records = Maps.newHashMap();
    records.put("operatorId", dto.getOperatorId());
    records.put("operatorName", dto.getOperatorName());
    records.put("title", dto.getTitle());
    records.put("uri", dto.getUri());
    records.put("ip", dto.getIp());
    records.put("msg", dto.getMsg());
    records.put("time", String.valueOf(dto.getTime()));
    records.put("requestData", dto.getRequestData());
    records.put("type", String.valueOf(dto.getType()));
    records.put("createTime", String.valueOf(currentTimeMillis));

    final String rowKey =
        String.format("log_%d_%d_%s", dto.getType(), currentTimeMillis, dto.getOperatorId());
    hBaseUtils.insertRecords(TABLE_NAME, rowKey, COLUMN_FAMILIES, records);
    return rowKey;
  }

  public List<LogResponseDto> get(LogRequestDto requestDto) throws Exception {
    return hBaseUtils.scanByExample1(
        TABLE_NAME,
        COLUMN_FAMILIES,
        requestDto.getStartRowKeyWords(),
        requestDto.getPageSize(),
        requestDto.getType(),
        requestDto.getOperatorId(),
        requestDto.getStartTime(),
        requestDto.getEndTime(),
        LogResponseDto.class);
  }
}
