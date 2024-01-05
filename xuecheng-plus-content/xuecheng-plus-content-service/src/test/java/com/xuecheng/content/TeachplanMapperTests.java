package com.xuecheng.content;

import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.TeachplanDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: SS
 * @DATE: 2023/11/3 21:12
 * @Decription: 课程计划测试
 * @Version 1.0
 **/
@SpringBootTest
class TeachplanMapperTests {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Test
    void testTeachplanMapper() {
        List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(117L);
        System.out.println(teachplanDtos);
    }

}
