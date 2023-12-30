package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.CourseCategory;
import lombok.Data;

import java.util.List;

/**
 * @Author: SS
 * @DATE: 2023/12/30 17:16
 * @Decription: TODO
 * @Version 1.0
 **/
@Data
public class CourseCategoryTreeDto extends CourseCategory implements java.io.Serializable{
    // 子节点
    List<CourseCategoryTreeDto> childrenTreeNodes;

}
