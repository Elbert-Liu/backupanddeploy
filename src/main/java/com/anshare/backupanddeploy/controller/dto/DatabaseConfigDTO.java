package com.anshare.backupanddeploy.controller.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

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
public class DatabaseConfigDTO {
    private Integer ID;
    private String dbHostIp;
    private String dbUsername;
    private String dbPassword;
    private Integer dbPort;
    private String dbName;
    private String tarDir;

    private String restoreFile;
}
