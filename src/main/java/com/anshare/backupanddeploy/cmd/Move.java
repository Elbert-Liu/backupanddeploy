package com.anshare.backupanddeploy.cmd;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.anshare.backupanddeploy.entity.Sftp;
import com.anshare.backupanddeploy.entity.SftpCmd;
import com.anshare.backupanddeploy.result.SftpError;
import com.anshare.backupanddeploy.result.SftpResult;
import org.osgl.util.E;
import org.osgl.util.S;

public class Move extends SftpCmd<Void> {

    private String source;

    public Move(String src, String dest,  Sftp sftp) {
        super(dest, sftp);
        E.illegalArgumentIf(S.blank(src));
        source = ensurePath(src);
    }

    private String destination() {
        return path;
    }

    private String source() {
        return source;
    }

    @Override
    protected void execute() throws SftpResult, JSchException {
        ChannelSftp ch = channel();
        try {
            ch.rename(source(), destination());
        } catch (SftpException e) {
            throw new SftpError(e, "Error moving file[%s] to [%s]", source, path);
        } finally {
            release(ch);
        }
    }

}
