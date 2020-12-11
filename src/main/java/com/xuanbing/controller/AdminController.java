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
 * @create 2020-12-08 21:21
 */
@RestController
@CrossOrigin
@RequestMapping("admin")
public class AdminController {

    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    BranchServiceImpl branchService;

    @Autowired
    TeacherServiceImpl teacherService;

    @Autowired
    TargetInformationServiceImpl targetInformationService;

    @Autowired
    TargetAcceptServiceImpl targetAcceptService;

    //添加党支部
    @PostMapping("insertBranch")
    public Map<String, Object> insertBranch(@RequestBody Branch addBranch){
        Map<String, Object> map = new HashMap<>();

        //查询党支部的ID是否重复
        Branch isExist = branchService.getBranchById(addBranch.getBranch_id());

        //如果ID已存在
        if(isExist != null){
            map.put("status", false);
            map.put("msg", "ID已存在，添加失败！");
        }else{
            try {
                //如果不存在
                Branch branch = new Branch();

                branch.setBranch_id(addBranch.getBranch_id());
                branch.setName(addBranch.getName());
                branch.setPerson_num(0);

                //添加
                branchService.insertBranch(branch);

                map.put("status", true);
                map.put("msg", "添加成功！");
            } catch (Exception e) {
                map.put("status", false);
                map.put("msg", "由于未知原因，添加失败！");
                e.printStackTrace();
            }

        }

        return map;
    }

    //添加教师
    @PostMapping("insertTeacher")
    public Map<String, Object> insertTeacher(@RequestBody Teacher addTeacher){
        Map<String, Object> map = new HashMap<>();

        //查询工号是否重复
        Teacher isExist = teacherService.getTeacherById(addTeacher.getTeacher_id());

        //如果工号已存在
        if(isExist != null){
            map.put("status", false);
            map.put("msg", "该工号已存在！");
        }else{
            try {
                //如果工号不存在 则添加
                Teacher teacher = new Teacher();
                teacher.setTeacher_id(addTeacher.getTeacher_id());
                teacher.setName(addTeacher.getName());
                teacher.setPassword("123");

                //根据党支部名称查询对应的党支部
                Branch branch = branchService.getBranchByName(addTeacher.getBranch_id());

                //获取对应的党支部ID
                teacher.setBranch_id(branch.getBranch_id());

                //将信息添加进数据库
                teacherService.insertTeacher(teacher);

                map.put("status", true);
                map.put("msg", "添加成功！");
            } catch (Exception e) {
                map.put("status", false);
                map.put("msg", "由于未知原因，添加失败！");
                e.printStackTrace();
            }
        }

        return map;
    }

    //添加学生
    @PostMapping("insertStudent")
    public Map<String, Object> insertStudent(@RequestBody Student addStudent){
        Map<String, Object> map = new HashMap<>();

        Student isExist = studentService.getStudentById(addStudent.getStudent_id());

        if(isExist != null){
            map.put("status", false);
            map.put("msg", "该学号已存在！");
        }else{
            try {
                Student student = new Student();

                student.setStudent_id(addStudent.getStudent_id());
                student.setPassword("123");
                student.setName(addStudent.getName());
                student.setMajor(addStudent.getMajor());
                student.setDuty(addStudent.getDuty());

                if(addStudent.getStatus_id().equals("入党积极分子")){
                    student.setStatus_id("1");
                    student.setTarget_id("1");
                }else if(addStudent.getStatus_id().equals("中共预备党员")){
                    student.setStatus_id("2");
                    student.setTarget_id("2");
                }else if(addStudent.getStatus_id().equals("中共正式党员")){
                    student.setStatus_id("3");
                    student.setTarget_id("3");
                }else{
                    student.setStatus_id("4");
                    student.setTarget_id("4");
                }

                Branch branch = branchService.getBranchByName(addStudent.getBranch_id());

                student.setBranch_id(branch.getBranch_id());

                //将新学生信息插入数据库
                studentService.insertStudent(student);

                //更新党支部人数
                branchService.updatePersonNum(student.getBranch_id(),branch.getPerson_num() + 1);

                map.put("status", true);
                map.put("msg", "添加成功！");
            } catch (Exception e) {
                map.put("status", false);
                map.put("msg", "由于未知原因，添加失败！");
                e.printStackTrace();
            }
        }

        return map;
    }

