package com.example.demo.controller;

import com.example.demo.constant.CommonConst;
import com.example.demo.model.User;
import com.example.demo.service.IUserService;
import com.example.demo.vo.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:38
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/queryList")
    public ResultObject<List<User>> getUserList(){
        ResultObject<List<User>> resultObject = new ResultObject<>();
        try {
            List<User> userList = userService.getUserList();
            //비밀버호 안전보호
            userList.stream().forEach(user -> user.passwordEncryption());
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setResult(userList);
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("검색 실패");
        }
        return resultObject;
    }

}
