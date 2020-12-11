package com.xuanbing.service;

import com.xuanbing.bean.Branch;
import com.xuanbing.mapper.BranchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuanbing
 * @create 2020-12-08 22:06
 */
@Service
public class BranchServiceImpl implements BranchService{

    @Autowired
    BranchMapper branchMapper;

    //更新党支部人数
    @Override
    public void updatePersonNum(String branch_id, int personNum) {
        branchMapper.updatePersonNum(branch_id, personNum);
    }

    //添加党支部
    @Override
    public void insertBranch(Branch branch) {
        branchMapper.insertBranch(branch);
    }

    //根据党支部名称查询党支部
    @Override
    public Branch getBranchByName(String name) {
        return branchMapper.getBranchByName(name);
    }

    // 根据 branch_id 查询党支部信息
    @Override
    public Branch getBranchById(String branch_id) {
        return branchMapper.getBranchById(branch_id);
    }

    // 查询所有的党支部信息
    @Override
    public List<Branch> getAllBranch() {
        return branchMapper.getAllBranch();
    }

    // 根据 branch_id 删除党支部
    @Override
    public void deleteBranchById(String branch_id) {
        branchMapper.deleteBranchById(branch_id);
    }

}
