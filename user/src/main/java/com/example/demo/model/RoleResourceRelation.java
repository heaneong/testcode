package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * \* User: lihaining
 * \* Date: 2022/6/4
 * \* Time: 12:35
 * \* Description:
 * \
 */

@Data
@TableName("role_resource")
@ToString
public class RoleResourceRelation implements Serializable {

    private static final long serialVersionUID = 799399610551430436L;

    @TableId(value = "role_resource_id", type = IdType.ID_WORKER_STR)
    private String roleResourceId;

    private String roleId;

    private String resourceId;

    private Integer delFlag;

    private Date createtime;

    private Date updateTime;

    private String createId;

    private String updateId;
}
