package com.xuanbing.service;

import com.xuanbing.bean.Teacher;
import com.xuanbing.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuanbing
 * @create 2020-12-07 17:13
 */
@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    TeacherMapper teacherMapper;

    //添加老师
    @Override
    public void insertTeacher(Teacher teacher) {
        teacherMapper.insertTeacher(teacher);
    }

    //根据 teacher_id 删除教师信息
    @Override
    public void deleteTeacherById(String teacher_id) {
        teacherMapper.deleteTeacherById(teacher_id);
    }

    //根据 teacher_id 查询教师信息
    @Override
    public Teacher getTeacherById(String teacher_id) {
        return teacherMapper.getTeacherById(teacher_id);
    }

    //查询所有教师信息
    @Override
    public List<Teacher> getAllTeachers() {
        return teacherMapper.getAllTeachers();
    }

    //查询所有教师信息


    //根据 teacher_id 修改密码
    @Override
    public void updatePasswordById(String teacher_id, String password) {
        teacherMapper.updatePasswordById(teacher_id, password);
    }

    //根据Teacher修改教师信息
    @Override
    public void updateTeacherInfo(Teacher teacher) {
        teacherMapper.updateTeacherInfo(teacher);
    }

}
