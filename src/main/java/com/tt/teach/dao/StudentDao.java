package com.tt.teach.dao;

import com.tt.teach.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentDao {

    @Select("select * from student where studentNo=#{studentNo} and loginPwd=#{loginPwd}")
    Student doLogin(Student student);
    @Select("SELECT student.*,grade.gradeName as gradeName FROM student,grade WHERE student.gradeId=grade.gradeID")
    List<Student> findStuAll();
    @Update("update student set studentName=#{studentName},loginPwd=#{loginPwd},phone=#{phone} where studentNo=#{studentNo}")
    int updateStu(Student student);
    @Delete("delete from student where studentNo=#{studentNo}")
    int deleteStu(Integer studentNo);
}
