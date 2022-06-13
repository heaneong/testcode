package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.constant.CommonConst;
import com.example.demo.mapper.ResourceMapper;
import com.example.demo.mapper.UserRoleMapper;
import com.example.demo.model.Resource;
import com.example.demo.model.Role;
import com.example.demo.model.UserRoleRelation;
import com.example.demo.service.IResourceService;
import com.example.demo.service.IRoleResourceService;
import com.example.demo.service.IUserRoleService;
import com.example.demo.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @Autowired
    private IRoleResourceService roleResourceService;

    @Override
    public List<Resource> queryAllResources() {
        return baseMapper.selectList(new QueryWrapper<Resource>().lambda().eq(Resource::getDelFlag,CommonConst.DEL_FLAG_NOT_DEL));
    }

    @Override
    public Resource getResourceById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void insertResource(Resource resource) throws Exception {
        //역할 존재 상태 체크
        List<Resource> roleNameList = baseMapper.selectList(new QueryWrapper<Resource>().lambda()
                .eq(Resource::getDelFlag, CommonConst.DEL_FLAG_NOT_DEL).eq(Resource::getResourceName,resource.getResourceName()));
        if (roleNameList == null || roleNameList.size() > 0){
            throw new Exception("자원이름 이미 존재 하고 있습니다");
        }
        List<Resource> roleCodeList = baseMapper.selectList(new QueryWrapper<Resource>().lambda()
                .eq(Resource::getDelFlag, CommonConst.DEL_FLAG_NOT_DEL).eq(Resource::getUrl,resource.getUrl()));
        if (roleCodeList == null || roleCodeList.size() > 0){
            throw new Exception("자원 url 이미 존재 하고 있습니다");
        }
        resource.setResourceId(CommonUtils.createNewId());
        baseMapper.insert(resource);
    }

    @Override
    public void updateResource(Resource resource) throws Exception {
        if (StringUtils.isEmpty(resource.getResourceId())){
            throw new Exception("자원 ID 없습니다");
        }
        resource.setUpdateTime(new Date());
        baseMapper.updateById(resource);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteResources(List<String> resourceIds) {
        //역할 삭제
        Resource resource = new Resource();
        resource.setDelFlag(CommonConst.DEL_FLAG_DEL);
        baseMapper.batchDeleteByIds(resourceIds);

        //자원 관계 삭제
        roleResourceService.deleteRoleResourceByResourceIds(resourceIds);
    }
}
