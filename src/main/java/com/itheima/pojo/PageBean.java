package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 封装分页结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    private Long total;  //总记录数
    private List rows;  //当前页数据
}
