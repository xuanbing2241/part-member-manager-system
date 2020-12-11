package com.xuanbing.service;

import com.xuanbing.bean.Admin;
import com.xuanbing.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuanbing
 * @create 2020-12-08 16:31
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminMapper adminMapper;

    //根据id查询管理员
    @Override
    public Admin getAdminById(String id) {
        Admin admin = adminMapper.getAdminById(id);
        return admin;
    }

}
