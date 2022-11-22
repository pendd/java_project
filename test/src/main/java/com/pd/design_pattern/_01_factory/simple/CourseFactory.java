package com.pd.design_pattern._01_factory.simple;

/**
 * @author YCWB0382
 * @date 2022-09-03 22:25
 */
public class CourseFactory {
  public static ICourse getInstance(String courseName) {
    ICourse course = null;
    switch (courseName) {
      case "java":
        course = new JavaCourse();
        break;
      case "python":
        course = new PythonCourse();
        break;
    }
    return course;
  }
}
