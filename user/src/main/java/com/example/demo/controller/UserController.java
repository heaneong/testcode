package com.example.demo.controller;

import com.example.demo.constant.CommonConst;
import com.example.demo.model.Resource;
import com.example.demo.model.User;
import com.example.demo.service.IUserRoleService;
import com.example.demo.service.IUserService;
import com.example.demo.vo.ResultObject;
import com.example.demo.vo.user.UserRequestVo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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
@Api(value = "사용자 관리")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @ApiOperation("전체 사용자 검색")
    @RequestMapping(value = "/queryList",method = RequestMethod.GET)
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

    @ApiOperation("지정 사용자 검색")
    @RequestMapping(value = "/queryById",method = RequestMethod.GET)
    public ResultObject<User> getUserById(String id){
        ResultObject<User> resultObject = new ResultObject<>();
        try {
            User user = userService.getUserById(id);
            //비밀버호 안전보호
            user.passwordEncryption();
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setResult(user);
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("검색 실패");
        }
        return resultObject;
    }

    @ApiOperation("새로운 사용자 저장")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "사용자 이름", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "비밀번호", required = true, dataType = "String"),
            @ApiImplicitParam(name = "createId", value = "생성 자 id", required = true, dataType = "String")})
    @RequestMapping(value = "/insert",method = RequestMethod.PUT)
    public ResultObject insertUser(UserRequestVo user){
        ResultObject resultObject = new ResultObject<>();
        try {
            userService.insertUser(user);
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setMsg("저장 성공");
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg(e.getMessage());
        }
        return resultObject;
    }

    @ApiOperation("사용자 자료 변경")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "사용자 이름", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "사용자 id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "비밀번호", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "상태", required = false, dataType = "String"),
            @ApiImplicitParam(name = "updateId", value = "수개 자 id", required = true, dataType = "String")})
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResultObject updateUser(UserRequestVo user){
        ResultObject resultObject = new ResultObject<>();
        try {
            userService.updateUser(user);
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setMsg("변경 성공");
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("변경 실패");
        }
        return resultObject;
    }

    @ApiOperation("사용자 삭제")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "사용자 id", required = true, dataType = "String")})
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ResultObject deleteUser(UserRequestVo user){
        ResultObject resultObject = new ResultObject<>();
        try {
            userService.deleteUser(user);
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setMsg("삭제 성공");
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("삭제 실패");
        }
        return resultObject;
    }

    @ApiOperation("사용자 가진 권한 검색")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "사용자 id", required = true, dataType = "String")})
    @RequestMapping(value = "/queryResourcesByUserId",method = RequestMethod.GET)
    public ResultObject<List<Resource>> queryUserResources(UserRequestVo user){
        ResultObject<List<Resource>> resultObject = new ResultObject<>();
        try {
            List<Resource> result = userService.queryUserResources(user);
            resultObject.setResult(result);
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setMsg("검색 성공");
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("검색 실패");
        }
        return resultObject;
    }

    @ApiOperation("사용자 역할 저장")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "사용자 id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "roleId", value = "역할 id", required = true, dataType = "String")})
    @RequestMapping(value = "/insertUserRole",method = RequestMethod.PUT)
    public ResultObject insertUserRole(String userId,String roleId){
        ResultObject resultObject = new ResultObject<>();
        try {
            userRoleService.insertUserRole(userId,roleId);
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setMsg("저장 성공");
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("저장 실패");
        }
        return resultObject;
    }

}
