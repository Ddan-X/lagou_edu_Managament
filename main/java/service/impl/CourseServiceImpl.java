package service.impl;

import base.StatusCode;
import dao.CourseDao;
import dao.impl.CourseDaoImpl;
import pojo.Course;
import service.CourseService;
import utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseServiceImpl implements CourseService {
    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public List<Course> findCourseList() {
         return courseDao.findCourseList();
    }

    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {
        return courseDao.findByCourseNameAndStatus(courseName,status);
    }

    @Override
    public String saveCourseSalesInfo(Course course) {
        //1.补全课程信息
        String dateFormart = DateUtils.getDateFormart();
        course.setCreate_time(dateFormart);
        course.setUpdate_time(dateFormart);
        course.setStatus(1);
        String result;

        int row = courseDao.saveCourseSalesInfo(course);
        if(row>0){
            //插入成功
            result = StatusCode.SUCCESS.toString();
            return result;
        }else {
            //插入失败
            result = StatusCode.FAIL.toString();
            return result;
        }

    }

    @Override
    public Course findCourseById(int id) {
        return courseDao.findCourseById(id);
    }

    @Override
    public String updateCourseSalesInfo(Course course) {
        int row = courseDao.updateCourseSalesInfo(course);
        if(row>0){
            String result = StatusCode.SUCCESS.toString();
            return result;
        }else {
            String result = StatusCode.FAIL.toString();
            return result;
        }
    }

    @Override
    public Map<String, Integer> updateCourseStatus(Course course) {
        int row = courseDao.updateCourseSalesInfo(course);
        Map<String,Integer> map = new HashMap<>();
        if(row>0){

            if(course.getStatus()==0){
                map.put("status",0);
            }else {
                map.put("status",1);
            }
        }
        return map;
    }


}
