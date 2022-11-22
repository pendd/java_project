package com.pd.design_pattern._01_factory.simple;

/**
 * @author YCWB0382
 * @date 2022-09-03 22:29
 */
public class CourseTest {
  public static void main(String[] args) {
    final ICourse java = CourseFactory.getInstance("java");
    java.record();
  }
}
