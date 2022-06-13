package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Resource;
import com.example.demo.model.UserRoleRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface ResourceMapper extends BaseMapper<Resource> {

    @Update("<script>" +
            "        UPDATE\n" +
            "            resource\n" +
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
    void batchDeleteByIds(@Param("resourceIds") List<String> resourceIds);
}
