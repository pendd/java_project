package com.pd.design_pattern._01_factory.abstract_factory;

/**
 * @author YCWB0382
 * @date 2022-09-03 22:50
 */
public abstract class CourseFactory {
  void init() {
    System.out.println("init data");
  }

  protected abstract INote createNote();

  protected abstract IVideo createVideo();
}