    // 删除党支部
    @GetMapping("deleteBranchById")
    public Map<String, Object> deleteBranchById(String branch_id){
        Map<String, Object> map = new HashMap<>();

        try {

            //清空该党支部下的所有成员信息

            //查询属于该党支部的所有学生
            List<Student> students = studentService.getStudentsByBranch(branch_id);

            //删除这些学生的相关记录
            for(Student s : students){
                //删除目标信息
                targetInformationService.deleteTargetInformationByStudentId(s.getStudent_id());
                //删除目标验收信息
                targetAcceptService.deleteTargetAcceptByStudentId(s.getStudent_id());
                //删除学生信息
                studentService.deleteStudentById(s.getStudent_id());
            }


            //删除党支部
            branchService.deleteBranchById(branch_id);

            map.put("status", true);
            map.put("msg", "删除成功！");
        } catch (Exception e) {
            map.put("status", false);
            map.put("msg", "由于某些未知原因，删除失败！");
            e.printStackTrace();
        }

        return map;

    }

    //查询所有党支部信息并封装成在页面中显示的AllBranchList对象
    @GetMapping("getAllBranchList")
    public List<AllBranchList> getAllBranchList(){
        List<AllBranchList> result = new ArrayList<>();

        //获取所有的党支部信息
        List<Branch> list = branchService.getAllBranch();

        //遍历所有信息，并封装成AllBranchList对象
        for(Branch b : list){
            AllBranchList allBranchList = new AllBranchList();
            allBranchList.setBranch_id(b.getBranch_id());
            allBranchList.setName(b.getName());
            allBranchList.setPerson_num(b.getPerson_num());

            result.add(allBranchList);
        }


        return result;
    }

    //根据teacher_id删除教师信息
    @GetMapping("deleteTeacherById")
    public Map<String, Object> deleteTeacherById(String teacher_id){

        Map<String, Object> map = new HashMap<>();

        System.out.println(teacher_id);

        try {
            //删除该教师
            teacherService.deleteTeacherById(teacher_id);

            map.put("status", true);
            map.put("msg", "删除成功！");
        } catch (Exception e) {
            map.put("status", false);
            map.put("msg", "由于未知原因，删除失败！");
            e.printStackTrace();
        }


        return map;
    }

    //根据AllTeacherList修改Teacher信息
    @PostMapping("updateTeacherInfo")
    public Map<String, Object> updateTeacherInfo(@RequestBody AllTeacherList allTeacherList){
        Map<String, Object> map = new HashMap<>();

        try {

            //获取需要修改的教师信息
            Teacher teacher = teacherService.getTeacherById(allTeacherList.getTeacher_id());

            teacher.setName(allTeacherList.getName());

            //查询所有的党支部信息
            List<Branch> branches = branchService.getAllBranch();

            //找到与之匹配的党支部id
            for(Branch b : branches){
                if(b.getName().equals(allTeacherList.getBranch())){
                    teacher.setBranch_id(b.getBranch_id());
                    break;
                }
            }

            System.out.println(teacher);

            //提交更新
            teacherService.updateTeacherInfo(teacher);

            map.put("status", true);
            map.put("msg", "更新成功！");
        } catch (Exception e) {
            map.put("status", false);
            map.put("msg", "由于未知原因，更新失败！");
            e.printStackTrace();
        }

        return map;
    }

    //查询所有教师信息
    @GetMapping("getAllTeachers")
    public List<AllTeacherList> getAllTeachers(){
        List<AllTeacherList> result = new ArrayList<>();

        //查询所有教师信息
        List<Teacher> teachers = teacherService.getAllTeachers();

        //遍历全部Teacher 并封装成便于页面展示的AllTeacherList对象
        for(Teacher t : teachers){
            AllTeacherList allTeacherList = new AllTeacherList();
            allTeacherList.setTeacher_id(t.getTeacher_id());
            allTeacherList.setName(t.getName());
            allTeacherList.setBranch(branchService.getBranchById(t.getBranch_id()).getName());

            result.add(allTeacherList);
        }

        return result;
    }

