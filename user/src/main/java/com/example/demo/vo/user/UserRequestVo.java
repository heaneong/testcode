package com.example.demo.vo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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

@ApiModel(value="UserRequestVo 대상", description="사용자 대상")
@Getter
@Setter
@ToString
public class UserRequestVo implements Serializable {

    private static final long serialVersionUID = 1103515721959863427L;

    @ApiModelProperty(value = "사용자 id")
    private String userId;

    @ApiModelProperty(value = "사용자 이름")
    private String userName;

    @ApiModelProperty(value = "비밀번호")
    private String password;

    @ApiModelProperty(value = "생성 자 id")
    private String createId;

    @ApiModelProperty(value = "수개 자 id")
    private String updateId;

    @ApiModelProperty(value = "상태")
    private Integer status;

}
