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
@TableName("user_role")
@ToString
public class UserRoleRelation implements Serializable {

    private static final long serialVersionUID = -1777976400075147663L;

    @TableId(value = "user_role_id", type = IdType.ID_WORKER_STR)
    private String userRoleId;

    private String userId;

    private String roleId;

    private Integer delFlag;

    private Date createtime;

    private Date updateTime;

    private String createId;

    private String updateId;
}
