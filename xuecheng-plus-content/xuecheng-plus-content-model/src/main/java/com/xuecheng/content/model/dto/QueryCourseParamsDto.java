package com.xuecheng.content.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: SS
 * @DATE: 2023/11/3 16:57
 * @Decription: 课程条件查询类
 * @Version 1.0
 **/
@Data
@ToString
public class QueryCourseParamsDto {
    //审核状态
    private String auditStatus;
    //课程名称
    private String courseName;
    //发布状态
    private String publishStatus;

}
