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
@TableName("resource")
@ToString
public class Resource implements Serializable {

    private static final long serialVersionUID = -6955605301721060770L;

    @ApiModelProperty(value = "자원 id")
    @TableId(value = "resource_id", type = IdType.ID_WORKER_STR)
    private String resourceId;

    @ApiModelProperty(value = "자원 이름")
    private String resourceName;

    @ApiModelProperty(value = "상급 자원 id")
    private Integer parentId;

    @ApiModelProperty(value = "자원 등금")
    private Integer resourceLevel;

    @ApiModelProperty(value = "자원 url")
    private String url;

    @ApiModelProperty(value = "자원 아이콘")
    private String img;

    @ApiModelProperty(value = "자원 상태")
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
