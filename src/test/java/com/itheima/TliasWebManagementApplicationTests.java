package com.itheima;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import com.itheima.service.DeptService;
import com.itheima.service.ServiceA;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class TliasWebManagementApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    @Test
    void contextLoads() {
        //1 告诉分页查询查询第几页几条数据
        PageHelper.startPage(1,10);
        //2 分页查询
        Page<Emp> page = (Page<Emp>) empMapper.list(null, null, null, null);

        //list.forEach(emp -> log.info("{}",emp));
        //3 获取分页结果
        log.info("page={}",page);
        log.info("总记录数:{}",page.getTotal());
        List<Emp> list = page.getResult();
        log.info("当前页数据:{}",list);
    }

    /**
     * 生成jwt令牌
     */
    @Test
    void testGenJwt(){
        //2 创建map集合，向令牌中保存自定义信息
        Map<String, Object> claims=new HashMap<>();
        claims.put("id",1);
        claims.put("username","jinyong");
        claims.put("name","金庸");
        //1 生成令牌
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "itheima")  //设置签名算法(HS256)和密钥(itheima)
                .setClaims(claims) //设置有效载荷（自定义信息）
                .setExpiration(new Date(System.currentTimeMillis() + 600 * 1000))  //有效期，超时时间，单位毫秒
                .compact();  //转成字符串
        log.info("生成的jwt令牌：{}",jwt);

    }

    /**
     * 解析jwt令牌
     */
    @Test
    void testParseJwt(){
        //1 解析jwt令牌获取载荷数据
        Claims claims = Jwts.parser()
                .setSigningKey("itheima") //设置解析的密钥，要和生成jwt令牌时指定的密钥一样。
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi6YeR5bq4IiwiaWQiOjEsImV4cCI6MTY4MTM1OTU3MCwidXNlcm5hbWUiOiJqaW55b25nIn0.cdKimClICsNYDYj9iXg7GfAluY5c18uEvwj3-sxujIw")  //解析jwt令牌
                .getBody();

        log.info("claims={}",claims);
    }

    @Autowired
    private ServiceA serviceA;
    /**
     * 测试事务的传播信息
     */
    @Test
    public void testPropagation(){
        serviceA.a();
    }

    @Autowired
    private DeptService deptService;

    @Test
    public void testAop() throws FileNotFoundException {

        log.info("deptService={}",deptService.getClass());

        List<Dept> list = deptService.list();
        //System.out.println(list);
        //list.forEach(dept -> log.info("{}",dept));

        //deptService.delete(10);
    }
}
