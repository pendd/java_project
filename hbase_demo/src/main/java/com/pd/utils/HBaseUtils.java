package com.pd.utils;

import com.pd.config.HbaseConfig;
import com.pd.config.SpringContextHolder;
import com.pd.entity.LogResponseDto;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * hbase工具类
 *
 * @author sixmonth @Date 2019年5月13日
 */
@DependsOn("springContextHolder") // 控制依赖顺序，保证springContextHolder类在之前已经加载
@Component
public class HBaseUtils {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  // 手动获取hbaseConfig配置类对象
  private static HbaseConfig hbaseConfig = SpringContextHolder.getBean("hbaseConfig");

  private static Configuration conf = HBaseConfiguration.create();
  private static ExecutorService pool = Executors.newScheduledThreadPool(20); // 设置hbase连接池
  private static Connection connection = null;
  private static HBaseUtils instance = null;
  private static Admin admin = null;

  private HBaseUtils() {
    if (connection == null) {
      try {
        // 将hbase配置类中定义的配置加载到连接池中每个连接里
        Map<String, String> confMap = hbaseConfig.getconfMaps();
        for (Map.Entry<String, String> confEntry : confMap.entrySet()) {
          conf.set(confEntry.getKey(), confEntry.getValue());
        }
        connection = ConnectionFactory.createConnection(conf, pool);
        admin = connection.getAdmin();
      } catch (IOException e) {
        logger.error("HbaseUtils实例初始化失败！错误信息为：" + e.getMessage(), e);
      }
    }
  }

  // 简单单例方法，如果autowired自动注入就不需要此方法
  public static synchronized HBaseUtils getInstance() {
    if (instance == null) {
      instance = new HBaseUtils();
    }
    return instance;
  }

  /**
   * 创建表
   *
   * @param tableName 表名
   * @param columnFamily 列族（数组）
   */
  public void createTable(String tableName, String[] columnFamily) throws IOException {
    TableName name = TableName.valueOf(tableName);
    // 如果存在则删除
    if (admin.tableExists(name)) {
      admin.disableTable(name);
      admin.deleteTable(name);
      logger.error("create htable error! this table {} already exists!", name);
    } else {
      HTableDescriptor desc = new HTableDescriptor(name);
      for (String cf : columnFamily) {
        desc.addFamily(new HColumnDescriptor(cf));
      }
      admin.createTable(desc);
    }
  }

  /**
   * 插入记录（单行单列族-多列多值）
   *
   * @param tableName 表名
   * @param row 行名
   * @param columnFamilies 列族名
   * @param records key 列名 value 值
   */
  public void insertRecords(
      String tableName, String row, String columnFamilies, Map<String, String> records)
      throws IOException {
    if (CollectionUtils.isEmpty(records)) {
      return;
    }

    TableName name = TableName.valueOf(tableName);
    Table table = connection.getTable(name);
    Put put = new Put(Bytes.toBytes(row));

    for (String column : records.keySet()) {
      put.addColumn(
          Bytes.toBytes(columnFamilies), Bytes.toBytes(column), Bytes.toBytes(records.get(column)));
      table.put(put);
    }
  }

  /**
   * 插入记录（单行单列族-多列多值）
   *
   * @param tableName 表名
   * @param row 行名
   * @param columnFamilies 列族名
   * @param columns 列名（数组）
   * @param values 值（数组）（且需要和列一一对应）
   */
  public void insertRecords(
      String tableName, String row, String columnFamilies, String[] columns, String[] values)
      throws IOException {
    TableName name = TableName.valueOf(tableName);
    Table table = connection.getTable(name);
    Put put = new Put(Bytes.toBytes(row));
    for (int i = 0; i < columns.length; i++) {
      put.addColumn(
          Bytes.toBytes(columnFamilies), Bytes.toBytes(columns[i]), Bytes.toBytes(values[i]));
      table.put(put);
    }
  }

  /**
   * 插入记录（单行单列族-单列单值）
   *
   * @param tableName 表名
   * @param row 行名
   * @param columnFamily 列族名
   * @param column 列名
   * @param value 值
   */
  public void insertOneRecord(
      String tableName, String row, String columnFamily, String column, String value)
      throws IOException {
    TableName name = TableName.valueOf(tableName);
    Table table = connection.getTable(name);
    Put put = new Put(Bytes.toBytes(row));
    put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
    table.put(put);
  }

