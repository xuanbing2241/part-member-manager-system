package com.xuanbing.service;

import com.xuanbing.bean.Admin;

/**
 * @author xuanbing
 * @create 2020-12-08 16:30
 */
public interface AdminService {

    //根据id查询管理员信息
    Admin getAdminById(String id);

}
