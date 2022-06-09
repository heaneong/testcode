package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.constant.CommonConst;
import com.example.demo.mapper.ResourceMapper;
import com.example.demo.mapper.UserRoleMapper;
import com.example.demo.model.Resource;
import com.example.demo.model.UserRoleRelation;
import com.example.demo.service.IResourceService;
import com.example.demo.service.IUserRoleService;
import com.example.demo.util.CommonUtils;
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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @Override
    public List<Resource> queryAllResources() {
        return baseMapper.selectList(new QueryWrapper<Resource>().lambda().eq(Resource::getDelFlag,CommonConst.DEL_FLAG_NOT_DEL));
    }
}
