package com.xuanbing.controller;

import com.xuanbing.bean.Admin;
import com.xuanbing.bean.LoginInfo;
import com.xuanbing.bean.Student;
import com.xuanbing.bean.Teacher;
import com.xuanbing.mapper.TeacherMapper;
import com.xuanbing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuanbing
 * @create 2020-12-06 20:53
 */
@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    TeacherServiceImpl teacherService;

    @Autowired
    AdminServiceImpl adminService;

    //处理登录请求
    @PostMapping("login")
    public Map<String, Object> login(@RequestBody LoginInfo loginInfo){

        Map<String, Object> map = new HashMap<>();

        if(loginInfo.getRole().equals("学生")){
            Student student = studentService.getStudentById(loginInfo.getId());
            //如果该学号存在
            if(student != null){
                if(student.getPassword().equals(loginInfo.getPassword())){
                    map.put("student", student);
                    map.put("status", true);
                    map.put("msg", "恭喜您，登录成功！");
                    map.put("role", "student");
                }else{
                    map.put("status", false);
                    map.put("msg", "密码错误！");
                }
            }else{
                map.put("status", false);
                map.put("msg", "账号不存在！");
            }
        }else if(loginInfo.getRole().equals("教师")){
            Teacher teacher = teacherService.getTeacherById(loginInfo.getId());

            //如果该教师ID存在
            if(teacher != null){
                //如果密码正确
                if(teacher.getPassword().equals(loginInfo.getPassword())){
                    map.put("status", true);
                    map.put("msg", "恭喜您，登录成功！");
                    map.put("teacher", teacher);
                    map.put("role", "teacher");
                }else{
                    //如果密码错误
                    map.put("status", false);
                    map.put("msg", "密码错误！");
                }
            }else{
                //如果教师ID不存在
                map.put("status", false);
                map.put("msg", "账号不存在！");
            }
        }else{
            Admin admin = adminService.getAdminById(loginInfo.getId());

            //如果管理员id存在
            if(admin != null){
                //如果密码正确
                if(admin.getPassword().equals(loginInfo.getPassword())){
                    map.put("status", true);
                    map.put("msg", "恭喜您，登录成功！");
                    map.put("admin", admin);
                    map.put("role", "admin");
                }else{
                    //如果密码错误
                    map.put("status", false);
                    map.put("msg", "密码错误！");
                }
            }else{
                //如果管理员id不存在
                map.put("status", false);
                map.put("msg", "账号不存在！");
            }
        }

        return map;
    }

}
