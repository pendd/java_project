package com.pd.design_pattern._01_factory.complex;

/**
 * @author YCWB0382
 * @date 2022-09-03 22:38
 */
public class CourseTest {
  public static void main(String[] args) {
    new JavaCourseFactory().getInstance().record();
    new PythonCourseFactory().getInstance().record();
  }
}
