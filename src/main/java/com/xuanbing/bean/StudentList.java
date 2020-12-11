package com.xuanbing.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xuanbing
 * @create 2020-12-07 18:52
 */
@Data
@Accessors(chain = true)
public class StudentList {

    private String student_id;
    private String name;
    private String status;
    private String major;

}
