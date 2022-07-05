package com.anshare.backupanddeploy.service;

import com.anshare.backupanddeploy.entity.BackupDir;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 功能模块-中文描述
 *
 * @author liule
 * @since 1/7/2022 10:51
 */

public interface IBackupDirService extends IService<BackupDir> {
    List<BackupDir> findall();


    Page<BackupDir> findPage(Page<BackupDir> objectPage, String hostIP);
}
