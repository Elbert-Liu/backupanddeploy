package com.anshare.backupanddeploy.mapper;

import com.anshare.backupanddeploy.entity.BackupDB;
import com.anshare.backupanddeploy.entity.User;
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
public interface BackupDBMapper extends BaseMapper<BackupDB> {
    @Select("SELECT * from sys_backupDB")
    List<BackupDB> findall();
    @Delete("delete from sys_backupDB where id = #{id}")
    Integer deleteByid(@Param("id") Integer id);

    Page<BackupDB> findPage(Page<BackupDB> page, @Param("dbHostIp") String dbHostIp);

}
