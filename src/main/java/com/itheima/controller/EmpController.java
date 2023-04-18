package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@RestController
@RequestMapping("/emps")  //公共的访问路径
@Slf4j
public class EmpController {

    @Autowired
    private EmpService empService;
    /**
     * 分页查询
     * @return
     */
    /*@GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize){
        //1 接收请求参数
        //2 调用service，完成分页查询，得到PageBean对象
        PageBean pageBean=empService.page(page,pageSize);
        //3 响应Result
        return Result.success(pageBean);
    }*/

    /**
     * 带条件分页查询
     * @return
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name,Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        PageBean pageBean= null;
        try {
            log.info("5、带条件分页查询员工信息");
            //1 接收请求参数
            //2 调用service，完成分页查询，得到PageBean对象
            pageBean = empService.page(page,pageSize,name,gender,begin,end);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("查询失败！");
        }
        //3 响应Result
        return Result.success(pageBean);
    }

    /**
     * 删除员工
     * @return
     */
    @Log
    @DeleteMapping("/{ids}")  // /emps/1
    public Result delete(@PathVariable List<Integer> ids){
        //int i=1/0;
        //1 接收请求参数
        //2 调用service，完成删除。
        empService.delete(ids);
        //3 响应Result
        return Result.success();
    }

    /**
     * 新增员工
     * @return
     */
    @Log
    @PostMapping  // /emps +post
    public Result add(@RequestBody Emp emp){
        //2 调用service层方法，完成新增
        empService.add(emp);
        //3 响应Result
        return Result.success();
    }

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        //1 调用service，根据id查询员工
        Emp emp=empService.getById(id);
        //2 响应Result
        return Result.success(emp);
    }

    /**
     * 修改员工
     * @param emp
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        //1 调用service，完成修改
        empService.update(emp);
        //2 响应Result
        return Result.success();
    }
}
