package com.pd.producer.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/** @author dpeng */
@Component
@MapperScan(
    basePackages = MySqlDataSourceConfig.PACKAGE,
    sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MySqlDataSourceConfig {

  static final String PACKAGE = "com.pd.producer.mapper.mysql";
  static final String MAPPER_LOCATION = "classpath:mapper/mysql/*.xml";

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.mysql")
  public DataSource mysqlDataSource() {
    return new DruidDataSource();
  }

  @Bean("mysqlT")
  @Primary
  public DataSourceTransactionManager mysqlTransactionManager(
      @Qualifier("mysqlDataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  @Primary
  public SqlSessionFactory mysqlSqlSessionFactory(
      @Qualifier("mysqlDataSource") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
    Configuration configuration = new Configuration();
    // 开启驼峰
    configuration.setMapUnderscoreToCamelCase(true);
    sqlSessionFactory.setConfiguration(configuration);
    sqlSessionFactory.setDataSource(dataSource);
    sqlSessionFactory.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
    return sqlSessionFactory.getObject();
  }
}
