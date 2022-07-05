package com.anshare.backupanddeploy.service;

import com.anshare.backupanddeploy.entity.BackupDB;
import com.anshare.backupanddeploy.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能模块-中文描述
 *
 * @author liule
 * @since 1/7/2022 10:51
 */

public interface IBackupDBService extends IService<BackupDB> {
    List<BackupDB> findall();
    Page<BackupDB> findPage(Page<BackupDB> objectPage, String dbHostIp);
}
