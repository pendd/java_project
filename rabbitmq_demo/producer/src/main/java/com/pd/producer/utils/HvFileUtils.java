package com.pd.producer.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

/** @author dpeng */
@Slf4j
public class HvFileUtils {

  public static <T> void writeFile(T t, String fileName) {
    File file = new File(fileName);
    File parentFile = file.getParentFile();
    if (!parentFile.exists()) {
      parentFile.mkdirs();
    }
    if (!file.exists()) {
      try {
        file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        FileUtils.writeStringToFile(file, objectMapper.writeValueAsString(t));
      } catch (IOException e) {
        log.debug(String.format("%s路径文件写操作异常", fileName));
      }
    }
  }

  public static <T> T readFile(String fileName, Class<T> clazz) {
    try {
      File file = new File(fileName);
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(file, clazz);
    } catch (IOException e) {
      log.debug(String.format("%s路径文件读操作异常", fileName));
    }
    return null;
  }

  private HvFileUtils() {}
}
