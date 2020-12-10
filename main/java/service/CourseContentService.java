package service;

import pojo.Course;
import pojo.Course_Section;

import java.util.List;

/**
 * 课程内容管理 Service层接口
 * */
public interface CourseContentService {

    //根据课程id查询课程内容
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId);

    //根据id查询课程信息
    public Course findCourseByCourseId(int courseId);

    //保存章节信息
    public String saveSection(Course_Section section);

    //修改章节
    public String updateSection(Course_Section section);

    //修改章节状态
    public String updateSectionStatus(int id,int status);

}
