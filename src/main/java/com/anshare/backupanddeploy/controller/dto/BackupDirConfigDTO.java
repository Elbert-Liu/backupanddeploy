package com.anshare.backupanddeploy.controller.dto;

import lombok.Data;

/**
 * 功能模块-中文描述
 *
 * @author liule
 * @since 30/6/2022 10:04
 *
 * 接受前端登录请求的参数
 *
 */
@Data
public class BackupDirConfigDTO {
    private Integer ID;
    private String HostIp;
    private String Username;
    private String Password;
    private Integer Port;
    private String srcDir;
    private String tarDir;
}
