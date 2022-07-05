package com.anshare.backupanddeploy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@TableName("sys_backupDir")
@ApiModel(value = "backupDir对象", description = "")
@ToString
public class BackupDir implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("服务器地址")
    private String HostIp;

    @ApiModelProperty("数据库用户名")
    private String Username;

    @ApiModelProperty("数据库密码")
    private String Password;

    @ApiModelProperty("数据库端口")
    private String Port;

    @ApiModelProperty("源文件夹")
    private String srcDir;

    @ApiModelProperty("目标文件夹")
    private String tarDir;


}
