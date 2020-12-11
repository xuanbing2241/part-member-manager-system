package com.xuanbing.service;

import com.xuanbing.bean.TargetAccept;
import com.xuanbing.mapper.TargetAcceptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuanbing
 * @create 2020-12-07 14:55
 */
@Service
public class TargetAcceptServiceImpl implements TargetAcceptService{

    @Autowired
    TargetAcceptMapper targetAcceptMapper;

    //查询指定的目标验收信息
    @Override
    public TargetAccept getTargetAccept(String student_id, String target_id) {
        return targetAcceptMapper.getTargetAccept(student_id, target_id);
    }

    //插入目标验收信息
    @Override
    public void insertTargetAccept(TargetAccept targetAccept) {
        targetAcceptMapper.insertTargetAccept(targetAccept);
    }

    //更新目标验收信息
    @Override
    public void updateTargetAccept(TargetAccept targetAccept) {
        targetAcceptMapper.updateTargetAccept(targetAccept);
    }

    //根据 student_id 删除目标验收信息
    @Override
    public void deleteTargetAcceptByStudentId(String student_id) {
        targetAcceptMapper.deleteTargetAcceptByStudentId(student_id);
    }
}
