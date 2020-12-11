package com.xuanbing.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * @author xuanbing
 * @create 2020-11-11 20:52
 */
@Data
@Accessors(chain = true)
public class TargetAccept {

    private String student_id;
    private String target_id;
    private String task_one;
    private String task_two;
    private String task_three;
    private String self_evaluation;


}
