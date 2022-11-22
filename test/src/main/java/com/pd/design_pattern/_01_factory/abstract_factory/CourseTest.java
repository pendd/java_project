package com.pd.design_pattern._01_factory.abstract_factory;

/**
 * @author YCWB0382
 * @date 2022-09-03 22:54
 */
public class CourseTest {
  public static void main(String[] args) {
    new JavaCourseFactory().createNote().note();
    new JavaCourseFactory().createVideo().record();
    new PythonCourseFactory().createNote().note();
    new PythonCourseFactory().createVideo().record();
  }
}
