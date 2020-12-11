package com.xuanbing.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xuanbing
 * @create 2020-12-09 9:51
 */
@Data
@Accessors(chain = true)
public class BranchMenuItem {

    private String value;
    private String label;

}
