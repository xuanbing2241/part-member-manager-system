package com.xuanbing.service;

import com.xuanbing.bean.Student;
import com.xuanbing.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuanbing
 * @create 2020-12-06 20:49
 */
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentMapper studentMapper;

    //插入学生
    @Override
    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    //根据学生ID查询学生信息
    public Student getStudentById(String student_id){
        Student student = studentMapper.getStudentById(student_id);
        return student;
    }

    //查询所有学生
    @Override
    public List<Student> getAllStudents() {
        return studentMapper.getAllStudents();
    }

    //根据学生ID修改密码
    @Override
    public void updatePasswordById(String student_id, String password) {
        studentMapper.updatePasswordById(student_id, password);
    }

    //根据 branch_id 查询学生
    @Override
    public List<Student> getStudentsByBranch(String branch_id) {
        List<Student> list = studentMapper.getStudentsByBranch(branch_id);
        return list;
    }

    // 根据Student更新学生信息
    @Override
    public void updateStudentInfoByStudent(Student student) {
        studentMapper.updateInfoByStudent(student);
    }

    //根据 student_id 删除学生
    @Override
    public void deleteStudentById(String student_id) {
        studentMapper.deleteStudentById(student_id);
    }

    //根据 student_id 查询该学生的当前身份
    @Override
    public String getStatusByStudentId(String student_id) {
        return studentMapper.getStatusByStudentId(student_id);
    }


}
