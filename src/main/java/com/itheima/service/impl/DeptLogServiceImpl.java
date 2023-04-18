package com.itheima.service.impl;

import com.itheima.mapper.DeptLogMapper;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class DeptLogServiceImpl implements DeptLogService {

    @Resource
    private DeptLogMapper deptLogMapper;

    //@Transactional   //如果外层方法有事务，则加入外层方法事务。
    @Transactional(propagation = Propagation.REQUIRES_NEW)   //无论外层方法是否有事务，都创建一个事务单独使用。
    @Override
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
