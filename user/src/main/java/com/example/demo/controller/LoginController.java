package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.constant.CommonConst;
import com.example.demo.model.Resource;
import com.example.demo.model.User;
import com.example.demo.service.IUserRoleService;
import com.example.demo.service.IUserService;
import com.example.demo.vo.ResultObject;
import com.example.demo.vo.user.UserRequestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/")
@Api(value = "로그인 관리")
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("로그인")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ResultObject<User> login(String userName,String password){
        ResultObject<User> resultObject = new ResultObject<>();
        try {
            User user = userService.getUserByUserName(userName);
            if (user == null || StringUtils.isEmpty(user.getUserId()) || !password.equals(user.getPassword())){
                resultObject.setCode(CommonConst.FAIL);
                resultObject.setMsg("로그인 이름 나 비밀번호 맞지안습니다");
            }
            UserRequestVo requestVo = new UserRequestVo();
            BeanUtils.copyProperties(user,requestVo);
            List<Resource> resources = userService.queryUserResources(requestVo);
            //로그인 상태 및 권한 저장
            redisTemplate.opsForValue().set("login::user::"+userName, JSON.toJSON(resources),10*1000);

            //비밀버호 안전보호
            user.passwordEncryption();
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setResult(user);
            resultObject.setMsg("로그인 성공");
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("로그인 실패");
        }
        return resultObject;
    }

}
