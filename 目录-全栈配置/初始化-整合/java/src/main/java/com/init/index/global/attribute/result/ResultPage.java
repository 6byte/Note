package com.init.index.global.attribute.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 功能: 返回带分页信息的数据
 * 返回值
 *       (int)totalCount:总数
 *       (int)totalPages:总页数
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultPage<T> {
    private Integer totalCount;
    private Integer totalPages;
    private T data;
    private Integer status;

}
