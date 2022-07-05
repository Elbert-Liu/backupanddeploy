package com.anshare.backupanddeploy.cmd;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.anshare.backupanddeploy.entity.Sftp;
import com.anshare.backupanddeploy.entity.SftpCmd;
import com.anshare.backupanddeploy.result.SftpError;
import com.anshare.backupanddeploy.result.SftpResult;
import org.osgl.storage.ISObject;
import org.osgl.storage.impl.SObject;
import org.osgl.util.IO;

import java.io.InputStream;

public class Put extends SftpCmd<Void> {
    public static enum Mode {
        OVERWRITE(ChannelSftp.OVERWRITE),
        APPEND(ChannelSftp.APPEND),
        RESUME(ChannelSftp.RESUME)
        ;
        private final int code;
        private Mode(int code) {
            this.code = code;
        }
        public int code() {
            return code;
        }
    }

    private ISObject content;
    private Mode mode;

    public Put(ISObject content, Sftp sftp) {
        this(content.getKey(), content, sftp);
    }

    public Put(String path, ISObject content, Sftp sftp) {
        this(path, content, Mode.OVERWRITE, sftp);
    }

    public Put(ISObject content, Mode mode, Sftp sftp) {
        this(content.getKey(), content, mode, sftp);
    }

    public Put(String path, ISObject content, Mode mode, Sftp sftp) {
        super(path, sftp, false);
        if (null == content) content = SObject.of("");
        this.content = content;
        this.mode = mode;
    }

    @Override
    protected void execute() throws SftpResult, JSchException {
        InputStream is = null;
        ChannelSftp ch = channel();
        try {
            is = content.asInputStream();
            ch.put(is, path, mode.code());
        } catch (SftpException e) {
            throw new SftpError(e, "Error putting file[%s]", path);
        } finally {
            IO.close(is);
            release(ch);
        }
    }

}
