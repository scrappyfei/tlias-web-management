package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {

    /**
     * 分页查询
     * @param page 当前页码
     * @param pageSize 每页条数
     * @return 封装的分页结果PageBean对象
     */
    //PageBean page(Integer page, Integer pageSize);

    PageBean page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 完成删除。
     * @param ids 被删除的员工id们
     */
    void delete(List<Integer> ids);

    /**
     * 完成新增
     * @param emp
     */
    void add(Emp emp);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 完成修改
     * @param emp
     */
    void update(Emp emp);

    /**
     * 处理登录
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}

