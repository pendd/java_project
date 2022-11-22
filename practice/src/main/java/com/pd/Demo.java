package com.pd;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author: pd
 * @date: 2021-06-09 上午11:15
 */
public class Demo {
  public static void main(String[] args) {

    try {
      InputStream is = new FileInputStream("/Users/pd/Documents/a.png");
      BufferedImage image = ImageIO.read(is);
      LuminanceSource source = new BufferedImageLuminanceSource(image);
      BinaryBitmap bb = new BinaryBitmap(new HybridBinarizer(source));
      HashMap<DecodeHintType, String> hints = new HashMap<>();
      hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

      Result result = new MultiFormatReader().decode(bb, hints);
      System.out.println("二维码格式类型：" + result.getBarcodeFormat());
      System.out.println("二维码文本内容：" + result.getText());
    } catch (IOException | NotFoundException e) {
      System.out.println(e);
    }
  }
}
