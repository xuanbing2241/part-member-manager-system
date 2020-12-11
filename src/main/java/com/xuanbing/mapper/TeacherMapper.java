package com.xuanbing.mapper;

import com.xuanbing.bean.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xuanbing
 * @create 2020-12-07 17:09
 */
@Repository
@Mapper
public interface TeacherMapper {

    //添加老师
    @Insert("insert into teacher values(#{teacher_id}, #{name}, #{password}, #{branch_id})")
    public void insertTeacher(Teacher teacher);

    //根据 teacher_id 删除教师信息
    @Delete("delete from teacher where teacher_id = #{teacher_id}")
    public void deleteTeacherById(String teacher_id);

    //根据 teacher_id 查询教师信息
    @Select("select * from teacher where teacher_id = #{teacher_id}")
    public Teacher getTeacherById(String teacher_id);

    //查询所有教师信息
    @Select("select * from teacher")
    public List<Teacher> getAllTeachers();

    //根据 teacher_id 修改密码
    @Update("update teacher set password = #{password} where teacher_id = #{teacher_id}")
    public void updatePasswordById(String teacher_id, String password);

    //根据 Teacher 修改教师信息
    @Update("update teacher set name = #{name}, password = #{password}, branch_id = #{branch_id}" +
            "where teacher_id = #{teacher_id}")
    public void updateTeacherInfo(Teacher teacher);

}
