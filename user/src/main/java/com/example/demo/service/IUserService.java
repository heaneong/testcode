package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.vo.user.UserRequestVo;

import java.util.List;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:40
 * \* Description:
 * \
 */
public interface IUserService {

    //전체 사용자 검색
    List<User> getUserList();

    User getUserById(String id);

    void insertUser(UserRequestVo user);
}
