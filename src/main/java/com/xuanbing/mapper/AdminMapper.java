package com.xuanbing.mapper;

import com.xuanbing.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author xuanbing
 * @create 2020-12-08 16:28
 */
@Repository
@Mapper
public interface AdminMapper {

    //根据id查询管理员
    @Select("select * from admin where id = #{id}")
    public Admin getAdminById(String id);

}
