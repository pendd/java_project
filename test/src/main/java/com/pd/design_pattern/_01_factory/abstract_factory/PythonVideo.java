package com.pd.design_pattern._01_factory.abstract_factory;

/**
 * @author YCWB0382
 * @date 2022-09-03 22:52
 */
public class PythonVideo implements IVideo {
  @Override
  public void record() {
    System.out.println("record python video");
  }
}
