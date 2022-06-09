package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Resource;
import com.example.demo.model.UserRoleRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:39
 * \* Description:
 * \
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

}
