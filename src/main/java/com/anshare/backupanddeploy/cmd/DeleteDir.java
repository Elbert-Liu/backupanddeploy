package com.anshare.backupanddeploy.cmd;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.anshare.backupanddeploy.entity.Sftp;
import com.anshare.backupanddeploy.entity.SftpCmd;
import com.anshare.backupanddeploy.result.SftpError;
import com.anshare.backupanddeploy.result.SftpResult;

public class DeleteDir extends SftpCmd<Boolean> {

    public DeleteDir(String path, Sftp sftp) {
        super(path, sftp);
    }

    @Override
    protected void execute() throws SftpResult, JSchException {
        ChannelSftp ch = channel();
        try {
            ch.rmdir(path);
            throw new SftpResult(true);
        } catch (SftpException e) {
            if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
                throw new SftpResult(false);
            }
            throw new SftpError(e, "Error putting file[%s]", path);
        } finally {
            release(ch);
        }
    }

}
