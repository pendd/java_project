package com.pd.producer.config;

import com.alibaba.fastjson.JSON;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

/** @author dpeng */
@MappedTypes(Object.class)
public class MyBatisHandler extends BaseTypeHandler<Object> {
  @Override
  public void setNonNullParameter(
      PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
    preparedStatement.setString(i, JSON.toJSONString(o));
  }

  @Override
  public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
    String sqlJson = resultSet.getString(s);
    if (null != sqlJson) {
      return JSON.parse(sqlJson);
    }
    return null;
  }

  @Override
  public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
    String sqlJson = resultSet.getString(i);
    if (null != sqlJson) {
      return JSON.parse(sqlJson);
    }
    return null;
  }

  @Override
  public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    String sqlJson = callableStatement.getString(i);
    if (null != sqlJson) {
      return JSON.parse(sqlJson);
    }
    return null;
  }
}
