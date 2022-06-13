package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.constant.CommonConst;
import com.example.demo.mapper.RoleResourceMapper;
import com.example.demo.model.Resource;
import com.example.demo.model.RoleResourceRelation;
import com.example.demo.service.IRoleResourceService;
import com.example.demo.util.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:44
 * \* Description:
 * \
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResourceRelation> implements IRoleResourceService {

    @Override
    public void deleteRoleResourceByRoleId(String roleId, Date date) {
        baseMapper.deleteRoleResourceByRoleId(roleId,date);
    }

    @Override
    public List<Resource> queryRoleResources(String roleId) {
        return baseMapper.queryRoleResources(roleId);
    }

    @Override
    public void insertRoleResource(String roleId, List<String> resourceIds, String currentUserName) {
        RoleResourceRelation resourceRelation = new RoleResourceRelation();
        Date time = new Date();
        //원래 관계랑 비교하고 관계에 없는 경우 관계 삭제
        List<RoleResourceRelation> roleResourceRelations = baseMapper.selectList(new QueryWrapper<RoleResourceRelation>().lambda()
                .eq(RoleResourceRelation::getRoleId, roleId)
                .eq(RoleResourceRelation::getDelFlag, CommonConst.DEL_FLAG_NOT_DEL));
        List<RoleResourceRelation> delList = roleResourceRelations.stream()
                .filter(roleResourceRelation -> !resourceIds.contains(resourceRelation.getResourceId()))
                .collect(Collectors.toList());
        for (RoleResourceRelation roleResourceRelation : delList) {
            roleResourceRelation.setDelFlag(CommonConst.DEL_FLAG_DEL);
            roleResourceRelation.setUpdateTime(time);
            baseMapper.updateById(roleResourceRelation);
        }
        for (String resourceId : resourceIds) {
            //새로운 자원관계 추가
            List<RoleResourceRelation> checkRelations = baseMapper.selectList(new QueryWrapper<RoleResourceRelation>().lambda()
                    .eq(RoleResourceRelation::getDelFlag, CommonConst.DEL_FLAG_NOT_DEL)
                    .eq(RoleResourceRelation::getRoleId, roleId).eq(RoleResourceRelation::getResourceId, resourceId));
            if (checkRelations != null && checkRelations.size() > 0){
                continue;
            }

            resourceRelation.setRoleResourceId(CommonUtils.createNewId());
            resourceRelation.setRoleId(roleId);
            resourceRelation.setResourceId(resourceId);
            resourceRelation.setCreateId(currentUserName);
            baseMapper.insert(resourceRelation);
        }
    }

    @Override
    public void deleteRoleResourceByResourceIds(List<String> resourceIds) {
        baseMapper.deleteRoleResourceByResourceIds(resourceIds);
    }
}
