package com.xuanbing.mapper;

import com.xuanbing.bean.TargetAccept;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author xuanbing
 * @create 2020-12-07 14:39
 */
@Repository
@Mapper
public interface TargetAcceptMapper {

    //根据student_id和target_id查询目标验收信息
    @Select("select * from target_accept where student_id = #{student_id} and target_id = #{target_id}")
    public TargetAccept getTargetAccept(String student_id, String target_id);

    //根据 student_id 删除目标验收信息
    @Delete("delete from target_accept where student_id = #{student_id}")
    public void deleteTargetAcceptByStudentId(String student_id);

    //插入目标验收信息
    @Insert("insert into target_accept values(#{student_id}, #{target_id}, #{task_one}, " +
            "#{task_two}, #{task_three}, #{self_evaluation})")
    public int insertTargetAccept(TargetAccept targetAccept);

    //更新目标验收信息
    @Update("update target_accept set task_one = #{task_one}, task_two = #{task_two}, task_three = #{task_three}," +
            "self_evaluation = #{self_evaluation} where student_id = #{student_id} and target_id = #{target_id}")
    public void updateTargetAccept(TargetAccept targetAccept);

}
