package com.pd.producer.utils;

import com.pd.model.TOrder;
import com.pd.producer.service.MqService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** @author dpeng */
@SpringBootTest
class HvFileUtilsTest {

  @Autowired private MqService mqService;

  @Test
  void writeFile() {
    String fileName = "d:" + File.separator + "testFile" + File.separator + "20200628outFile.txt";
    TOrder order = new TOrder();
    order.setId(2020062801);
    order.setName("订单no.062801");
    order.setMessageId(UUID.randomUUID().toString());
    order.setScoreId(2801);
    HvFileUtils.writeFile(order, fileName);
  }

  @Test
  void readFile() {
    String fileName = "d:" + File.separator + "testFile" + File.separator + "20200628outFile.txt";
    TOrder tOrder = HvFileUtils.readFile(fileName, TOrder.class);
    mqService.testConsumePostT(tOrder);
    try {
      Files.delete(new File(fileName).toPath());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
