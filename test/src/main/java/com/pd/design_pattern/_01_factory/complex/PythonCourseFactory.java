package com.pd.design_pattern._01_factory.complex;

/**
 * @author YCWB0382
 * @date 2022-09-03 22:37
 */
public class PythonFactory implements ICourseFactory {
  @Override
  public ICourse getInstance() {
    return new PythonCourse();
  }
}
