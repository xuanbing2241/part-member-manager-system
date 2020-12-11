package com.xuanbing.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * @author xuanbing
 * @create 2020-11-10 17:06
 */
@Data
@Accessors(chain = true)
public class Student {

    private String student_id;
    private String name;
    private String password;
    private String major;
    private String duty;
    private String status_id;
    private String target_id;
    private String branch_id;

}