    //查询所有学生，并封装进AllStudentList用于展示在管理员界面上
    @GetMapping("getAllStudents")
    public List<AllStudentList> getAllStudents(){

        List<AllStudentList> result = new ArrayList<>();

        //查询所有学生
        List<Student> list = studentService.getAllStudents();

        //遍历所有的学生 并封装成AllStudentList对象
        for(Student student : list){
            AllStudentList allStudentList = new AllStudentList();
            allStudentList.setStudent_id(student.getStudent_id());
            allStudentList.setName(student.getName());
            allStudentList.setMajor(student.getMajor());
            allStudentList.setDuty(student.getDuty());

            //根据 status_id 设置对应的身份
            String status = student.getStatus_id();
            if(status.equals("1")){
                allStudentList.setStatus("入党积极分子");
            }else if(status.equals("2")){
                allStudentList.setStatus("中共预备党员");
            }else if(status.equals("3")){
                allStudentList.setStatus("中共正式党员");
            }else{
                allStudentList.setStatus("中共正式党员（毕业）");
            }

            //获取 branch_id
            String branch = student.getBranch_id();

            // 根据 branch_id 查询其对应的党支部名称
            allStudentList.setBranch(branchService.getBranchById(branch).getName());

            result.add(allStudentList);

        }

        return result;
    }

    //查询所有的党支部信息
    @GetMapping("getAllBranch")
    public List<BranchMenuItem> getAllBranch(){

        //存放结果的list
        List<BranchMenuItem> result = new ArrayList<>();

        //获取所有的党支部信息
        List<Branch> list = branchService.getAllBranch();

        //遍历所有的党支部对象 并将其封装成便于放入下拉菜单展示的BranchMenuItem对象
        for(Branch b : list){
            BranchMenuItem bm = new BranchMenuItem();
            bm.setValue(b.getName());
            bm.setLabel(b.getName());
            result.add(bm);
        }

        return result;
    }

    //根据ALLStudentList更新学生信息
    @PostMapping("updateStudentInfo")
    public Map<String, Object> updateStudentInfo(@RequestBody AllStudentList allStudentList){

        Map<String, Object> map = new HashMap<>();

        try {
            //获取需要修改的学生信息
            Student s = studentService.getStudentById(allStudentList.getStudent_id());

            //修改数据
            s.setName(allStudentList.getName());
            s.setMajor(allStudentList.getMajor());
            s.setDuty(allStudentList.getDuty());

            //修改身份
            if(allStudentList.getStatus().equals("入党积极分子")){
                s.setStatus_id("1");
                s.setTarget_id("1");
            }else if(allStudentList.getStatus().equals("中共预备党员")){
                s.setStatus_id("2");
                s.setTarget_id("2");
            } else if (allStudentList.getStatus().equals("中共正式党员")) {
                s.setStatus_id("3");
                s.setTarget_id("3");
            }else{
                s.setStatus_id("4");
                s.setTarget_id("4");
            }

            //修改党支部
            List<Branch> branchs = branchService.getAllBranch();

            //遍历寻找对应的branch_id
            for(Branch b : branchs){
                if(b.getName().equals(allStudentList.getBranch())){

                    //原来的党支部人数-1
                    Branch beforeBranch = branchService.getBranchById(s.getBranch_id());
                    branchService.updatePersonNum(beforeBranch.getBranch_id(), beforeBranch.getPerson_num() - 1);

                    //当前的党支部人数+1
                    branchService.updatePersonNum(b.getBranch_id(), b.getPerson_num() + 1);

                    s.setBranch_id(b.getBranch_id());
                    break;
                }
            }

            //提交修改
            studentService.updateStudentInfoByStudent(s);

            map.put("status", true);
            map.put("msg", "修改成功！");
        } catch (Exception e) {
            map.put("status", false);
            map.put("msg", "由于未知原因，修改失败！");
            e.printStackTrace();
        }

        return map;

    }

}
