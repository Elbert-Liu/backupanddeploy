package com.anshare.backupanddeploy.service.impl;

import com.anshare.backupanddeploy.entity.BackupDir;
import com.anshare.backupanddeploy.mapper.BackupDirMapper;
import com.anshare.backupanddeploy.service.IBackupDirService;
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
public class IBackupDirServiceimpl extends ServiceImpl<BackupDirMapper, BackupDir> implements IBackupDirService {
    @Autowired
    BackupDirMapper backupDirMapper;

    public List<BackupDir> findall(){
        List<BackupDir> all = backupDirMapper.findall();
        return all;
    }

    @Override
    public Page<BackupDir> findPage(Page<BackupDir> page, String hostIP) {
        return backupDirMapper.findPage(page, hostIP);
    }
}
