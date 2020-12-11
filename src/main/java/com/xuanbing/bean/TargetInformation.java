package com.xuanbing.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * @author xuanbing
 * @create 2020-11-11 20:40
 */
@Data
@Accessors(chain = true)
public class TargetInformation {

    private String student_id;
    private String target_id;
    private String thinking_report;
    private String honorary_title;
    private String first_rank;
    private String second_rank;
    private String scholarship_level;
    private String ncre;
    private String contest;
    private String social_practice;
    private String sport_activity;


}
