package dao;

import dao.impl.CourseContentDaoImpl;
import pojo.Course;
import pojo.Course_Lesson;
import pojo.Course_Section;
import utils.DateUtils;
import org.junit.Test;

import java.util.List;

public class TestCourseContentDao {

    CourseContentDao contentDao = new CourseContentDaoImpl();

    //测试 查询对应课程下的章节与课时
    @Test
    public void testFindSectionAndLessonByCourseId(){

        List<Course_Section> list = contentDao.findSectionAndLessonByCourseId(59);

        for (Course_Section courseSection : list) {
            System.out.println(courseSection.getId()+" = "+courseSection.getSection_name());

            List<Course_Lesson> lessonList = courseSection.getLessonList();
            for (Course_Lesson lesson : lessonList) {
                System.out.println(lesson.getId()+" = "+lesson.getTheme()+" = " + lesson.getSection_id());
            }
        }
    }

    //测试根据课程id 回显课程名称
    @Test
    public void testFindCourseByCourseId(){

        Course course = contentDao.findCourseByCourseId(59);

        System.out.println(course.getId()+"  "+course.getCourse_name());
    }

    //测试保存章节功能
    @Test
    public void testSaveSection(){

        Course_Section section = new Course_Section();
        section.setCourse_id(59);
        section.setSection_name("Vue高级2");
        section.setDescription("Vue相关的高级技术");
        section.setOrder_num(8);

        String dateFormart = DateUtils.getDateFormart();
        section.setCreate_time(dateFormart);
        section.setUpdate_time(dateFormart);
        section.setStatus(2); //0:隐藏；1：待更新；2：已发布

        int i = contentDao.saveSection(section);
        System.out.println(i);
    }

    //测试修改章节功能
    @Test
    public void testUpdateSection(){

        Course_Section section = new Course_Section();
        section.setId(41);
        section.setSection_name("微服务架构");
        section.setDescription("微服务架构详细讲解");
        section.setOrder_num(3);
        section.setUpdate_time(DateUtils.getDateFormart());

        int i = contentDao.updateSection(section);
        System.out.println(i);
    }

    //测试修改章节状态
    @Test
    public void testUpdateSectionStatus(){

        int i = contentDao.updateSectionStatus(1, 0);
        System.out.println(i);
    }
}
