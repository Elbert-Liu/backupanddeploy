package com.anshare.backupanddeploy.entity;

import com.jcraft.jsch.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SSHManager
{
    private static final Logger LOGGER =
            Logger.getLogger(SSHManager.class.getName());
    private JSch jschSSHChannel;
    private String strUserName;
    private String strConnectionIP;
    private int intConnectionPort;
    private String strPassword;
    private Session sesConnection;
    private int intTimeOut;

    //建立连接：

    /**
     *
     * @param userName 用户名
     * @param password 密码
     * @param connectionIP  服务器ip地址
     * @param knownHostsFileName 已知的服务器文件夹
     */
    private void doCommonConstructorActions(String userName,
                                            String password, String connectionIP, String knownHostsFileName)
    {
        jschSSHChannel = new JSch();  //新建ssh通道

        try
        {
            jschSSHChannel.setKnownHosts(knownHostsFileName);
        }
        catch(JSchException jschX)
        {
            logError(jschX.getMessage());
        }

        strUserName = userName;
        strPassword = password;
        strConnectionIP = connectionIP;
    }

    /**
     *
     * @param userName
     * @param password
     * @param connectionIP
     * @param knownHostsFileName
     */
    public SSHManager(String userName, String password,
                      String connectionIP, String knownHostsFileName)
    {
        doCommonConstructorActions(userName, password,
                connectionIP, knownHostsFileName);
        intConnectionPort = 22;
        intTimeOut = 60000;
    }

    /**
     *
     * @param userName
     * @param password
     * @param connectionIP
     * @param knownHostsFileName
     * @param connectionPort  参数可选
     */
    public SSHManager(String userName, String password, String connectionIP,
                      String knownHostsFileName, int connectionPort)
    {
        doCommonConstructorActions(userName, password, connectionIP,
                knownHostsFileName);
        intConnectionPort = connectionPort;
        intTimeOut = 60000;
    }

    /**
     *
     * @param userName
     * @param password
     * @param connectionIP
     * @param knownHostsFileName
     * @param connectionPort  参数
     * @param timeOutMilliseconds  超时参数
     */
    public SSHManager(String userName, String password, String connectionIP,
                      String knownHostsFileName, int connectionPort, int timeOutMilliseconds)
    {
        doCommonConstructorActions(userName, password, connectionIP,
                knownHostsFileName);
        intConnectionPort = connectionPort;
        intTimeOut = timeOutMilliseconds;
    }

    public String connect()
    {
        String errorMessage = null;

        try
        {
            sesConnection = jschSSHChannel.getSession(strUserName,
                    strConnectionIP, intConnectionPort);
            sesConnection.setConfig("StrictHostKeyChecking", "no");
            sesConnection.setPassword(strPassword);
            // UNCOMMENT THIS FOR TESTING PURPOSES, BUT DO NOT USE IN PRODUCTION
            // sesConnection.setConfig("StrictHostKeyChecking", "no");
            sesConnection.connect(intTimeOut);
        }
        catch(JSchException jschX)
        {
            errorMessage = jschX.getMessage();
        }

        return errorMessage;
    }

    private String logError(String errorMessage)
    {
        if(errorMessage != null)
        {
            LOGGER.log(Level.SEVERE, "{0}:{1} - {2}",
                    new Object[]{strConnectionIP, intConnectionPort, errorMessage});
        }

        return errorMessage;
    }

    private String logWarning(String warnMessage)
    {
        if(warnMessage != null)
        {
            LOGGER.log(Level.WARNING, "{0}:{1} - {2}",
                    new Object[]{strConnectionIP, intConnectionPort, warnMessage});
        }

        return warnMessage;
    }

    public String sendCommand(String command)
    {
        StringBuilder outputBuffer = new StringBuilder();

        try
        {
            Channel channel = sesConnection.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);
            InputStream commandOutput = channel.getInputStream();
            channel.connect();
            int readByte = commandOutput.read();

            while(readByte != 0xffffffff)
            {
                outputBuffer.append((char)readByte);
                readByte = commandOutput.read();
            }

            channel.disconnect();
        }
        catch(IOException ioX)
        {
            logWarning(ioX.getMessage());
            return null;
        }
        catch(JSchException jschX)
        {
            logWarning(jschX.getMessage());
            return null;
        }

        return outputBuffer.toString();
    }

    public void close()
    {
        sesConnection.disconnect();
    }

}
