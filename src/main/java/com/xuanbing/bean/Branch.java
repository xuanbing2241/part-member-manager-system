package com.xuanbing.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xuanbing
 * @create 2020-12-08 21:58
 */
@Data
@Accessors(chain = true)
public class Branch {

    private String branch_id;
    private String name;
    private int person_num;

}
