package com.example.demo.controller;

import com.example.demo.constant.CommonConst;
import com.example.demo.model.Resource;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserRoleService;
import com.example.demo.service.IUserService;
import com.example.demo.util.CommonUtils;
import com.example.demo.vo.ResultObject;
import com.example.demo.vo.user.RoleRequestVo;
import com.example.demo.vo.user.UserRequestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:38
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/role")
@Api(value = "역할 관리")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRoleService userRoleService;

    @ApiOperation("전체 역할 검색")
    @RequestMapping(value = "/queryList",method = RequestMethod.GET)
    public ResultObject<List<Role>> getUserList(){
        ResultObject<List<Role>> resultObject = new ResultObject<>();
        try {
            List<Role> roleList = roleService.getRoleList();
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setResult(roleList);
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("검색 실패");
        }
        return resultObject;
    }

    @ApiOperation("지정 역할 검색")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "역할 id", required = true, dataType = "String")})
    @RequestMapping(value = "/queryById",method = RequestMethod.GET)
    public ResultObject<Role> getRoleById(String id){
        ResultObject<Role> resultObject = new ResultObject<>();
        try {
            Role role = roleService.getRoleById(id);
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setResult(role);
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("검색 실패");
        }
        return resultObject;
    }

    @ApiOperation("새로운 역할 저장")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "역할 코드", required = true, dataType = "String"),
            @ApiImplicitParam(name = "roleName", value = "역할 이름", required = true, dataType = "String"),
            @ApiImplicitParam(name = "des", value = "역할 설명", required = true, dataType = "String"),
            @ApiImplicitParam(name = "createId", value = "생성 자 id", required = true, dataType = "String")})
    @RequestMapping(value = "/insert",method = RequestMethod.PUT)
    public ResultObject insertUser(RoleRequestVo roleRequestVo){
        ResultObject resultObject = new ResultObject<>();
        try {
            roleService.insertRole(roleRequestVo);
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setMsg("저장 성공");
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg(e.getMessage());
        }
        return resultObject;
    }

    @ApiOperation("역할 자료 변경")
    @ApiImplicitParams({@ApiImplicitParam(name = "roleName", value = "역할 이름", required = false, dataType = "String"),
            @ApiImplicitParam(name = "roleId", value = "역할 id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "des", value = "역할 설명", required = false, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "상태", required = false, dataType = "String"),
            @ApiImplicitParam(name = "updateId", value = "수개 자 id", required = true, dataType = "String")})
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResultObject updateRole(RoleRequestVo roleRequestVo){
        ResultObject resultObject = new ResultObject<>();
        try {
            roleService.updateRole(roleRequestVo);
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setMsg("변경 성공");
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("변경 실패");
        }
        return resultObject;
    }

    @ApiOperation("역할 삭제")
    @ApiImplicitParams({@ApiImplicitParam(name = "roleId", value = "역할 id", required = true, dataType = "String")})
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ResultObject deleteRole(RoleRequestVo roleRequestVo){
        ResultObject resultObject = new ResultObject<>();
        try {
            roleService.deleteRole(roleRequestVo);
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setMsg("삭제 성공");
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("삭제 실패");
        }
        return resultObject;
    }

    @ApiOperation("역할 관리 권한 검색")
    @ApiImplicitParams({@ApiImplicitParam(name = "roleId", value = "역할 id", required = true, dataType = "String")})
    @RequestMapping(value = "/queryResourcesByRoleId",method = RequestMethod.GET)
    public ResultObject<List<Resource>> queryRoleResources(RoleRequestVo roleRequestVo){
        ResultObject<List<Resource>> resultObject = new ResultObject<>();
        try {
            List<Resource> result = roleService.queryUserResources(roleRequestVo);
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
    @ApiImplicitParams({@ApiImplicitParam(name = "roleId", value = "역할 id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "resourceIds", value = "관련 자원 전체", allowMultiple = true, required = true, dataType="java.util.List")})
    @RequestMapping(value = "/insertUserRole",method = RequestMethod.PUT)
    public ResultObject insertUserRole(String roleId, @RequestParam("resourceIds") List<String> resourceIds, HttpServletRequest request){
        ResultObject resultObject = new ResultObject<>();
        try {
            String currentUserName = CommonUtils.getCurrentUserName(request);
            if (StringUtils.isEmpty(currentUserName)){
                resultObject.setCode(CommonConst.SUCCESS);
                resultObject.setMsg("저장 실패");
                return resultObject;
            }
            roleService.insertRoleResource(roleId,resourceIds,currentUserName);
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
