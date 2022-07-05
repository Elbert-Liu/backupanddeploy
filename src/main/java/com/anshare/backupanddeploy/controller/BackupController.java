package com.anshare.backupanddeploy.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.anshare.backupanddeploy.common.Result;
import com.anshare.backupanddeploy.controller.dto.DatabaseConfigDTO;
import com.anshare.backupanddeploy.controller.dto.BackupDirConfigDTO;
import com.anshare.backupanddeploy.entity.BackupDB;
import com.anshare.backupanddeploy.entity.BackupDir;
import com.anshare.backupanddeploy.entity.SSHManager;
import com.anshare.backupanddeploy.mapper.BackupDBMapper;
import com.anshare.backupanddeploy.service.IBackupDBService;
import com.anshare.backupanddeploy.service.IBackupDirService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/backup")
public class BackupController {

    @Autowired
    BackupDBMapper backupDBMapper;

    @Autowired
    BackupDBMapper backupDirMapper;
    @Autowired
    IBackupDBService backupDBService;

    @Autowired
    IBackupDirService backupDirService;
    @PostMapping("/backupDatabase")
    public Result backupDatabase(@RequestBody DatabaseConfigDTO databaseConfigDTO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String formatDate = simpleDateFormat.format(date);

        //数据库备份服务器
        SSHManager instance = new SSHManager("le", "050697", "127.0.0.1", "");
        String errorMessage = instance.connect();
        if (errorMessage != null) {
            return Result.error(errorMessage, "与备份服务器建立连接失败");
        }
        System.out.println(errorMessage);
        System.out.println(instance);
        String savePath = databaseConfigDTO.getTarDir() + formatDate + "/" + databaseConfigDTO.getDbName() + ".sql";
        /*NOTE: Used to create a cmd command
         * mysqldump -uroot -p123456 meteo sdata --where=" sensorid=11 and fieldid=0" > /home/xyx/Temp.sql
         * mysqldump -t 数据库名 -uroot -p > xxx.sql
         * */
        instance.sendCommand("mkdir " + databaseConfigDTO.getTarDir() + formatDate);
        instance.sendCommand("touch " + savePath);
        databaseConfigDTO.setRestoreFile(savePath);
        //macos linux is different,change by the location of mysqldump.
        String command = "/usr/local/bin/mysqldump -u" + databaseConfigDTO.getDbUsername() + " -h" + databaseConfigDTO.getDbHostIp() + " -p" + databaseConfigDTO.getDbPassword() + " " + databaseConfigDTO.getDbName() + " > " + savePath;
        System.out.println(command);
        return Result.success(instance.sendCommand(command));
    }


    //添加新的备份服务器
    @PostMapping("/saveBackupDB")
    public Result saveDB(@RequestBody BackupDB backupDB) {
        if (backupDB.getId() == null && backupDB.getDbPassword() == null) {
            backupDB.setDbPassword("123");
        }
        return Result.success(backupDBService.saveOrUpdate(backupDB));
    }

    @PostMapping("/delDB/batch")
    public Result deleteDBBatch(@RequestBody List<Integer> ids) {
        return Result.success(backupDBService.removeByIds(ids));
    }

    @PostMapping("/delDir/batch")
    public Result deleteDirBatch(@RequestBody List<Integer> ids) {
        return Result.success(backupDirService.removeByIds(ids));
    }

    @PostMapping("/saveBackupDir")
    public Result saveDir(@RequestBody BackupDir backupDir) {
        if (backupDir.getId() == null && backupDir.getPassword() == null) {
            backupDir.setPassword("123");
        }
        return Result.success(backupDirService.saveOrUpdate(backupDir));
    }
    @DeleteMapping("/deleteDB/{id}")
    public Integer deleteDB(@PathVariable Integer id) {
        return backupDBMapper.deleteById(id);
    }

