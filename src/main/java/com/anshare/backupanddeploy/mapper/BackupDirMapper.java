package com.anshare.backupanddeploy.mapper;

import com.anshare.backupanddeploy.entity.BackupDir;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 功能模块-中文描述
 *
 * @author liule
 * @since 1/7/2022 10:32
 */
@Mapper
public interface BackupDirMapper extends BaseMapper<BackupDir> {
    @Select("SELECT * from sys_backupDir")
    List<BackupDir> findall();

    @Delete("delete from sys_backupDir where id = #{id}")
    Integer deleteByid(@Param("id") Integer id);

    Page<BackupDir> findPage(Page<BackupDir> page, @Param("hostIP") String hostIP);
}
