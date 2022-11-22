package com.pd.controller;

import com.pd.entity.LogAddRequestDto;
import com.pd.entity.LogRequestDto;
import com.pd.entity.LogResponseDto;
import com.pd.service.LogService;
import com.pd.utils.HBaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YCWB0382
 * @date 2022-01-09 17:03
 */
@RestController
public class DemoController {

  @Autowired private HBaseUtils hBaseUtils;
  @Autowired private LogService logService;

  @GetMapping(value = "/test")
  public List<LogResponseDto> test() throws Exception {
    String str = hBaseUtils.scanAllRecord("log"); // 扫描表
    System.out.println("获取到hbase的内容：" + str);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("hbaseContent", str);
    return null;
    //    return hBaseUtils.scanAll("log");
  }

  @PostMapping("/add")
  public String add(@RequestBody LogAddRequestDto dto) throws Exception {
    return logService.addLog(dto);
  }

  @PostMapping("/get")
  public List<LogResponseDto> get(@RequestBody LogRequestDto requestDto) throws Exception {
    return logService.get(requestDto);
  }
}
