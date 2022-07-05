package com.anshare.backupanddeploy.controller;

import com.anshare.backupanddeploy.common.Result;
import com.anshare.backupanddeploy.controller.dto.BackupDirConfigDTO;
import com.anshare.backupanddeploy.entity.SSHManager;
import com.anshare.backupanddeploy.mapper.BackupDirMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/deploy")
public class DeployController {

    @Autowired
    BackupDirMapper backupDirMapper;

    @PostMapping("/deployDir")
    public Result deployDirctory(@RequestBody BackupDirConfigDTO backupDirConfigDTO) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String formatDate = simpleDateFormat.format(date);
//
//        SSHManager instance = new SSHManager(username, password, hostIP, "");
//        String errorMessage = instance.connect();
        /**
         * scp [option] /path/to/source/file user@server-ip:/path/to/destination/directory
         * /Users/le/Downloads
         */
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
        String command = "/usr/local/bin/sshpass -p 839119912oO " + "scp -r " + backupDirConfigDTO.getTarDir() + " " + backupDirConfigDTO.getUsername() + "@" + backupDirConfigDTO.getHostIp() + ":" + backupDirConfigDTO.getSrcDir();
        System.out.println(command);
        String result = instance.sendCommand(command);
        return Result.success(result);
    }

    @DeleteMapping("/delete/{id}")
    public Integer delete(@PathVariable Integer id) {
        return backupDirMapper.deleteById(id);
    }

    @RequestMapping(value = "/startProject", method = RequestMethod.GET)
    public void deployDirctory(@RequestParam("hostIP") String hostIP, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("projectName") String projectName) {
        SSHManager instance = new SSHManager(username, password, hostIP, "");
        String errorMessage = instance.connect();
        System.out.println(errorMessage);
        /**
         * need a start shell
         */
        String command = "123"; //run61
        String result = instance.sendCommand("sh" + command);
        System.out.println(result);
        System.out.println("over!");
    }

}
