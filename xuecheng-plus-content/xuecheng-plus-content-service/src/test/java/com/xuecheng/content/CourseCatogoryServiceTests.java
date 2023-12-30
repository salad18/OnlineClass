package com.xuecheng.content;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: SS
 * @DATE: 2023/11/3 21:12
 * @Decription: TODO
 * @Version 1.0
 **/
@SpringBootTest
class CourseCatogoryServiceTests {

    @Autowired
    CourseCategoryService courseCategoryService;


    @Test
    void testCatogoryService() {
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryService.queryTreeNodes("1");
        System.out.println(courseCategoryTreeDtos);
    }

}
