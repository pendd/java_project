package com.pd.ioc.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

/**
 * 类工具
 *
 * @author: pd
 * @date: 2021-06-04 下午10:49
 */
public class ClassUtils {
  /**
   * 获取某包下所有类
   *
   * @param packageName 包名
   * @param isRecursion 是否遍历子包
   * @return 类的完整名称
   */
  public static Set<String> getClassName(String packageName, boolean isRecursion) {
    Set<String> classNames = new HashSet<>();
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    String packagePath = packageName.replace(".", "/");
    URL url = loader.getResource(packagePath);
    String filePath = null;
    try {
      filePath = URLDecoder.decode(url.getPath(), "utf-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    if (filePath != null) {
      classNames = getClassNameFromDir(filePath, packageName, isRecursion);
    }
    return classNames;
  }

  /**
   * 从项目文件获取某包下有类
   *
   * @param filePath 文件路径
   * @param isRecursion 是否遍历子包
   * @return 类的完整名称
   */
  private static Set<String> getClassNameFromDir(
      String filePath, String packageName, boolean isRecursion) {
    Set<String> className = new HashSet<>();
    File file = new File(filePath);
    File[] files = file.listFiles();
    for (File childFile : files) {

      if (childFile.isDirectory()) {
        if (isRecursion) {
          className.addAll(
              getClassNameFromDir(
                  childFile.getPath(), packageName + "." + childFile.getName(), isRecursion));
        }
      } else {
        String fileName = childFile.getName();
        if (fileName.endsWith(".class") && !fileName.contains("$")) {
          className.add(packageName + "." + fileName.replace(".class", ""));
        }
      }
    }
    return className;
  }
}
