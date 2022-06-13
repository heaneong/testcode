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
@TableName("role_resource")
@ToString
public class RoleResourceRelation implements Serializable {

    private static final long serialVersionUID = 799399610551430436L;

    @ApiModelProperty(value = "역할 자원관계 id")
    private String roleResourceId;

    @ApiModelProperty(value = "역할 id")
    private String roleId;

    @ApiModelProperty(value = "자원 id")
    private String resourceId;

    @ApiModelProperty(value = "삭제 표시")
    private Integer delFlag;

    @ApiModelProperty(value = "생성 시간")
    private Date createTime;

    @ApiModelProperty(value = "변경 시간")
    private Date updateTime;

    @ApiModelProperty(value = "생성 자 id")
    private String createId;

    @ApiModelProperty(value = "수개 자 id")
    private String updateId;
}
