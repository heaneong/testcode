package com.example.demo.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * \* User: lihaining
 * \* Date: 2022/6/4
 * \* Time: 12:35
 * \* Description:
 * \
 */

@ApiModel(value="RoleRequestVo 대상", description="역할 대상")
@Getter
@Setter
@ToString
public class RoleRequestVo implements Serializable {

    private static final long serialVersionUID = 1103515721959863427L;

    @ApiModelProperty(value = "역할 id")
    private String roleId;

    @ApiModelProperty(value = "역할 코드")
    private String code;

    @ApiModelProperty(value = "역할 이름")
    private String roleName;

    @ApiModelProperty(value = "역할 설명")
    private String desc;

    @ApiModelProperty(value = "역할 상태")
    private Integer status;

    @ApiModelProperty(value = "생성 자 id")
    private String createId;

    @ApiModelProperty(value = "수개 자 id")
    private String updateId;

}
