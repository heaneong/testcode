package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.constant.CommonConst;
import com.example.demo.model.Resource;
import com.example.demo.model.User;
import com.example.demo.service.IResourceService;
import com.example.demo.service.IUserRoleService;
import com.example.demo.service.IUserService;
import com.example.demo.vo.ResultObject;
import com.example.demo.vo.user.UserRequestVo;
import com.netflix.client.http.HttpResponse;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private IResourceService resourceService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("로그인")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ResultObject<User> login(String userName, String password, HttpSession session, HttpServletResponse res){
        ResultObject<User> resultObject = new ResultObject<>();

        //권한 필요 자원 없으면 저장
        String allResource = (String) session.getAttribute(CommonConst.SESSION_RESOURCES_NAME);
//        String allResource = (String) redisTemplate.opsForValue().get(CommonConst.SESSION_RESOURCES_NAME);
        if (StringUtils.isEmpty(allResource)){
            List<Resource> resourceList = resourceService.queryAllResources();
            session.setAttribute(CommonConst.SESSION_RESOURCES_NAME,JSON.toJSONString(resourceList));
//            redisTemplate.opsForValue().set(CommonConst.SESSION_RESOURCES_NAME,JSON.toJSONString(resourceList));
        }

        try {
            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
                resultObject.setCode(CommonConst.FAIL);
                resultObject.setMsg("로그인 이름 나 비밀번호 맞지안습니다");
            }
            User user = userService.getUserByUserName(userName);
            if (user == null || StringUtils.isEmpty(user.getUserId()) || !password.equals(user.getPassword())){
                resultObject.setCode(CommonConst.FAIL);
                resultObject.setMsg("로그인 이름 나 비밀번호 맞지안습니다");
            }
            UserRequestVo requestVo = new UserRequestVo();
            BeanUtils.copyProperties(user,requestVo);
            List<Resource> resources = userService.queryUserResources(requestVo);

            //로그인 상태 및 권한 저장
            session.setAttribute(CommonConst.SESSION_LOGIN_NAME+userName,JSON.toJSONString(resources));

            //username cookie 저장
            res.addCookie(new Cookie(CommonConst.COOKIE_USER_NAME,userName));

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

    @ApiOperation("호출")
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ResultObject<User> logout(String userName, HttpSession session){
        ResultObject<User> resultObject = new ResultObject<>();
        try {
            User user = userService.getUserByUserName(userName);
            if (user == null || StringUtils.isEmpty(user.getUserId()) ){
                resultObject.setCode(CommonConst.FAIL);
                resultObject.setMsg("실패");
            }
            //로그인 상태 삭재
            session.removeAttribute("login::user::"+userName);

            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("호출 성공");
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("호출 실패");
        }
        return resultObject;
    }

}
