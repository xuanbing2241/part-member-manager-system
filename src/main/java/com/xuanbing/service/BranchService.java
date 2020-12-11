package com.xuanbing.service;

import com.xuanbing.bean.Branch;

import java.util.List;

/**
 * @author xuanbing
 * @create 2020-12-08 22:05
 */
public interface BranchService {

    //更新党支部人数
    public void updatePersonNum(String branch_id, int personNum);

    //添加党支部
    public void insertBranch(Branch branch);

    //根据党支部名称查询党支部
    public Branch getBranchByName(String name);

    //根据 branch_id 查询党支部
    public Branch getBranchById(String branch_id);

    //查询所有的党支部信息
    public List<Branch> getAllBranch();

    //根据 branch_id 删除党支部
    public void deleteBranchById(String branch_id);

}
