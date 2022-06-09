package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.constant.CommonConst;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Resource;
import com.example.demo.model.User;
import com.example.demo.service.IUserService;
import com.example.demo.util.CommonUtils;
import com.example.demo.vo.user.UserRequestVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        return baseMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUserId,id)
                .eq(User::getDelFlag,CommonConst.DEL_FLAG_NOT_DEL));
    }

    @Override
    public void insertUser(UserRequestVo user) throws Exception {
        //사용자 회원이름 확인
        List<User> users = baseMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getUserName, user.getUserName())
                .eq(User::getDelFlag, CommonConst.DEL_FLAG_NOT_DEL));
        if (users == null || users.size() > 0){
            throw new Exception("회원이름 이미 존재 하고 있습니다");
        }

        try {
            User saveUser = new User();
            BeanUtils.copyProperties(user,saveUser);
            //사용자 id 생성
            saveUser.setUserId(CommonUtils.createNewId());
            baseMapper.insert(saveUser);
        } catch (BeansException e) {
            throw new Exception("저장 실패");
        }
    }

    @Override
    public void updateUser(UserRequestVo user) {
        User saveUser = new User();
        BeanUtils.copyProperties(user,saveUser);
        saveUser.setUpdateTime(new Date());
        baseMapper.updateById(saveUser);
    }

    @Override
    public void deleteUser(UserRequestVo user) {
        User saveUser = new User();
        BeanUtils.copyProperties(user,saveUser);
        saveUser.setDelFlag(CommonConst.DEL_FLAG_DEL);
        baseMapper.updateById(saveUser);
    }

    @Override
    public List<Resource> queryUserResources(UserRequestVo user) {
        return baseMapper.queryUserResources(user.getUserId());
    }

    @Override
    public User getUserByUserName(String userName) {
        return baseMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUserName,userName)
                .eq(User::getDelFlag,CommonConst.DEL_FLAG_NOT_DEL));
    }

}
