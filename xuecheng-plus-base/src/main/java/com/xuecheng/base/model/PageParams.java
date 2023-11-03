package com.xuecheng.base.model;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: SS
 * @DATE: 2023/11/3 16:29
 * @Decription: 分页查询参数
 * @Version 1.0
 **/
@Data
@ToString
public class PageParams {
    //当前页码
    private Long pageNo = 1L;

    //每页记录数默认值
    private Long pageSize =10L;

    public PageParams(){
    }

    public PageParams(long pageNo,long pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
