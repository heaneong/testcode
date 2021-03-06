package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
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
@TableName("user")
@ApiModel(value="User 대상", description="사용자 대상")
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1103515721959863427L;

    @ApiModelProperty(value = "사용자 id")
    @TableId(value = "user_id", type = IdType.ID_WORKER_STR)
    private String userId;

    @ApiModelProperty(value = "사용자 이름")
    private String userName;

    @ApiModelProperty(value = "비밀번호")
    private String password;

    @ApiModelProperty(value = "상태")
    private Integer status;

    @ApiModelProperty(value = "삭제 표시")
    private Integer delFlag;

    @ApiModelProperty(value = "생성 시간")
    private Date createTime;

    @ApiModelProperty(value = "수개 시간")
    private Date updateTime;

    @ApiModelProperty(value = "생성 자 id")
    private String createId;

    @ApiModelProperty(value = "수개 자 id")
    private String updateId;

    //비밀버호 안전보호
    public void passwordEncryption(){
        this.setPassword(this.getPassword().replaceAll("\\d","*"));
    }


}