    @DeleteMapping("/deleteDir/{id}")
    public Integer deleteDir(@PathVariable Integer id) {
        return backupDirMapper.deleteById(id);
    }
    @GetMapping("/DBpage")
    public Result findDBPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String dbHostIp
    ) {
        return Result.success(backupDBService.findPage(new Page<>(pageNum, pageSize), dbHostIp));
    }

    @GetMapping("/Dirpage")
    public Result findDirPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String hostIP
    ) {
        return Result.success(backupDirService.findPage(new Page<>(pageNum, pageSize), hostIP));
    }
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<BackupDB> list = backupDBService.findall();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("id", "序号");
        writer.addHeaderAlias("dbHostIp", "数据库服务器地址");
        writer.addHeaderAlias("dbPassword", "数据库服务器密码");
        writer.addHeaderAlias("dbPort", "数据库端口");
        writer.addHeaderAlias("dbName", "数据库名");
        writer.addHeaderAlias("tarDir", "备份文件夹");
        writer.addHeaderAlias("restore", "恢复文件");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("数据库信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
//        List<User> list = reader.readAll(User.class);

        // 方式2：忽略表头的中文，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<BackupDB> backupDBS = CollUtil.newArrayList();
        for (List<Object> row : list) {
            BackupDB backupDB = new BackupDB();
            backupDB.setId((Integer) row.get(0));
            backupDB.setDbHostIp(row.get(1).toString());
            backupDB.setDbUsername(row.get(2).toString());
            backupDB.setDbPassword(row.get(3).toString());
            backupDB.setDbPort((Integer) row.get(4));
            backupDB.setDbName(row.get(1).toString());


            backupDBS.add(backupDB);
        }

        backupDBService.saveBatch(backupDBS);
        return Result.success(true);
    }

    @PostMapping("/restoreDatabase")

    public Result restoreDatabase(@RequestBody DatabaseConfigDTO databaseConfigDTO) {
        {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = new Date();
//            String formatDate = simpleDateFormat.format(date);

            //数据库备份服务器
            SSHManager instance = new SSHManager("le", "050697", "127.0.0.1", "");
            //SSHManager instance = new SSHManager("cjr", "123456", "192.168.3.126", "");
            String errorMessage = instance.connect();

            // call sendCommand for each command and the output
            //(without prompts) is returned

            /*NOTE: Creating Path Constraints for folder saving*/
            /*NOTE: Here the backup folder is created for saving inside it*/
            /*NOTE: Creating Folder if it does not exist*/
            /*NOTE: Creating Path Constraints for backup saving*/
            /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/

            /**
             * mysql -uroot -p < 路径+数据库名
             */


            /*NOTE: Used to create a cmd command
             * mysqldump -uroot -p123456 meteo sdata --where=" sensorid=11 and fieldid=0" > /home/xyx/Temp.sql
             * mysqldump -t 数据库名 -uroot -p > xxx.sql
             * */

            String executeCmd = "/usr/local/bin/mysql -u" + databaseConfigDTO.getDbUsername() + " -h" + databaseConfigDTO.getDbHostIp() + " -p" + databaseConfigDTO.getDbPassword() + " " + databaseConfigDTO.getDbName() + " < " + databaseConfigDTO.getRestoreFile();
            String result = instance.sendCommand(executeCmd);
            return Result.success(result);
        }
    }


    @PostMapping(value = "/backupDir")
    public Result backupDirctory(
            @RequestBody BackupDirConfigDTO backupDirConfigDTO
    ) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String formatDate = simpleDateFormat.format(date);

        SSHManager instance = new SSHManager("le", "050697", "127.0.0.1", "");
        //SSHManager instance = new SSHManager(username, password, hostIP, "");
        String errorMessage = instance.connect();
        if (errorMessage != null) {
            return Result.error(errorMessage, "与备份服务器建立连接失败");
        }
        /**
         * scp [option] /path/to/source/file user@server-ip:/path/to/destination/directory
         * /Users/le/Downloads
         */
        String command = "/usr/local/bin/sshpass -p " + backupDirConfigDTO.getPassword() + " scp -r " + backupDirConfigDTO.getUsername() + "@" + backupDirConfigDTO.getHostIp() + ":" + backupDirConfigDTO.getSrcDir() + " " + backupDirConfigDTO.getTarDir();
        String result = instance.sendCommand(command);
        return Result.success(result);
    }


}
