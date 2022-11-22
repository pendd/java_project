import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YCWB0382
 * @date 2022-01-09 17:03
 */
@RestController
public class DemoController {

  @Autowired private HBaseUtils hBaseUtils;

  @RequestMapping(value = "/test")
  public Map<String, Object> test() throws IOException {
    String str = hBaseUtils.scanAllRecord("sixmonth"); // 扫描表
    System.out.println("获取到hbase的内容：" + str);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("hbaseContent", str);
    return map;
  }
}
