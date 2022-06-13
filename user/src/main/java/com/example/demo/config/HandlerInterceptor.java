package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * \* User: lihaining
 * \* Date: 2022/6/13
 * \* Time: 11:56
 * \* Description: interceptor service 사용할수 있는 설정
 * \
 */
@Configuration
public class HandlerInterceptor implements WebMvcConfigurer {
    @Bean
    public PermissionInterceptor setBean2(){
//        System.out.println("springboot handler 관리 ");
        return new PermissionInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String [] exculudes = new String[]{"/*.html","/html/**","/js/**","/css/**","/images/**"};
        registry.addInterceptor(setBean2()).addPathPatterns("/**").excludePathPatterns(exculudes);
    }
}