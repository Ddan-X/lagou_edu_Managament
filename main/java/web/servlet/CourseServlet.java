package web.servlet;

import base.BaseServlet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import pojo.Course;
import service.CourseService;
import service.impl.CourseServiceImpl;
import utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/course")
public class CourseServlet extends BaseServlet {

    public void findCourseList(HttpServletRequest request, HttpServletResponse response){
        try {
            //1.接收参数 BaseServlet 代替
            //2.业务处理
            CourseService courseService = new CourseServiceImpl();
            List<Course> courseList = courseService.findCourseList();

            //SimplePropertyFilter 指定转换的JSON字段
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,"id",
                    "course_name","price","sort_num","status");

            //3.转换成JSON
            String result = JSON.toJSONString(courseList,filter);

            response.getWriter().print(result);

        } catch (IOException ioException) {

        }
    }

    //根据条件查询
    public void findByCourseNameAndStatus(HttpServletRequest request,HttpServletResponse response){
        try {
            //1.接收参数
            String course_name = request.getParameter("course_name");
            String status = request.getParameter("status");

            //2.业务处理
            CourseService courseService = new CourseServiceImpl();
            List<Course> courseList = courseService.findByCourseNameAndStatus(course_name, status);

            //SimplePropertyFilter 指定转换的JSON字段
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,"id",
                    "course_name","price","sort_num","status");

            String result = JSON.toJSONString(courseList, filter);

            response.getWriter().print(result);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    //根据课程id 查询课程信息
    public void findCourseById(HttpServletRequest request,HttpServletResponse response){

        try {
            //1.接收参数
            String id = request.getParameter("id");

            //2.业务处理
            CourseService cs = new CourseServiceImpl();
            Course course = cs.findCourseById(Integer.parseInt(id));

            //3.返回结果
            //SimplePropertyFilter 指定转换的JSON字段
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,"id",
                    "course_name","brief","teacher_name","teacher_info","price","price_tag",
                    "discounts","preview_first_field","preview_second_field","course_img_url","share_title",
                    "share_description","course_description","share_image_title");

            String result = JSON.toJSONString(course,filter);
            response.getWriter().print(result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //修改课程状态
    public void updateCourseStatus(HttpServletRequest request,HttpServletResponse response){

        try {
            //1.获取参数
            String id = request.getParameter("id");

            //2.业务处理
            CourseService cs = new CourseServiceImpl();

            //3.根据课程id 查询课程信息
            Course course = cs.findCourseById(Integer.parseInt(id));

            //4.判断课程信息状态,进行取反设置
            int status = course.getStatus();
            if(status == 0){
                //如果是0 设置为1
                course.setStatus(1);
            }else{
                course.setStatus(0);
            }

            //5.设置更新时间
            course.setUpdate_time(DateUtils.getDateFormart());

            //6.修改状态
            Map<String, Integer> map = cs.updateCourseStatus(course);

            //7.响应结果
            String result = JSON.toJSONString(map);

            response.getWriter().print(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
