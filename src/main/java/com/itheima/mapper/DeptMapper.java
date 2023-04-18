package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
//@Mapper
public interface DeptMapper {

    /**
     * 查询所有部门
     * @return
     */
    @Select("select * from dept")
    List<Dept> list();

    /**
     * 根据id删除部门
     * @param id 要删除的部门编号
     */
    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    @Insert("insert into dept values(null,#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @Select("select * from dept where id=#{id}")
    Dept getById(Integer id);

    /**
     * 修改部门
     * @param dept
     */
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
