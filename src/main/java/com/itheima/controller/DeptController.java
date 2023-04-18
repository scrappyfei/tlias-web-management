package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * 部门管理Controller
 */
@RestController
@Slf4j
@RequestMapping("/depts") //访问该类中的所有方法默认都有一个一级路径/depts
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询所有部门
     * @return
     */
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list() {
        log.info("查询所有部门");
        //1 调用servic层方法，查询所有部门
        List<Dept> list = deptService.list();
        //2 响应Result
        return Result.success(list);
    }

    /**
     * 根据id删除部门
     * @param id 要删除的部门编号
     */
    @DeleteMapping("/{id}")  //最终的访问路径：类上的路径+方法上的路径
    public Result delete(@PathVariable Integer id) throws FileNotFoundException {
        log.info("删除部门id={}", id);
        //2 调用servicde，完成删除
        deptService.delete(id);
        //3 响应Result
        return Result.success();
    }

    /**
     * 新增部门
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门:{}",dept);
        //2 调用service，新增部门
        deptService.add(dept);
        //3 响应result对象
        return Result.success();
    }

    /**
     * 根据id查询部门
     * @param id 要查询的部门编号
     */
    @GetMapping("/{id}")  //最终的访问路径：类上的路径+方法上的路径
    public Result getById(@PathVariable Integer id) {
        log.info("查询部门id={}", id);
        //2 调用servicde，完成查询
        Dept dept=deptService.getById(id);
        //3 响应Result
        return Result.success(dept);
    }

    /**
     * 新增部门
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门:{}",dept);
        //2 调用service，修改部门
        deptService.update(dept);
        //3 响应result对象
        return Result.success();
    }
}
