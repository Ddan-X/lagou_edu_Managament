package dao;

import dao.impl.CourseDaoImpl;
import org.junit.Test;
import pojo.Course;
import utils.DateUtils;

import java.util.List;

public class TestCourseDao {
    CourseDao courseDao = new CourseDaoImpl();

    @Test
    public void testFindCourseList(){
        List<Course> courseList = courseDao.findCourseList();
        System.out.println(courseList);
    }

    @Test
    public void testfindByCourseNameAndStatus(){
        List<Course> courseList = courseDao.findByCourseNameAndStatus("微服务架构", "1");

        for(Course c : courseList){
            System.out.println(c.getId()+" "+c.getCourse_name()+" "+c.getStatus());
        }
    }

    @Test
    public void testSaveCourseSalesInfo(){
        //1.创建course对象
        Course course = new Course();
        course.setCourse_name("爱情36计");
        course.setBrief("学会去找对象");
        course.setTeacher_name("药水哥");
        course.setTeacher_info("人人都是药水哥");
        course.setPreview_first_field("共10讲");
        course.setPreview_second_field("每周日更新");
        course.setDiscounts(88.88);
        course.setPrice(188.0);
        course.setPrice_tag("最新优惠价");
        course.setShare_image_title("哈哈哈");
        course.setShare_title("嘻嘻嘻");
        course.setShare_description("天天向上");
        course.setCourse_description("爱情36计,就像一场游戏");
        course.setCourse_img_url("https://www.xx.com/xxx.jpg");
        course.setStatus(1); //1 上架 ,0 下架
        String formart = DateUtils.getDateFormart();
        course.setCreate_time(formart);
        course.setUpdate_time(formart);

        int i = courseDao.saveCourseSalesInfo(course);
        System.out.println(i);
    }

    //测试修改课程信息
    @Test
    public void testUpdateCourse(){

        //1.根据id查询课程信息
        Course course = courseDao.findCourseById(1);
        System.out.println(course);

        //2.修改课程营销信息
        course.setCourse_name("100个Java面试必考点");
        course.setTeacher_name("汪老师");
        course.setDiscounts(88.8);

        int i = courseDao.updateCourseSalesInfo(course);
        System.out.println(i);

    }
}
