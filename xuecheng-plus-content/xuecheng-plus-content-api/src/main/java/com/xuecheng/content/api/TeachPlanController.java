package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.SaveTechplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: SS
 * @DATE: 2023/12/31 21:10
 * @Decription: 课程计划接口
 * @Version 1.0
 **/
@Api(value = "课程计划编辑接口",tags = "课程计划编辑接口")
@RestController
public class TeachPlanController {

    @Autowired
    TeachplanService teachplanService;

    // 查询课程计划
    @ApiOperation("查询课程计划树形结构")
    @ApiImplicitParam(value = "courseId",name = "课程Id",required = true,dataType = "Long",paramType = "path")
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId) {
        return teachplanService.findTeachplanTree(courseId);
    }

    @ApiOperation("课程计划创建和修改")
    @PostMapping("/teachplan")
    public void saveTeachplan(@RequestBody SaveTechplanDto teachplan) {
        //
        teachplanService.saveTeachplan(teachplan);
    }
}
