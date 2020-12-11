package com.xuanbing.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xuanbing
 * @create 2020-12-08 21:03
 */
@Data
@Accessors(chain = true)
public class AllStudentList {

    private String student_id;
    private String name;
    private String major;
    private String duty;
    private String status;
    private String branch;

}
