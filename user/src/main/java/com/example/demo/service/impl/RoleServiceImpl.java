package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.constant.CommonConst;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.model.Resource;
import com.example.demo.model.Role;
import com.example.demo.service.IRoleResourceService;
import com.example.demo.service.IRoleService;
import com.example.demo.util.CommonUtils;
import com.example.demo.vo.user.RoleRequestVo;
import org.springframework.beans.BeanUtils;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IRoleResourceService roleResourceService;


    @Override
    public List<Role> getRoleList() {
        return baseMapper.selectList(new QueryWrapper<Role>().lambda().eq(Role::getDelFlag,CommonConst.DEL_FLAG_NOT_DEL)
                .eq(Role::getStatus,CommonConst.USER_STATUS_IN_USE));
    }

    @Override
    public Role getRoleById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void insertRole(RoleRequestVo roleRequestVo) throws Exception {
        //역할 존재 상태 체크
        List<Role> roleNameList = baseMapper.selectList(new QueryWrapper<Role>().lambda()
                .eq(Role::getDelFlag, CommonConst.DEL_FLAG_NOT_DEL).eq(Role::getRoleName,roleRequestVo.getRoleName()));
        if (roleNameList == null || roleNameList.size() > 0){
            throw new Exception("역할이름 이미 존재 하고 있습니다");
        }
        List<Role> roleCodeList = baseMapper.selectList(new QueryWrapper<Role>().lambda()
                .eq(Role::getDelFlag, CommonConst.DEL_FLAG_NOT_DEL).eq(Role::getCode,roleRequestVo.getCode()));
        if (roleCodeList == null || roleCodeList.size() > 0){
            throw new Exception("역할코드 이미 존재 하고 있습니다");
        }

        try {
            Role role = new Role();
            BeanUtils.copyProperties(roleRequestVo,role);
            role.setRoleId(CommonUtils.createNewId());
            baseMapper.insert(role);
        } catch (Exception e){
            throw new Exception("저장 실패");
        }
    }

    @Override
    public void updateRole(RoleRequestVo roleRequestVo) throws Exception {
        Role role = new Role();
        if (StringUtils.isEmpty(roleRequestVo.getRoleId())){
            throw new Exception("역할 ID 없습니다");
        }
        BeanUtils.copyProperties(roleRequestVo,role);
        role.setUpdateTime(new Date());
        baseMapper.updateById(role);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRole(RoleRequestVo roleRequestVo) {
        //역할 삭제
        Role role = new Role();
        BeanUtils.copyProperties(roleRequestVo,role);
        role.setDelFlag(CommonConst.DEL_FLAG_DEL);
        role.setUpdateTime(new Date());
        baseMapper.updateById(role);

        //자원 관계 삭제
        roleResourceService.deleteRoleResourceByRoleId(role.getRoleId(),new Date());
    }

    @Override
    public List<Resource> queryUserResources(RoleRequestVo roleRequestVo) {
        return roleResourceService.queryRoleResources(roleRequestVo.getRoleId());
    }

    @Override
    public void insertRoleResource(String roleId, List<String> resourceIds, String currentUserName) {
        roleResourceService.insertRoleResource(roleId,resourceIds,currentUserName);
    }
}
