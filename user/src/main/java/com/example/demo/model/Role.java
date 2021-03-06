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
@TableName("role")
@ToString
public class Role implements Serializable {

    private static final long serialVersionUID = -8683011096014316711L;

    @ApiModelProperty(value = "역할 id")
    @TableId(value = "role_id", type = IdType.ID_WORKER_STR)
    private String roleId;

    @ApiModelProperty(value = "역할 코드")
    private String code;

    @ApiModelProperty(value = "역할 이름")
    private String roleName;

    @ApiModelProperty(value = "역할 설명")
    private String des;

    @ApiModelProperty(value = "역할 상태")
    private Integer status;

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
