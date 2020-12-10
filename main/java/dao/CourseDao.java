package dao;

import pojo.Course;

import java.util.List;

public interface CourseDao {

    //查询
    public List<Course> findCourseList();

    public List<Course> findByCourseNameAndStatus(String courseName,String status);

    //save
    public int saveCourseSalesInfo(Course course);

    public Course findCourseById(int id);

    public int updateCourseSalesInfo(Course course);

    public int updateCourseStatus(Course course);
}
