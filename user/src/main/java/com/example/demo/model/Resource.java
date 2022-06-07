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
@TableName("resource")
@ToString
public class Resource implements Serializable {

    private static final long serialVersionUID = -6955605301721060770L;

    @TableId(value = "resource_id", type = IdType.ID_WORKER_STR)
    private String resourceId;

    private String resourceName;

    private Integer parentId;

    private Integer resourceLevel;

    private Integer url;

    private Integer img;

    private Integer status;

    private Integer delFlag;

    private Date createtime;

    private Date updateTime;

    private String createId;

    private String updateId;
}