  /**
   * 删除一行记录
   *
   * @param tablename 表名
   * @param rowkey 行名
   */
  public void deleteRow(String tablename, String rowkey) throws IOException {
    TableName name = TableName.valueOf(tablename);
    Table table = connection.getTable(name);
    Delete d = new Delete(rowkey.getBytes());
    table.delete(d);
  }

  /**
   * 删除单行单列族记录
   *
   * @param tablename 表名
   * @param rowkey 行名
   * @param columnFamily 列族名
   */
  public void deleteColumnFamily(String tablename, String rowkey, String columnFamily)
      throws IOException {
    TableName name = TableName.valueOf(tablename);
    Table table = connection.getTable(name);
    Delete d = new Delete(rowkey.getBytes()).addFamily(Bytes.toBytes(columnFamily));
    table.delete(d);
  }

  /**
   * 删除单行单列族单列记录
   *
   * @param tablename 表名
   * @param rowkey 行名
   * @param columnFamily 列族名
   * @param column 列名
   */
  public void deleteColumn(String tablename, String rowkey, String columnFamily, String column)
      throws IOException {
    TableName name = TableName.valueOf(tablename);
    Table table = connection.getTable(name);
    Delete d =
        new Delete(rowkey.getBytes()).addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
    table.delete(d);
  }

