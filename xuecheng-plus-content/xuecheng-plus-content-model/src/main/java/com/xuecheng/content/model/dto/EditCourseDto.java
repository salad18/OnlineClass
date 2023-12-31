package com.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: SS
 * @DATE: 2023/12/31 20:04
 * @Decription: TODO
 * @Version 1.0
 **/
@Data
public class EditCourseDto extends AddCourseDto{
    @ApiModelProperty(value = "课程id", required = true)
    private Long id;
}
