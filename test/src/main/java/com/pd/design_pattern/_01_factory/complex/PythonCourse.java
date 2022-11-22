package com.pd.design_pattern._01_factory.simple;

/**
 * @author YCWB0382
 * @date 2022-09-03 22:25
 */
public class PythonCourse implements ICourse {
  @Override
  public void record() {
    System.out.println("录制Python课程");
  }
}
