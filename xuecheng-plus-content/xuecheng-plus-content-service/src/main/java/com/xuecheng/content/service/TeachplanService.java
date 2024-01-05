package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveTechplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;

import java.util.List;

/**
 * @Author: SS
 * @DATE: 2024/1/5 16:04
 * @Decription: 课程计划管理相关接口
 * @Version 1.0
 **/
public interface TeachplanService {
    /**
     * 根据课程id查询课程计划
     * @param courseId 课程id
     * @return
     */
    public List<TeachplanDto> findTeachplanTree(Long courseId);

    /**
     * 新增/修改/保存课程计划
     * @param saveTechplanDto
     */
    public void saveTeachplan(SaveTechplanDto saveTechplanDto);
}
