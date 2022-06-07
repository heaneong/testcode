package com.example.nacos.demo.controller;

import com.example.nacos.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* User: lihaining
 * \* Date: 2022/6/3
 * \* Time: 15:29
 * \* Description:
 * \
 */

@RestController
@RefreshScope //使当前类下的配置支持Nacos的动态刷新功能。
public class ConfigClientController {
    @Value("${config.port}")
    private String configInfo;

    @Autowired
    private TestService testService;

    @GetMapping("/config/info")
    public String getConfigInfo(String id) {
        return testService.getById(id).toString();
    }
}
