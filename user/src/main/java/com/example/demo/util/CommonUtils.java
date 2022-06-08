package com.example.demo.util;

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

}
