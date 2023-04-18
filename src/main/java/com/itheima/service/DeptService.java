package com.itheima.service;

import com.itheima.pojo.Dept;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    /**
     * 查询所有部门
     * @return
     */
    List<Dept> list();

    /**
     * 根据id删除部门
     * @param id 要删除的部门编号
     */
    void delete(Integer id) throws FileNotFoundException;

    /**
     * 新增部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    Dept getById(Integer id);

    /**
     * 修改部门
     * @param dept
     */
    void update(Dept dept);
}
