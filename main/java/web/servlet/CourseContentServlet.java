package web.servlet;

import base.BaseServlet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import pojo.Course;
import pojo.Course_Section;
import service.CourseContentService;
import service.impl.CourseContentServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/courseContent")
public class CourseContentServlet extends BaseServlet {

    //展示对应课程的章节与课时信息
    public void findSectionAndLessonByCourseId(HttpServletRequest request , HttpServletResponse response){

        try {
            //1.获取参数
            String course_id = request.getParameter("course_id");

            //2.业务处理
            CourseContentService contentService = new CourseContentServiceImpl();
            List<Course_Section> list = contentService.findSectionAndLessonByCourseId(Integer.parseInt(course_id));

            //3.返回结果
            String result = JSON.toJSONString(list);
            response.getWriter().print(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //根据课程id 回显课程信息
    public void findCourseById(HttpServletRequest request ,HttpServletResponse response){

        try {
            //1.获取参数
            String course_id = request.getParameter("course_id");

            //2.业务处理
            CourseContentService contentService = new CourseContentServiceImpl();
            Course course = contentService.findCourseByCourseId(Integer.parseInt(course_id));

            //3.返回JSON数据
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,"id","course_name");

            String result = JSON.toJSONString(course, filter);
            response.getWriter().print(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存&修改 章节信息
     * */
    public void saveOrUpdateSection(HttpServletRequest request ,HttpServletResponse response){

        try {
            //1.获取参数  从域对象中获取
            Map<String,Object> map = (Map)request.getAttribute("map");

            //2.创建Course_Section
            Course_Section section = new Course_Section();

            //3.使用BeanUtils工具类,将map中的数据封装到 section
            BeanUtils.populate(section,map);

            //4.业务处理
            CourseContentService contentService = new CourseContentServiceImpl();

            //判断是否携带id
            if(section.getId() == 0){
                //新增操作
                String result = contentService.saveSection(section);
                //5.响应结果
                response.getWriter().print(result);

            }else{
                //修改操作
                String result = contentService.updateSection(section);
                response.getWriter().print(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改章节状态
    public void updateSectionStatus(HttpServletRequest request ,HttpServletResponse response){

        try {
            //1.接收参数
            int id = Integer.parseInt(request.getParameter("id"));//章节id
            int status = Integer.parseInt(request.getParameter("status"));//章节状态

            //2.业务处理
            CourseContentService contentService = new CourseContentServiceImpl();
            String result = contentService.updateSectionStatus(id, status);

            //3.返回结果
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
