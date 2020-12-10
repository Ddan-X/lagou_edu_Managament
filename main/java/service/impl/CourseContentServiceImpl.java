package service.impl;

import base.StatusCode;
import dao.CourseContentDao;
import dao.impl.CourseContentDaoImpl;
import pojo.Course;
import pojo.Course_Section;
import service.CourseContentService;
import utils.DateUtils;

import java.util.List;

/**
 * 课程内容管理 Service层实现类
 * */
public class CourseContentServiceImpl implements CourseContentService {

    CourseContentDao contentDao = new CourseContentDaoImpl();

    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {
        List<Course_Section> sections = contentDao.findSectionAndLessonByCourseId(courseId);
        return sections;
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        Course course = contentDao.findCourseByCourseId(courseId);
        return course;
    }

    @Override
    public String saveSection(Course_Section section) {

        //1.补全章节信息
        section.setStatus(2);//状态 0 隐藏,1 待更新,2 已发布
        String date = DateUtils.getDateFormart();
        section.setCreate_time(date);
        section.setUpdate_time(date);

        //2.调用dao
        int row = contentDao.saveSection(section);

        //3.根据是否插入成功,封装对应信息
        if(row > 0){
            //保存成功
            String result = StatusCode.SUCCESS.toString();
            return result;
        }else{
            String result = StatusCode.FAIL.toString();
            return result;
        }

    }

    @Override
    public String updateSection(Course_Section section) {

        //1.补全信息
        String date = DateUtils.getDateFormart();
        section.setUpdate_time(date);

        //2.调用dao
        int row = contentDao.updateSection(section);

        //3.判断是否插入成功
        if(row > 0){
            String result = StatusCode.SUCCESS.toString();
            return result;
        }else{
            String result  = StatusCode.FAIL.toString();
            return result;
        }
    }

    @Override
    public String updateSectionStatus(int id, int status) {

        int row = contentDao.updateSectionStatus(id, status);

        //判断是否修改成功
        if(row > 0){
            //修改状态成功
            String result = StatusCode.SUCCESS.toString();
            return result;
        }else{
            //修改失败
            String result = StatusCode.FAIL.toString();
            return result;
        }
    }


}
