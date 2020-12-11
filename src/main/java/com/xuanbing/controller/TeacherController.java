package com.xuanbing.controller;

import com.xuanbing.bean.*;
import com.xuanbing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuanbing
 * @create 2020-12-07 17:28
 */
@RestController
@CrossOrigin
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    TeacherServiceImpl teacherService;

    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    TargetAcceptServiceImpl targetAcceptService;

    @Autowired
    TargetInformationServiceImpl targetInformationService;

    @Autowired
    BranchServiceImpl branchService;

    //根据支部ID(branch_id)查询属于该支部的所有学生
    @GetMapping("queryBranchMembers")
    public List<StudentList> queryBranchMembers(String branch_id){
        List<Student> list = studentService.getStudentsByBranch(branch_id);

//        if(list.isEmpty()){
//            System.out.println("结果为空");
//        }else{
//            System.out.println("结果有" + list.size() + "条");
//        }

        List<StudentList> result = new ArrayList<>();
        //将每一个Student对象转成便于在表格中显示的StudentList对象
        for(Student s : list){
            StudentList sl = new StudentList();
            sl.setStudent_id(s.getStudent_id());
            sl.setName(s.getName());
            sl.setMajor(s.getMajor());
            if(s.getStatus_id().equals("1")){
                sl.setStatus("入党积极分子");
            }else if(s.getStatus_id().equals("2")){
                sl.setStatus("中共预备党员");
            }else if(s.getStatus_id().equals("3")){
                sl.setStatus("中共正式党员");
            }else{
                sl.setStatus("中共正式党员（毕业）");
            }

            result.add(sl);
        }
        return result;
    }

    //修改教师的密码
    @GetMapping("updatePasswordById")
    public Map<String, Object> updatePasswordById(String teacher_id, String password){
        Map<String, Object> map = new HashMap<>();
        try {
            teacherService.updatePasswordById(teacher_id, password);
            map.put("status", true);
            map.put("msg", "密码修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("msg", "由于未知原因，密码修改失败！");
        }
        return map;
    }

    //根据StudentList对象修改学生信息
    @PostMapping("updateStudentInfoByStudentList")
    public Map<String, Object> updateStudentInfoByStudentList(@RequestBody StudentList studentList){
        Map<String, Object> map = new HashMap<>();

        try {
            //查询要修改的学生信息
            Student student = studentService.getStudentById(studentList.getStudent_id());

            //更新姓名和班级
            student.setName(studentList.getName());
            student.setMajor(studentList.getMajor());

            //更新身份
            String status = studentList.getStatus();

            if(status.equals("入党积极分子")){
                student.setStatus_id("1");
                student.setTarget_id("1");
            }else if(status.equals("中共预备党员")){
                student.setStatus_id("2");
                student.setTarget_id("2");
            }else if(status.equals("中共正式党员")){
                student.setStatus_id("3");
                student.setTarget_id("3");
            }else{
                student.setStatus_id("4");
                student.setTarget_id("4");
            }

            // 更新数据库
            studentService.updateStudentInfoByStudent(student);
            map.put("status", true);
            map.put("msg", "信息更新成功！");
        } catch (Exception e) {
            map.put("status", false);
            map.put("msg", "由于未知原因，信息更新失败！");
            e.printStackTrace();
        }

        return map;
    }

    //根据 student_id 删除该学生的所有信息
    @GetMapping("deleteStudent")
    public Map<String, Object> deleteStudent(String student_id){
        Map<String, Object> map = new HashMap<>();

        try {

            //获取该学生对象
            Student student = studentService.getStudentById(student_id);

            //更新党支部人数
            Branch branch = branchService.getBranchById(student.getBranch_id());
            branchService.updatePersonNum(branch.getBranch_id(), branch.getPerson_num() - 1);

            //删除目标验收信息
            targetAcceptService.deleteTargetAcceptByStudentId(student_id);

            //删除目标信息
            targetInformationService.deleteTargetInformationByStudentId(student_id);

            //删除学生信息
            studentService.deleteStudentById(student_id);



            map.put("status", true);
            map.put("msg", "删除成功！");

        } catch (Exception e) {
            map.put("status", false);
            map.put("msg", "由于未知原因，删除失败！");
            e.printStackTrace();
        }

        return map;
    }

    // 根据 student_id 查询该学生当前的目标信息和目标验收信息
    @GetMapping("getAllTargetInfo")
    public Map<String, Object> getAllTargetInfo(String student_id){
        Map<String, Object> map = new HashMap<>();

        try {
            //查询该学生当前的身份信息
            String status_id = studentService.getStatusByStudentId(student_id);

            //如果该学生已经是中共正式党员（毕业）阶段  则显示其预备党员到正式党员时的目标信息
            if(status_id.equals("4")){
                status_id = "3";
            }

            //查询该学生当前的目标信息
            TargetInformation targetInfo = targetInformationService.getTargetInfo(student_id, status_id);

            //查询该学生当前的目标验收信息
            TargetAccept targetAccept = targetAcceptService.getTargetAccept(student_id, status_id);

            map.put("status", true);
            map.put("targetInfo", targetInfo);
            map.put("targetAccept", targetAccept);
            map.put("msg", "查询相关信息成功！（本信息测试环境使用，不应该显示在用户界面上。）");
        } catch (Exception e) {
            map.put("status", false);
            map.put("msg", "查询相关信息时发生未知错误！");
            e.printStackTrace();
        }

        return map;
    }

    // 同意学生进入下一个发展阶段
    @GetMapping("agreeDevelop")
    public Map<String, Object> agreeDevelop(String student_id){
        Map<String, Object> map = new HashMap<>();

        try {
            //查询该学生当前信息
            Student student = studentService.getStudentById(student_id);

            //如果当前为入党积极分子 则转为中共预备党员
            if(student.getStatus_id().equals("1")){
                student.setStatus_id("2");
                student.setTarget_id("2");
            }else if(student.getStatus_id().equals("2")){
                //如果当前为中共预备党员 则转为中共正式党员
                student.setStatus_id("3");
                student.setTarget_id("3");
            }else{
                //如果当前为中共正式党员 则转为中共正式党员（毕业）
                student.setStatus_id("4");
                student.setTarget_id("4");
            }

            //更新数据库
            studentService.updateStudentInfoByStudent(student);

            map.put("status", true);
            map.put("msg", "保存成功");
        } catch (Exception e) {
            map.put("status", false);
            map.put("msg", "由于未知原因，保存失败！");
            e.printStackTrace();
        }

        return map;
    }

}
