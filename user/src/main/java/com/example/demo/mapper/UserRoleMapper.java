package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Resource;
import com.example.demo.model.User;
import com.example.demo.model.UserRoleRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * \* User: lihaining
 * \* Date: 2022/6/7
 * \* Time: 16:39
 * \* Description:
 * \
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleRelation> {


    @Update("<script>" +
            "        UPDATE\n" +
            "            user_role\n" +
            "        SET\n" +
            "            del_flag = 1\n" +
            "        WHERE \n" +
            "            <if test=\"userId != ''\">\n" +
            "                 user_id = #{userId}\n" +
            "            </if>\n" +
            "</script>")
    void deleteUserRoleByUserId(String userId);

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
}
