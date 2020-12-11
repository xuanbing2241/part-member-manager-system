package com.xuanbing.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xuanbing
 * @create 2020-12-06 20:57
 */
@Data
@Accessors(chain = true)
public class LoginInfo {

    private String id;
    private String password;
    private String role;

}
