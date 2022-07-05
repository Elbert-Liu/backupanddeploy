package com.anshare.BackupAndDeploy.Utils;

import com.anshare.backupanddeploy.entity.SSHManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * 功能模块-中文描述
 *
 * @author haha
 * @since 18/6/2022 17:18
 */
public class TestSSHManager {
    /**
     * Test of sendCommand method, of class SSHManager.
     */
    @Test
    public void testSendCommand()
    {
        System.out.println("sendCommand");

        /**
         * YOU MUST CHANGE THE FOLLOWING
         * FILE_NAME: A FILE IN THE DIRECTORY
         * USER: LOGIN USER NAME
         * PASSWORD: PASSWORD FOR THAT USER
         * HOST: IP ADDRESS OF THE SSH SERVER
         **/
        String command ="ls";
        String userName ="cjr";
        String password ="123456";
        String connectionIP ="192.168.3.126";
        SSHManager instance = new SSHManager(userName, password, connectionIP,"");
        String errorMessage = instance.connect();

        if(errorMessage != null)
        {
            System.out.println(errorMessage);
            fail();
        }

        // call sendCommand for each command and the output
        //(without prompts) is returned
        String result = instance.sendCommand(command);
        System.out.println(result);
        // close only after all commands are sent
        instance.close();
    }
}
