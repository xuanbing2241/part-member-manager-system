package com.xuanbing.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * @author xuanbing
 * @create 2020-11-10 19:08
 */
@Data
@Accessors(chain = true)
public class Teacher {

    private String teacher_id;
    private String name;
    private String password;
    private String branch_id;

}
