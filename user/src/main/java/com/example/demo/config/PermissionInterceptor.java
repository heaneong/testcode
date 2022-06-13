package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import com.example.demo.constant.CommonConst;
import com.example.demo.model.Resource;
import com.example.demo.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * \* User: lihaining
 * \* Date: 2022/6/10
 * \* Time: 14:00
 * \* Description: 권한 필요한 페이지 에 권한 체크
 * \
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private IResourceService resourceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        //방문 uri
        String requestURI = request.getRequestURI();

        //전체 자원에 없는 경우 관리 필요 없음.
        String attribute = (String) session.getAttribute(CommonConst.SESSION_RESOURCES_NAME);
        if (StringUtils.isEmpty(attribute)){
            List<Resource> resourceList = resourceService.queryAllResources();
            session.setAttribute(CommonConst.SESSION_RESOURCES_NAME,attribute = JSON.toJSONString(resourceList));
//            redisTemplate.opsForValue().set(CommonConst.SESSION_RESOURCES_NAME,JSON.toJSONString(resourceList));
        }
        List<Resource> allResource = JSON.parseArray(attribute, Resource.class);
        List<Resource> checkAll = allResource.stream().filter(resource -> requestURI.contains(resource.getUrl())).collect(Collectors.toList());
        if (checkAll == null || checkAll.size() <= 0){
            return true;
        }

        //방문 주소 권한 체크
        //로그인 사용자 이름 얻음
        String username = "";
        for (Cookie cookie : request.getCookies()) {
            if (CommonConst.COOKIE_USER_NAME.equals(cookie.getName())){
                username = cookie.getValue();
            }
        }

        if (StringUtils.isEmpty(username)){
            //권한 없음
//            response.sendRedirect("/error_page");
            return false;
        }

        List<Resource> resourceList = JSON.parseArray(String.valueOf(session.getAttribute(CommonConst.SESSION_LOGIN_NAME + username)),Resource.class);

        List<Resource> collect = resourceList.stream().filter(resource -> requestURI.contains(resource.getUrl())).collect(Collectors.toList());

        if (collect == null || collect.size() <= 0){
            //사용자 방문 권한 없습니다.
//            response.sendRedirect("/error_page");
            return false;
        }

        return true;
    }
}
