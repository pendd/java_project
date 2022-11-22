package com.pd.producer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pd.model.TOrder;
import lombok.extern.slf4j.Slf4j;

/** @author dpeng */
@Slf4j
public class JacksonConvertUtil {
  public static TOrder convertJSONToObject(String message) {
    try {
      return new ObjectMapper().readValue(message, TOrder.class);
    } catch (JsonProcessingException e) {
      log.debug(String.format("%s json转换失败", message));
      return null;
    }
  }

  public static String convertObjectToJSON(Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      log.debug(String.format("%s json转换失败", obj));
      return null;
    }
  }

  private JacksonConvertUtil() {}
}
