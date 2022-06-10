package com.example.demo.service;

import com.example.demo.model.Resource;
import com.example.demo.model.User;
import com.example.demo.vo.user.UserRequestVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:40
 * \* Description:
 * \
 */
public interface IUserRoleService {
    void insertUserRole(String userId, String roleId) throws Exception;

    void deleteUserRoleByUserId(String userId);
}
