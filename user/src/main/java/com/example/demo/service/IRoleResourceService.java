package com.example.demo.service;

import com.example.demo.model.Resource;

import java.util.Date;
import java.util.List;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:40
 * \* Description:
 * \
 */
public interface IRoleResourceService {
    void deleteRoleResourceByRoleId(String roleId, Date date);

    List<Resource> queryRoleResources(String roleId);

    void insertRoleResource(String roleId, List<String> resourceIds, String currentUserName);

    void deleteRoleResourceByResourceIds(List<String> resourceIds);
}
