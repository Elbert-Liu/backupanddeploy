package com.anshare.backupanddeploy.service.impl;

import com.anshare.backupanddeploy.entity.BackupDB;
import com.anshare.backupanddeploy.entity.User;
import com.anshare.backupanddeploy.mapper.BackupDBMapper;
import com.anshare.backupanddeploy.service.IBackupDBService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能模块-中文描述
 *
 * @author liule
 * @since 1/7/2022 10:57
 */
@Service
public class BackupDBServiceimpl extends ServiceImpl<BackupDBMapper, BackupDB> implements IBackupDBService {
    @Autowired
    BackupDBMapper backupDBMapper;

    @Override
    public List<BackupDB> findall() {
        List<BackupDB> all = backupDBMapper.findall();
        return all;
    }
    @Override
    public Page<BackupDB> findPage(Page<BackupDB> page, String dbHostIp) {
        return backupDBMapper.findPage(page, dbHostIp);
    }
}
