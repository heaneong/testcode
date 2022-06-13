package com.example.demo.service;

import com.example.demo.model.Resource;
import com.example.demo.model.Role;
import com.example.demo.vo.user.RoleRequestVo;

import java.util.List;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:40
 * \* Description:
 * \
 */
public interface IRoleService {


    List<Role> getRoleList();

    Role getRoleById(String id);

    void insertRole(RoleRequestVo roleRequestVo) throws Exception;

    void updateRole(RoleRequestVo roleRequestVo) throws Exception;

    void deleteRole(RoleRequestVo roleRequestVo);

    List<Resource> queryUserResources(RoleRequestVo roleRequestVo);

    void insertRoleResource(String roleId, List<String> resourceIds, String currentUserName);
}
