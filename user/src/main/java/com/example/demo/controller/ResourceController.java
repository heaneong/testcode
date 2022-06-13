package com.example.demo.controller;

import com.example.demo.constant.CommonConst;
import com.example.demo.model.Resource;
import com.example.demo.model.Role;
import com.example.demo.service.IResourceService;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserRoleService;
import com.example.demo.util.CommonUtils;
import com.example.demo.vo.ResultObject;
import com.example.demo.vo.user.RoleRequestVo;
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
@RequestMapping("/resource")
@Api(value = "자원 관리")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    @ApiOperation("전체 자원 검색")
    @RequestMapping(value = "/queryList",method = RequestMethod.GET)
    public ResultObject<List<Resource>> getResourceList(){
        ResultObject<List<Resource>> resultObject = new ResultObject<>();
        try {
            List<Resource> roleList = resourceService.queryAllResources();
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setResult(roleList);
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("검색 실패");
        }
        return resultObject;
    }

    @ApiOperation("지정 자원 검색")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "자원 id", required = true, dataType = "String")})
    @RequestMapping(value = "/queryById",method = RequestMethod.GET)
    public ResultObject<Resource> getRoleById(String id){
        ResultObject<Resource> resultObject = new ResultObject<>();
        try {
            Resource resource = resourceService.getResourceById(id);
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setResult(resource);
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("검색 실패");
        }
        return resultObject;
    }

    @ApiOperation("새로운 자원 저장")
    @ApiImplicitParams({@ApiImplicitParam(name = "resourceName", value = "자원 이름", required = true, dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "상급 자원 id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "resourceLevel", value = "자원 등금", required = true, dataType = "String"),
            @ApiImplicitParam(name = "url", value = "자원 url", required = true, dataType = "String"),
            @ApiImplicitParam(name = "img", value = "자원 아이콘", required = false, dataType = "String"),
            @ApiImplicitParam(name = "createId", value = "생성 자 id", required = true, dataType = "String")})
    @RequestMapping(value = "/insert",method = RequestMethod.PUT)
    public ResultObject insertUser(Resource resource){
        ResultObject resultObject = new ResultObject<>();
        try {
            resourceService.insertResource(resource);
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setMsg("저장 성공");
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg(e.getMessage());
        }
        return resultObject;
    }

    @ApiOperation("자원 자료 변경")
    @ApiImplicitParams({@ApiImplicitParam(name = "resource_name", value = "자원 이름", required = false, dataType = "String"),
            @ApiImplicitParam(name = "resource_id", value = "자원 id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "parent_id", value = "상급 자원 id", required = false, dataType = "String"),
            @ApiImplicitParam(name = "url", value = "자원 url", required = false, dataType = "String"),
            @ApiImplicitParam(name = "img", value = "자원 아이콘", required = false, dataType = "String"),
            @ApiImplicitParam(name = "updateId", value = "수개 자 id", required = true, dataType = "String")})
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResultObject updateResource(Resource resource){
        ResultObject resultObject = new ResultObject<>();
        try {
            resourceService.updateResource(resource);
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setMsg("변경 성공");
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("변경 실패");
        }
        return resultObject;
    }

    @ApiOperation("자원 삭제")
    @ApiImplicitParams({@ApiImplicitParam(name = "resourceIds",value = "자원 id",allowMultiple = true,  required = true, dataType="java.util.List")})
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ResultObject deleteResource(@RequestParam("resourceIds")List<String> resourceIds){
        ResultObject resultObject = new ResultObject<>();
        try {
            resourceService.deleteResources(resourceIds);
            resultObject.setCode(CommonConst.SUCCESS);
            resultObject.setMsg("삭제 성공");
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.setCode(CommonConst.FAIL);
            resultObject.setMsg("삭제 실패");
        }
        return resultObject;
    }

}
