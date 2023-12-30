package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;

import java.util.List;

/**
 * @Author: SS
 * @DATE: 2023/12/30 18:59
 * @Decription: TODO
 * @Version 1.0
 **/
public interface CourseCategoryService {

    public List<CourseCategoryTreeDto> queryTreeNodes(String id);
}
