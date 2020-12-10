package dao.impl;


import dao.CourseContentDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Course;
import pojo.Course_Lesson;
import pojo.Course_Section;
import utils.DateUtils;
import utils.DruidUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * 课程内容管理DAO层实现类
 * */
public class CourseContentDaoImpl implements CourseContentDao {

    //根据课程ID查询课程相关信息
    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {

        try {
            //1.创建QueryRunner
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            //2.编写SQL
            String sql = "SELECT \n" +
                    "id,\n" +
                    "course_id,\n" +
                    "section_name,\n" +
                    "description,\n" +
                    "order_num,\n" +
                    "STATUS\n" +
                    "FROM course_section WHERE course_id = ?";

            //3.执行查询
            List<Course_Section> sectionList = qr.query(sql, new BeanListHandler<Course_Section>(Course_Section.class), courseId);

            //4.根据章节ID查询课时信息
            for (Course_Section section : sectionList) {

                //调用方法 获取章节对应的课时
                List<Course_Lesson> lessonList = findLessonBySectionId(section.getId());

                //将课时数据封装到 章节对象中
                section.setLessonList(lessonList);
            }

            return sectionList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    //根据章节ID查询课时信息
    @Override
    public List<Course_Lesson> findLessonBySectionId(int sectionId) {

        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "SELECT \n" +
                    "id,\n" +
                    "course_id,\n" +
                    "section_id,\n" +
                    "theme,\n" +
                    "duration,\n" +
                    "is_free,\n" +
                    "order_num,\n" +
                    "STATUS\n" +
                    "FROM course_lesson WHERE section_id = ?";

            List<Course_Lesson> lessonList = qr.query(sql, new BeanListHandler<Course_Lesson>(Course_Lesson.class), sectionId);

            return lessonList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    //根据课程id 回显课程信息
    @Override
    public Course findCourseByCourseId(int courseId) {

        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "SELECT id,course_name FROM course WHERE id = ?";

            Course course = qr.query(sql, new BeanHandler<Course>(Course.class), courseId);

            return course;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //保存章节信息
    @Override
    public int saveSection(Course_Section section) {

        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "INSERT INTO course_section(\n" +
                    "course_id,\n" +
                    "section_name,\n" +
                    "description,\n" +
                    "order_num,\n" +
                    "STATUS,\n" +
                    "create_time,\n" +
                    "update_time)VALUES(?,?,?,?,?,?,?);";

            Object[] param = {section.getCourse_id(),section.getSection_name(),section.getDescription(),
            section.getOrder_num(),section.getStatus(),section.getCreate_time(),section.getUpdate_time()};

            int row = qr.update(sql, param);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //修改章节
    @Override
    public int updateSection(Course_Section section) {

        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course_section SET\n" +
                    "section_name = ?,\n" +
                    "description = ?,\n" +
                    "order_num = ?,\n" +
                    "update_time = ? WHERE id = ?";

            Object[] param = {section.getSection_name(),section.getDescription(),section.getOrder_num(),
            section.getUpdate_time(),section.getId()};

            int row = qr.update(sql, param);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //修改章节状态
    @Override
    public int updateSectionStatus(int id, int status) {

        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course_section SET STATUS = ?,update_time = ? WHERE id = ?";

            Object[] param = {status, DateUtils.getDateFormart(),id};

            int row = qr.update(sql, param);

            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
