package com.xuanbing.mapper;

import com.xuanbing.bean.TargetInformation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xuanbing
 * @create 2020-12-07 13:19
 */
@Repository
@Mapper
public interface TargetInformationMapper {

    //根据student_id和target_id查询目标
    @Select("select * from target_information where student_id = #{student_id} and target_id = #{target_id}")
    public TargetInformation getTargetInfo(String student_id, String target_id);

    //根据 student_id 删除目标信息
    @Delete("delete from target_information where student_id = #{student_id}")
    public void deleteTargetInformationByStudentId(String student_id);

    //插入目标信息
    @Insert("insert into target_information values(#{student_id}, #{target_id}, #{thinking_report}," +
            "#{honorary_title}, #{first_rank}, #{second_rank}, #{scholarship_level}, #{ncre}, " +
            "#{contest}, #{social_practice}, #{sport_activity})")
    public int insertTargetInfo(TargetInformation targetInfo);

    //更新目标信息
    @Update("update target_information set thinking_report = #{thinking_report}, honorary_title = " +
            "#{honorary_title}, first_rank = #{first_rank}, second_rank = #{second_rank}, " +
            "scholarship_level = #{scholarship_level}, ncre = #{ncre}, contest = #{contest}, " +
            "social_practice = #{social_practice}, sport_activity = #{sport_activity} where " +
            "student_id = #{student_id} and target_id = #{target_id}")
    public void updateTargetInfo(TargetInformation targetInfo);

}
