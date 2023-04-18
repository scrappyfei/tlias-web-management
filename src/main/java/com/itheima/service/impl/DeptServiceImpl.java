package com.itheima.service.impl;

import com.itheima.anno.MyTransactional;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

//@Transactional //表示该类所有的public修饰的方法进行事务管理
@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper deptMapper;

    @Resource
    private EmpMapper empMapper;

    @Resource
    private DeptLogService deptLogService;

    /**
     * 查询所有部门
     *
     * @return
     */
    @MyTransactional
    @Override
    public List<Dept> list() {
        //int i=1/0;
        //调用mapepr层方法，查询所有部门
        return deptMapper.list();
    }

    /**
     * 根据id删除部门
     *
     * @param id 要删除的部门编号
     */
    //@Transactional //表示该方法进行事务管理
    @Transactional(rollbackFor = Exception.class) //指定要进行回滚操作的异常类型
    //@Transactional(rollbackForClassName = "java.lang.Exception") //指定要进行回滚操作的异常类型
    @Override
    public void delete(Integer id) throws FileNotFoundException {
        try {
            //1 调用mapper层方法，删除部门
            deptMapper.delete(id);
            //int i=1/0;
            //new FileInputStream("c:/abc/abc/abc/abc.txt");
            //2 调用empMapper层方法，根据部门id删除员工信息
            empMapper.deleteByDeptId(id);
        } finally {
            //保存日志信息到日志表中
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("当前删除id=" + id + "的部门信息");
            deptLogService.insert(deptLog);
        }
    }

    /**
     * 新增部门
     *
     * @param dept
     */
    @MyTransactional
    @Override
    public void add(Dept dept) {
        //1 补充基础信息
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2 调用service，完成添加
        deptMapper.add(dept);
    }

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     */
    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    /**
     * 修改部门
     *
     * @param dept
     */
    @Override
    public void update(Dept dept) {
        //1 补充基础信息
        dept.setUpdateTime(LocalDateTime.now());
        //2 调用service，完成修改
        deptMapper.update(dept);
    }
}
