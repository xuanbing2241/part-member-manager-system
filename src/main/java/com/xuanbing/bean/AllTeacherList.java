package com.xuanbing.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xuanbing
 * @create 2020-12-09 12:54
 */
@Data
@Accessors(chain = true)
public class AllTeacherList {

    private String teacher_id;
    private String name;
    private String branch;

}
