package com.example.demo.util;

import com.example.demo.constant.CommonConst;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * \* User: lihaining
 * \* Date: 2022/6/8
 * \* Time: 15:00
 * \* Description:
 * \
 */
public class CommonUtils {

    //ID 생성
    public static String createNewId(){
        String requestId = null;
        try {
            requestId = java.util.UUID.randomUUID().toString().replaceAll("-","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestId;
    }

    //지금 사용 하는 사용자 이름 받음
    public static String getCurrentUserName(HttpServletRequest request){
        String username = "";
        for (Cookie cookie : request.getCookies()) {
            if (CommonConst.COOKIE_USER_NAME.equals(cookie.getName())){
                username = cookie.getValue();
            }
        }
        return username;
    }

}
