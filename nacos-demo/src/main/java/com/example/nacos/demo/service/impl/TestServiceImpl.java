package com.example.nacos.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nacos.demo.mapper.TestMapper;
import com.example.nacos.demo.model.Test;
import com.example.nacos.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * \* User: lihaining
 * \* Date: 2022/6/4
 * \* Time: 12:38
 * \* Description:
 * \
 */
@Service
@Slf4j
public class TestServiceImpl extends ServiceImpl<TestMapper,Test> implements TestService{

    @Autowired
    private TestMapper testMapper;

    @Override
    public Test getById(String id) {
        return baseMapper.selectById(id);
    }
}
