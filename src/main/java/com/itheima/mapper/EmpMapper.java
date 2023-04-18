package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
//@Mapper
public interface EmpMapper {

    /**
     * 查询员工，如果使用了分页插件，此处的查询就是分页查询
     * @return
     * @param name
     * @param gender
     * @param begin
     * @param end
     */
    //@Select("select * from emp order by update_time desc")
    List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 完成删除。
     * @param ids 被删除的员工id们
     */
    void delete(List<Integer> ids);


    /**
     * 完成新增
     * @param emp
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) VALUES (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void add(Emp emp);

    /**
     * 根据id查询员工
     * @param id
     */
    @Select("select * from emp where id=#{id}")
    Emp getById(Integer id);

    /**
     * 完成修改
     * @param emp
     */
    void update(Emp emp);

    /**
     * 根据用户名和密码查询用户信息
     * @param emp
     * @return
     */
    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getByUsernameAndPassword(Emp emp);

    /**
     * 根据部门id删除员工信息
     * @param deptId
     */
    @Delete("delete from emp where dept_id=#{deptId}")
    void deleteByDeptId(Integer deptId);
}
