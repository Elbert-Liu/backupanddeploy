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
import java.util.Date;

@Getter
@Setter
@TableName("sys_backupDB")
@ApiModel(value = "backupDB对象", description = "")
@ToString
public class BackupDB implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("数据库服务器地址")
    private String dbHostIp;

    @ApiModelProperty("数据库用户名")
    private String dbUsername;

    @ApiModelProperty("数据库密码")
    private String dbPassword;

    @ApiModelProperty("数据库端口")
    private Integer dbPort;

    @ApiModelProperty("数据库名")
    private String dbName;
    @ApiModelProperty("备份文件夹")
    private String tarDir;

    @ApiModelProperty("恢复数据库文件")
    private String restoreFile;

}
