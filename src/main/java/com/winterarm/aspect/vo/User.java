package com.winterarm.aspect.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by huangdx on 2018/3/12.
 */
@Data
@ApiModel("User")
public class User implements Serializable{

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("信息")
    private String msg;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
