package com.anshare.backupanddeploy.cmd;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.osgl.storage.impl.SObject;

import com.anshare.backupanddeploy.entity.Sftp;
import com.anshare.backupanddeploy.entity.SftpCmd;
import com.anshare.backupanddeploy.result.SftpError;
import com.anshare.backupanddeploy.result.SftpResult;

import java.io.ByteArrayOutputStream;

public class Get extends SftpCmd<SObject> {

    public Get(String path, Sftp sftp) {
        super(path, sftp, false);
    }

    @Override
    protected void execute() throws SftpResult, JSchException {
        ChannelSftp ch = channel();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ch.get(path, baos);
            throw new SftpResult(SObject.of(path, baos.toByteArray()));
        } catch (SftpException e) {
            throw new SftpError(e, "Error getting file[%s]", path);
        } finally {
            release(ch);
        }
    }

}
