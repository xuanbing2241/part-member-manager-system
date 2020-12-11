package com.xuanbing.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * @author xuanbing
 * @create 2020-11-11 19:20
 */
@Data
@Accessors(chain = true)
public class Admin {

    private String id;
    private String password;

}
