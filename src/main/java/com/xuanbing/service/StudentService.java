package com.xuanbing.service;

import com.xuanbing.bean.Student;

import java.util.List;

/**
 * @author xuanbing
 * @create 2020-12-06 20:48
 */
public interface StudentService {

    //插入学生
    void insertStudent(Student student);

    //根据学生ID查询学生信息
    Student getStudentById(String student_id);

    //查询所有学生
    List<Student> getAllStudents();

    //根据学生ID修改密码
    void updatePasswordById(String student_id, String password);

    //根据 branch_id 查询学生
    List<Student> getStudentsByBranch(String branch_id);

    //根据Student更新学生信息
    void updateStudentInfoByStudent(Student student);

    //根据 student_id 删除学生信息
    void deleteStudentById(String student_id);

    //根据 student_id 查询学生的当前身份
    String getStatusByStudentId(String student_id);

}
