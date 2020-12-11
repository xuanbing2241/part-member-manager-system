package com.xuanbing.mapper;

import com.xuanbing.bean.Branch;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuanbing
 * @create 2020-12-08 22:01
 */
@Repository
@Mapper
public interface BranchMapper {

    //根据 branch_id 更新党支部人数
    @Update("update branch set person_num = #{person_num} where branch_id = #{branch_id}")
    public void updatePersonNum(String branch_id, int person_num);

    //添加党支部信息
    @Insert("insert into branch values(#{branch_id}, #{name}, #{person_num})")
    public void insertBranch(Branch branch);

    //根据 branch_id 查询党支部信息
    @Select("select * from branch where branch_id = #{branch_id}")
    public Branch getBranchById(String branch_id);

    //根据党支部名称查询党支部
    @Select("select * from branch where name = #{name}")
    public Branch getBranchByName(String name);

    //查询所有的党支部信息
    @Select("select * from branch")
    public List<Branch> getAllBranch();

    //根据 branch_id 删除党支部
    @Delete("delete from branch where branch_id = #{branch_id}")
    public void deleteBranchById(String branch_id);

}
