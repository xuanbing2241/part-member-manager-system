package com.xuanbing.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xuanbing
 * @create 2020-12-09 15:14
 */
@Data
@Accessors(chain = true)
public class AllBranchList {

    private String branch_id;
    private String name;
    private int person_num;

}
