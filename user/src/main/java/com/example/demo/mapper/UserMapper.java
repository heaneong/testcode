package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Resource;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:39
 * \* Description:
 * \
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("<script>" +
            "        SELECT\n" +
            "            resource.*\n" +
            "        FROM\n" +
            "            user\n" +
            "        LEFT JOIN \n" +
            "            user_role \n" +
            "        ON   user.user_id = user_role.user_id\n" +
            "        LEFT JOIN\n" +
            "            role_resource\n" +
            "        ON   role_resource.role_id = user_role.role_id\n" +
            "        LEFT JOIN\n" +
            "            resource\n" +
            "        ON   resource.resource_id = role_resource.resource_id\n" +
            "        <where>\n" +
            "            <if test=\"userId != ''\">\n" +
            "                 user.user_id = #{userId}\n" +
            "            </if>\n" +
            "        </where>\n" +
            "</script>")
    List<Resource> queryUserResources(@Param("userId") String userId);
}