  /**
   * 查找一行记录
   *
   * @param tablename 表名
   * @param rowKey 行名
   */
  public static String selectRow(String tablename, String rowKey) throws IOException {
    String record = "";
    TableName name = TableName.valueOf(tablename);
    Table table = connection.getTable(name);
    Get g = new Get(rowKey.getBytes());
    Result rs = table.get(g);
    NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> map = rs.getMap();
    for (Cell cell : rs.rawCells()) {
      StringBuffer stringBuffer =
          new StringBuffer()
              .append(Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength()))
              .append("\t")
              .append(
                  Bytes.toString(
                      cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()))
              .append("\t")
              .append(
                  Bytes.toString(
                      cell.getQualifierArray(),
                      cell.getQualifierOffset(),
                      cell.getQualifierLength()))
              .append("\t")
              .append(
                  Bytes.toString(
                      cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()))
              .append("\n");
      String str = stringBuffer.toString();
      record += str;
    }
    return record;
  }

  /**
   * 查找单行单列族单列记录
   *
   * @param tablename 表名
   * @param rowKey 行名
   * @param columnFamily 列族名
   * @param column 列名
   * @return
   */
  public static String selectValue(
      String tablename, String rowKey, String columnFamily, String column) throws IOException {
    TableName name = TableName.valueOf(tablename);
    Table table = connection.getTable(name);
    Get g = new Get(rowKey.getBytes());
    g.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
    Result rs = table.get(g);
    return Bytes.toString(rs.value());
  }

  /**
   * 查询表中所有行（Scan方式）
   *
   * @param tablename
   * @return
   */
  public String scanAllRecord(String tablename) throws IOException {
    String record = "";
    TableName name = TableName.valueOf(tablename);
    Table table = connection.getTable(name);
    Scan scan = new Scan();
    scan.setLimit(20);
    ResultScanner scanner = table.getScanner(scan);
    try {
      for (Result result : scanner) {

        for (Cell cell : result.rawCells()) {
          System.out.println(cell.getTimestamp());
          StringBuffer stringBuffer =
              new StringBuffer()
                  .append(
                      Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength()))
                  .append("\t")
                  .append(
                      Bytes.toString(
                          cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()))
                  .append("\t")
                  .append(
                      Bytes.toString(
                          cell.getQualifierArray(),
                          cell.getQualifierOffset(),
                          cell.getQualifierLength()))
                  .append("\t")
                  .append(
                      Bytes.toString(
                          cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()))
                  .append("\n");
          String str = stringBuffer.toString();
          record += str;
        }
      }
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }

    return record;
  }

  /** 查询表中所有行（Scan方式） */
  public List<LogResponseDto> scanAll(String tableName) throws Exception {
    TableName name = TableName.valueOf(tableName);
    Table table = connection.getTable(name);
    Scan scan = new Scan();

    final Field[] fields = LogResponseDto.class.getDeclaredFields();

    List<LogResponseDto> list = new ArrayList<>();

    try (ResultScanner scanner = table.getScanner(scan)) {
      for (Result result : scanner) {
        final LogResponseDto logResponseDto = new LogResponseDto();
        for (Field field : fields) {
          field.setAccessible(true);
          final String fieldName = field.getName();
          if (result.containsColumn(Bytes.toBytes("info"), Bytes.toBytes(fieldName))) {
            field.set(
                logResponseDto,
                Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes(fieldName))));
          }
        }

        list.add(logResponseDto);

        System.out.println("result:::" + result);
        System.out.println("row:::" + Bytes.toString(result.getRow()));
        for (Cell listCell : result.listCells()) {
          System.out.println(
              "qualifier:::"
                  + Bytes.toString(
                      listCell.getQualifierArray(),
                      listCell.getQualifierOffset(),
                      listCell.getQualifierLength()));
          System.out.println(
              "value:::"
                  + Bytes.toString(
                      listCell.getValueArray(),
                      listCell.getValueOffset(),
                      listCell.getValueLength()));
          System.out.println();
        }
        System.out.println("=================");
      }
    }
    return list;
  }

  /**
   * 根据rowkey关键字查询报告记录
   *
   * @param tableName
   * @param rowKeyword
   * @return
   */
  public List scanReportDataByRowKeyword(String tableName, String rowKeyword) throws IOException {
    ArrayList<Object> list = new ArrayList<Object>();

    Table table = connection.getTable(TableName.valueOf(tableName));
    Scan scan = new Scan();

    // 添加行键过滤器，根据关键字匹配
    RowFilter rowFilter = new RowFilter(CompareOperator.EQUAL, new SubstringComparator(rowKeyword));
    scan.setFilter(rowFilter);

    ResultScanner scanner = table.getScanner(scan);
    try {
      for (Result result : scanner) {
        // TODO 此处根据业务来自定义实现
        list.add(null);
      }
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }

    return list;
  }

  /**
   * 根据rowkey关键字和时间戳范围查询报告记录
   *
   * @param tableName
   * @param rowKeyword
   * @return
   */
  public List scanReportDataByRowKeywordTimestamp(
      String tableName, String rowKeyword, Long minStamp, Long maxStamp) throws IOException {
    ArrayList<Object> list = new ArrayList<Object>();

    Table table = connection.getTable(TableName.valueOf(tableName));
    Scan scan = new Scan();
    // 添加scan的时间范围
    scan.setTimeRange(minStamp, maxStamp);

    RowFilter rowFilter = new RowFilter(CompareOperator.EQUAL, new SubstringComparator(rowKeyword));
    scan.setFilter(rowFilter);

    ResultScanner scanner = table.getScanner(scan);
    try {
      for (Result result : scanner) {
        // TODO 此处根据业务来自定义实现

        list.add(null);
      }
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }

    return list;
  }

  /**
   * 删除表操作
   *
   * @param tablename
   */
  public void deleteTable(String tablename) throws IOException {
    TableName name = TableName.valueOf(tablename);
    if (admin.tableExists(name)) {
      admin.disableTable(name);
      admin.deleteTable(name);
    }
  }

  /**
   * 根据rowKey关键字查询报告记录
   *
   * @param tableName
   * @return
   */
  public <T> List<T> scanByExample(
      String tableName,
      String family,
      String startRowKeyword,
      Integer pageSize,
      Integer type,
      String createTimeQualifier,
      Long startTime,
      Long endTime,
      Class<T> clazz)
      throws Exception {
    List<T> list = new ArrayList<>();

    Table table = connection.getTable(TableName.valueOf(tableName));
    Scan scan = new Scan();

    FilterList filterList = new FilterList();

    // 添加行键过滤器，根据关键字匹配
    if (type != null) {
      final RowFilter rowFilter =
          new RowFilter(CompareOperator.EQUAL, new BinaryPrefixComparator(Bytes.toBytes(type)));
      filterList.addFilter(rowFilter);
    }

    if (startTime != null && endTime != null) {
      final SingleColumnValueFilter startTimeColumnValueFilter =
          new SingleColumnValueFilter(
              Bytes.toBytes(family),
              Bytes.toBytes(createTimeQualifier),
              CompareOperator.GREATER_OR_EQUAL,
              Bytes.toBytes(startTime));
      final SingleColumnValueFilter endTimeColumnValueFilter =
          new SingleColumnValueFilter(
              Bytes.toBytes(family),
              Bytes.toBytes(createTimeQualifier),
              CompareOperator.LESS_OR_EQUAL,
              Bytes.toBytes(endTime));

      filterList.addFilter(startTimeColumnValueFilter);
      filterList.addFilter(endTimeColumnValueFilter);
    }

    scan.setFilter(filterList);
    if (!StringUtils.isEmpty(startRowKeyword)) {
      scan.withStartRow(Bytes.toBytes(startRowKeyword), false);
    }
    scan.setLimit(pageSize);

    final Field[] fields = clazz.getDeclaredFields();

    try (ResultScanner scanner = table.getScanner(scan)) {
      for (Result result : scanner) {
        T dto = clazz.newInstance();

        final Field rowKey = clazz.getDeclaredField("rowKey");
        rowKey.setAccessible(true);
        rowKey.set(dto, Bytes.toString(result.getRow()));

        for (Field field : fields) {
          field.setAccessible(true);
          final String fieldName = field.getName();
          if (result.containsColumn(Bytes.toBytes(family), Bytes.toBytes(fieldName))) {
            field.set(
                dto,
                Bytes.toString(result.getValue(Bytes.toBytes(family), Bytes.toBytes(fieldName))));
          }
        }
        list.add(dto);
      }
    }

    return list;
  }

  /**
   * 根据rowKey关键字查询报告记录
   *
   * @param tableName
   * @return
   */
  public <T> List<T> scanByExample1(
      String tableName,
      String family,
      String startRowKeyword,
      Integer pageSize,
      Integer type,
      String operatorId,
      Long startTime,
      Long endTime,
      Class<T> clazz)
      throws Exception {
    List<T> list = new ArrayList<>();

    Table table = connection.getTable(TableName.valueOf(tableName));
    Scan scan = new Scan();

    FilterList filterList = new FilterList();

    // 添加行键过滤器，根据关键字匹配
    if (type != null) {
      final RowFilter rowFilterByType =
          new RowFilter(
              CompareOperator.EQUAL,
              new BinaryPrefixComparator(Bytes.toBytes(String.format("log_%d", type))));
      filterList.addFilter(rowFilterByType);
    }

    if (!StringUtils.isEmpty(operatorId)) {
      final RowFilter rowFilterByOperatorId =
          new RowFilter(CompareOperator.EQUAL, new SubstringComparator(operatorId));
      filterList.addFilter(rowFilterByOperatorId);
    }

    if (startTime != null) {
      final RowFilter rowFilterByStartTime =
          new RowFilter(
              CompareOperator.GREATER_OR_EQUAL,
              new BinaryComparator(Bytes.toBytes(String.format("log_%d_%d", 0, startTime))));
      filterList.addFilter(rowFilterByStartTime);
    }

    if (startTime != null) {
      final RowFilter rowFilterByEndTime =
          new RowFilter(
              CompareOperator.LESS_OR_EQUAL,
              new BinaryComparator(Bytes.toBytes(String.format("log_%d_%d", 2, endTime))));
      filterList.addFilter(rowFilterByEndTime);
    }

    scan.setFilter(filterList);
    if (!StringUtils.isEmpty(startRowKeyword)) {
      scan.withStartRow(Bytes.toBytes(startRowKeyword), false);
    }
    scan.setLimit(pageSize);

    final Field[] fields = clazz.getDeclaredFields();

    try (ResultScanner scanner = table.getScanner(scan)) {
      for (Result result : scanner) {
        T dto = clazz.newInstance();

        final Field rowKey = clazz.getDeclaredField("rowKey");
        rowKey.setAccessible(true);
        rowKey.set(dto, Bytes.toString(result.getRow()));

        for (Field field : fields) {
          field.setAccessible(true);
          final String fieldName = field.getName();
          if (result.containsColumn(Bytes.toBytes(family), Bytes.toBytes(fieldName))) {
            field.set(
                dto,
                Bytes.toString(result.getValue(Bytes.toBytes(family), Bytes.toBytes(fieldName))));
          }
        }
        list.add(dto);
      }
    }

    return list;
  }
}
