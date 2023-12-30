package com.xuecheng.content.service.impl;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: SS
 * @DATE: 2023/12/30 19:00
 * @Decription: TODO
 * @Version 1.0
 **/
@Slf4j
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
        // 调用mapper递归查询出分类信息
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);

        // 找到每个节点的子节点，最终封装成List<CourseCategoryTreeDto>
        // 先将list转成map,filter(item->!id.equals(item.getId()))排除根节点
        Map<String, CourseCategoryTreeDto> map = courseCategoryTreeDtos.stream().filter(item -> !id.equals(item.getId())).collect(Collectors.toMap(key -> key.getId(), value -> value, (key1, key2) -> key2));

        //最终返回的list
        ArrayList<CourseCategoryTreeDto> courseCategoryList = new ArrayList<>();

        // 从头遍历
        courseCategoryTreeDtos.stream().filter(item -> !id.equals(item.getId())).forEach(item -> {
            // 向list中写入元素
            if (item.getParentid().equals(id)) {
                courseCategoryList.add(item);
            } else {
                // 找到对应父节点
                CourseCategoryTreeDto courseCategoryTreeDto = map.get(item.getParentid());
                if (courseCategoryTreeDto.getChildrenTreeNodes() == null) {
                    courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
                }
                // 加入到父节点的子集中
                courseCategoryTreeDto.getChildrenTreeNodes().add(item);
            }
        });

        return courseCategoryList;
    }
}
