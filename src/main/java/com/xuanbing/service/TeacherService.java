package com.xuanbing.service;

import com.xuanbing.bean.Teacher;

import java.util.List;

/**
 * @author xuanbing
 * @create 2020-12-07 17:11
 */
public interface TeacherService {

    //添加老师
    void insertTeacher(Teacher teacher);

    //根据teacher_id 删除教师信息
    void deleteTeacherById(String teacher_id);

    //根据 teacher_id 查询教师信息
    Teacher getTeacherById(String teacher_id);

    //查询所有教师信息
    List<Teacher> getAllTeachers();

    //根据 teacher_id 修改密码
    void updatePasswordById(String teacher_id, String password);

    //根据Teacher修改教师信息
    void updateTeacherInfo(Teacher teacher);

}
