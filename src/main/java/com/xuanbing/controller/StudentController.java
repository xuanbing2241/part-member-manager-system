package com.xuanbing.controller;

import com.xuanbing.bean.TargetAccept;
import com.xuanbing.bean.TargetInformation;
import com.xuanbing.service.StudentService;
import com.xuanbing.service.StudentServiceImpl;
import com.xuanbing.service.TargetAcceptServiceImpl;
import com.xuanbing.service.TargetInformationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuanbing
 * @create 2020-12-07 13:35
 */
@RestController
@CrossOrigin
@RequestMapping("student")
public class StudentController {

    @Autowired
    TargetInformationServiceImpl targetInformationService;

    @Autowired
    TargetAcceptServiceImpl targetAcceptService;

    @Autowired
    StudentServiceImpl studentService;

    //学生插入或更新当前阶段目标
    @PostMapping("insertInfo")
    public Map<String, Object> insertInfo(@RequestBody TargetInformation targetInfo){
        Map<String, Object> map = new HashMap<>();

        try {
            //根据传过来的student_id和target_id查询该目标是否存在
            TargetInformation target = targetInformationService.getTargetInfo(targetInfo.getStudent_id(),
                    targetInfo.getTarget_id());

            //如果已经存在 则执行更新操作
            if(target != null){
                targetInformationService.updateTargetInfo(targetInfo);
                map.put("status", true);
                map.put("msg", "目标信息修改成功！");
            }else{
                //如果不存在则执行插入操作
                targetInformationService.insertTargetInfo(targetInfo);
                map.put("status", true);
                map.put("msg", "目标信息建立成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("msg", "发生未知异常，目标更新失败！");
        }

        return map;
    }


    //学生插入或者更新目标验收信息
    @PostMapping("insertCheckTarget")
    public Map<String, Object> insertCheckTarget(@RequestBody TargetAccept targetAccept){
        Map<String, Object> map = new HashMap<>();

        try {
            //根据 Student_id 和 target_id 查询验收记录是否已经存在
            TargetAccept checkInfo = targetAcceptService.getTargetAccept(targetAccept.getStudent_id(),
                    targetAccept.getTarget_id());

            //如果已经存在 则执行更新操作
            if(checkInfo != null){
                targetAcceptService.updateTargetAccept(targetAccept);
                map.put("status", true);
                map.put("msg", "目标验收信息更新成功！");
            }else{
                //如果不存在 则执行插入操作
                targetAcceptService.insertTargetAccept(targetAccept);
                map.put("status", true);
                map.put("msg", "目标验收信息建立成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("msg", "发生未知错误，目标验收信息保存失败！");
        }

        return map;
    }

    //学生修改密码
    @GetMapping("updatePasswordById")
    public Map<String, Object> updatePasswordById(String student_id, String password){
        Map<String, Object> map = new HashMap<>();

//        System.out.println("信息如下：");
//        System.out.println(student_id + "       " + password);

        try {
            studentService.updatePasswordById(student_id, password);
            map.put("status", true);
            map.put("msg", "密码修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("msg", "由于未知原因，密码修改失败！");
        }

        return map;
    }

}
