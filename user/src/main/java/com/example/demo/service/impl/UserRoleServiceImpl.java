package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.constant.CommonConst;
import com.example.demo.mapper.UserRoleMapper;
import com.example.demo.model.UserRoleRelation;
import com.example.demo.service.IUserRoleService;
import com.example.demo.util.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:44
 * \* Description:
 * \
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleRelation> implements IUserRoleService {

    @Override
    public void insertUserRole(String userId, String oldRoleId, String roleId) throws Exception {
        //사용자 역할 관계 체크
        List<UserRoleRelation> userRoleRelations = baseMapper.selectList(new QueryWrapper<UserRoleRelation>().lambda().eq(UserRoleRelation::getRoleId, roleId)
                .eq(UserRoleRelation::getUserId, userId).eq(UserRoleRelation::getDelFlag,CommonConst.DEL_FLAG_NOT_DEL));
        if (userRoleRelations == null || userRoleRelations.size() > 0){
            throw new Exception("관계 이미 존재 하고 있습니다");
            //return;
        }

        //원래 역할 있으면 삭제 새로운 역할 추가
        if (!StringUtils.isEmpty(oldRoleId)){
            baseMapper.deleteByUserRole(userId,oldRoleId);
        }

        UserRoleRelation userRoleRelation = new UserRoleRelation();
        userRoleRelation.setUserRoleId(CommonUtils.createNewId());
        userRoleRelation.setUserId(userId);
        userRoleRelation.setRoleId(roleId);
        baseMapper.insert(userRoleRelation);
    }

    @Override
    public void deleteUserRoleByUserId(String userId) {
        baseMapper.deleteUserRoleByUserId(userId);
    }
}
