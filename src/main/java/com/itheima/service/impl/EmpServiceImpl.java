package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Resource
    private EmpMapper empMapper;

    /**
     * 分页查询
     * @param page 当前页码
     * @param pageSize 每页条数
     * @return 封装的分页结果PageBean对象
     */
    /*@Override
    public PageBean page(Integer page, Integer pageSize) {
        //1 设置分页参数
        PageHelper.startPage(page,pageSize);
        //2 调用mapper方法，完成分页查询
        Page<Emp> p = (Page<Emp>) empMapper.list();
        //3 封装分页结果到PageBean中返回
        return new PageBean(p.getTotal(),p.getResult());
    }*/

    /**
     * 带条件分页查询
     *
     * @param page     当前页码
     * @param pageSize 每页条数
     * @return 封装的分页结果PageBean对象
     */
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        //1 设置分页参数
        PageHelper.startPage(page, pageSize);
        //2 调用mapper方法，完成分页查询
        Page<Emp> p = (Page<Emp>) empMapper.list(name, gender, begin, end);
        //3 封装分页结果到PageBean中返回
        return new PageBean(p.getTotal(), p.getResult());
    }

    /**
     * 完成删除。
     *
     * @param ids 被删除的员工id们
     */
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    /**
     * 完成新增
     *
     * @param emp
     */
    @Override
    public void add(Emp emp) {
        //1 补充基础属性
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //2 调用mapper层方法，完成新增
        empMapper.add(emp);
    }

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 完成修改
     *
     * @param emp
     */
    @Override
    public void update(Emp emp) {
        //1 设置基础属性
        emp.setUpdateTime(LocalDateTime.now());
        //2 调用mapper方法，完成修改
        empMapper.update(emp);
    }

    /**
     * 处理登录
     *
     * @param emp
     * @return
     */
    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
