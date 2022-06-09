package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "사용자 역할관계 id")
    @TableId(value = "user_role_id", type = IdType.ID_WORKER_STR)
    private String userRoleId;

    @ApiModelProperty(value = "사용자 id")
    private String userId;

    @ApiModelProperty(value = "역할 id")
    private String roleId;

    @ApiModelProperty(value = "삭제 상택 : 0 정상 1 삭제")
    private Integer delFlag;

    @ApiModelProperty(value = "생성 시간")
    private Date createTime;

    @ApiModelProperty(value = "변경 시간")
    private Date updateTime;

    @ApiModelProperty(value = "창설자 id")
    private String createId;

    @ApiModelProperty(value = "수정자 id")
    private String updateId;
}
