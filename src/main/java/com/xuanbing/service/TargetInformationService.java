package com.xuanbing.service;

import com.xuanbing.bean.TargetInformation;

/**
 * @author xuanbing
 * @create 2020-12-07 13:30
 */
public interface TargetInformationService {

    //查询目标
    public TargetInformation getTargetInfo(String student_id, String target_id);

    //插入目标
    public void insertTargetInfo(TargetInformation targetInfo);

    //更新目标
    public void updateTargetInfo(TargetInformation targetInfo);

    //根据 student_id 删除目标信息
    public void deleteTargetInformationByStudentId(String student_id);

}
