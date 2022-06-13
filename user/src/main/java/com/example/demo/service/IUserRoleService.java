package com.example.demo.service;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:40
 * \* Description:
 * \
 */
public interface IUserRoleService {
    void insertUserRole(String userId, String oldRoleId, String roleId) throws Exception;

    void deleteUserRoleByUserId(String userId);
}
