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
@TableName("user")
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1103515721959863427L;

    @TableId(value = "user_id", type = IdType.ID_WORKER_STR)
    private String userId;

    private String userName;

    private String password;

    private Integer status;

    private Integer delFlag;

    private Date createTime;

    private Date updateTime;

    private String createId;

    private String updateId;

    //비밀버호 안전보호
    public void passwordEncryption(){
        this.setPassword(this.getPassword().replaceAll("\\d","*"));
    }


}
