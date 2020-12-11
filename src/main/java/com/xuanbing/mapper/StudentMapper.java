package com.xuanbing.mapper;

import com.xuanbing.bean.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xuanbing
 * @create 2020-12-06 20:43
 */
@Repository
@Mapper
public interface StudentMapper {

    //添加学生
    @Insert("insert into student values(#{student_id}, #{name}, #{password}, #{major}, #{duty}, #{status_id}, #{target_id}, #{branch_id})")
    public void insertStudent(Student student);

    //根据ID查询学生
    @Select("select * from student where student_id = #{student_id}")
    public Student getStudentById(String student_id);

    //查询所有学生
    @Select("select * from student")
    public List<Student> getAllStudents();

    //根据ID修改密码
    @Update("update student set password = #{password} where student_id = #{student_id}")
    public void updatePasswordById(String student_id, String password);

    //根据 branch_id 查询学生
    @Select("select * from student where branch_id = #{branch_id}")
    public List<Student> getStudentsByBranch(String branch_id);

    //根据 Student 修改学生信息
    @Update("update student set name = #{name}, password = #{password}, major = #{major}, duty = #{duty}, " +
            "status_id = #{status_id}, target_id = #{target_id}, branch_id = #{branch_id} " +
            "where student_id = #{student_id}")
    public void updateInfoByStudent(Student student);

    //根据 student_id 删除学生
    @Delete("delete from student where student_id = #{student_id}")
    public void deleteStudentById(String student_id);

    //根据 student_id 查询学生的当前身份
    @Select("select status_id from student where student_id = #{student_id}")
    public String getStatusByStudentId(String student_id);

}
