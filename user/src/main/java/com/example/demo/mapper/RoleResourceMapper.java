package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Resource;
import com.example.demo.model.RoleResourceRelation;
import com.example.demo.model.UserRoleRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:39
 * \* Description:
 * \
 */
@Mapper
public interface RoleResourceMapper extends BaseMapper<RoleResourceRelation> {

    @Update("<script>" +
            "        UPDATE\n" +
            "            user_role\n" +
            "        SET\n" +
            "            del_flag = 1\n" +
            "        WHERE \n" +
            "            <if test=\"userId != ''\">\n" +
            "                 user_id = #{userId}\n" +
            "            </if>\n" +
            "            <if test=\"oldRoleId != ''\">\n" +
            "                 oldRoleId = #{oldRoleId}\n" +
            "            </if>\n" +
            "</script>")
    void deleteByUserRole(@Param("userId") String userId, @Param("oldRoleId") String oldRoleId);

    @Update("<script>" +
            "        UPDATE\n" +
            "            role_resource\n" +
            "        SET\n" +
            "            del_flag = 1 ,\n" +
            "            update_time = #{updateTime} \n" +
            "        WHERE \n" +
            "            <if test=\"roleId != ''\">\n" +
            "                 role_id = #{roleId}\n" +
            "            </if>\n" +
            "</script>")
    void deleteRoleResourceByRoleId(@Param("roleId") String roleId, @Param("updateTime") Date date);

    @Select("<script>" +
            "        SELECT\n" +
            "            resource.*\n" +
            "        FROM\n" +
            "            role_resource\n" +
            "        LEFT JOIN\n" +
            "            resource\n" +
            "        ON   resource.resource_id = role_resource.resource_id\n" +
            "        <where>\n" +
            "            <if test=\"roleId != ''\">\n" +
            "                 role_id = #{roleId}\n" +
            "            </if>\n" +
            "        </where>\n" +
            "</script>")
    List<Resource> queryRoleResources(@Param("roleId") String roleId);

    @Update("<script>" +
            "        UPDATE\n" +
            "            role_resource\n" +
            "        SET\n" +
            "            del_flag = 1\n" +
            "        WHERE \n" +
            "            <if test=\"resourceIds != null\">\n" +
            "                resource_id in \n" +
            "                   <foreach collection=\"resourceIds\" item=\"item\" open=\"(\" close=\")\" separator=\",\">\n" +
            "                       #{item}\n" +
            "                   </foreach>\n" +
            "            </if>\n" +
            "</script>")
    void deleteRoleResourceByResourceIds(@Param("resourceIds") List<String> resourceIds);
}
