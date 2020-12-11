package com.xuanbing.service;

import com.xuanbing.bean.TargetAccept;


/**
 * @author xuanbing
 * @create 2020-12-07 14:51
 */
public interface TargetAcceptService {

    //根据student_id和target_id查询目标验收信息
    public TargetAccept getTargetAccept(String student_id, String target_id);

    //插入目标验收信息
    public void insertTargetAccept(TargetAccept targetAccept);

    //更新目标验收信息
    public void updateTargetAccept(TargetAccept targetAccept);

    //根据 student_id 删除目标验收信息
    public void deleteTargetAcceptByStudentId(String student_id);

}
