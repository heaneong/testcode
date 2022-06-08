package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.constant.CommonConst;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.IUserService;
import com.example.demo.util.CommonUtils;
import com.example.demo.vo.user.UserRequestVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:44
 * \* Description:
 * \
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public List<User> getUserList() {
        return baseMapper.selectList(new QueryWrapper<User>().lambda()
                .eq(User::getDelFlag, CommonConst.USER_STATUS_IN_USE).eq(User::getStatus, CommonConst.DEL_FLAG_NOT_DEL));
    }

    @Override
    public User getUserById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void insertUser(UserRequestVo user) {
        //사용자 id 생성
        User saveUser = new User();
        BeanUtils.copyProperties(user,saveUser);
        saveUser.setUserId(CommonUtils.createNewId());
        baseMapper.insert(saveUser);
    }
}
