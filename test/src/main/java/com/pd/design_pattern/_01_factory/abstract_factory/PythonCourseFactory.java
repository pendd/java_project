package com.pd.design_pattern._01_factory.abstract_factory;

/**
 * @author YCWB0382
 * @date 2022-09-03 22:53
 */
public class JavaCourseFactory extends CourseFactory{
    @Override
    protected INote createNote() {
        super.init();
        return new JavaNote();
    }

    @Override
    protected IVideo createVideo() {
        super.init();
        return new JavaVideo();
    }
}
