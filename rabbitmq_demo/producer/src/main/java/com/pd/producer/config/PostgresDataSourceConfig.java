package com.pd.producer.config;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

/** @author dpeng */
@Component
@MapperScan(
    basePackages = PostgresDataSourceConfig.PACKAGE,
    sqlSessionFactoryRef = "postgresSqlSessionFactory")
public class PostgresDataSourceConfig {

  static final String PACKAGE = "com.pd.producer.mapper.postgresql";
  static final String MAPPER_LOCATION = "classpath:mapper/postgresql/*.xml";

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.postgres")
  public DataSource postgresDataSource() {
    return new DruidDataSource();
  }

  @Bean("postT")
  public DataSourceTransactionManager postgresTransactionManager(
      @Qualifier("postgresDataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public SqlSessionFactory postgresSqlSessionFactory(
      @Qualifier("postgresDataSource") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
    sqlSessionFactory.setDataSource(dataSource);
    sqlSessionFactory.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
    Configuration configuration = new Configuration();
    configuration.setMapUnderscoreToCamelCase(true);
    sqlSessionFactory.setConfiguration(configuration);
    return sqlSessionFactory.getObject();
  }
}
