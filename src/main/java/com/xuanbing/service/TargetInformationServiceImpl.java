package com.xuanbing.service;

import com.xuanbing.bean.TargetInformation;
import com.xuanbing.mapper.TargetInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuanbing
 * @create 2020-12-07 13:32
 */
@Service
public class TargetInformationServiceImpl implements TargetInformationService{

    @Autowired
    TargetInformationMapper targetInformationMapper;

    //查询目标
    @Override
    public TargetInformation getTargetInfo(String student_id, String target_id) {
        return targetInformationMapper.getTargetInfo(student_id, target_id);
    }

    //插入目标
    public void insertTargetInfo(TargetInformation targetInfo) {
        targetInformationMapper.insertTargetInfo(targetInfo);
    }

    //更新目标
    public void updateTargetInfo(TargetInformation targetInfo) {
        targetInformationMapper.updateTargetInfo(targetInfo);
    }

    //根据 student_id 删除目标信息
    @Override
    public void deleteTargetInformationByStudentId(String student_id) {
        targetInformationMapper.deleteTargetInformationByStudentId(student_id);
    }
}
