package com.anshare.backupanddeploy.entity;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.anshare.backupanddeploy.cmd.*;
import org.osgl.storage.ISObject;
import org.osgl.storage.impl.SObject;
import org.osgl.util.E;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class Sftp {
    private SftpConfig config;
    protected Session session;

    public Sftp(Map conf) {
        this(new SftpConfig(conf));
    }

    public Sftp(SftpConfig config) {
        E.NPE(config);
        this.config = config;
        try {
            startup();
        } catch (JSchException e) {
            throw E.unexpected(e, "cannot set up jsch session");
        }
    }

    SftpConfig getConfig() {
        return config;
    }

    private Session createSession() throws JSchException {
        return config.createSession();
    }

    Session getSession() throws JSchException {
        if (!isActive()) startup();
        return session;
    }

    private static final ThreadLocal<ChannelSftp> current = new ThreadLocal<ChannelSftp>();

    ChannelSftp channel() throws JSchException {
        ChannelSftp ch = current.get();
        if (null == ch || !ch.isConnected()) {
            ch = newChannel();
            current.set(ch);
        }
        return ch;
    }

    ChannelSftp newChannel() throws JSchException {
        ChannelSftp ch = (ChannelSftp) getSession().openChannel("sftp");
        ch.connect();
        return ch;
    }

    synchronized void startup() throws JSchException {
        if (isActive()) return;
        session = createSession();
    }

    public void shutdown() {
        if (!isActive()) return;
        session.disconnect();
        session = null;
    }

    boolean isActive() {
        return null != session && session.isConnected();
    }

    public boolean exists(String path) {
        SftpATTRS attrs = new Stat(path, this).apply();
        return null != attrs;
    }

    public void put(ISObject sobj) {
        new Put(sobj, this).apply();
    }

    public void put(String path, ISObject sobj) {
        new Put(path, sobj, this).apply();
    }

    public void put(String path, String content) {
        new Put(SObject.of(path, content), this).apply();
    }

    public void put(String path, InputStream is) {
        new Put(SObject.of(path, is), this).apply();
    }

    public void put(String path, File file) {
        new Put(SObject.of(path, file), this).apply();
    }

    public void put(String path, byte[] ba) {
        new Put(SObject.of(path, ba), this).apply();
    }

    public void put(String path, ISObject sobj, Put.Mode mode) {
        new Put(path, sobj, mode, this).apply();
    }

    public void put(String path, String content, Put.Mode mode) {
        new Put(SObject.of(path, content), mode, this).apply();
    }

    public void put(String path, InputStream is, Put.Mode mode) {
        new Put(SObject.of(path, is), mode, this).apply();
    }

    public void put(String path, File file, Put.Mode mode) {
        new Put(SObject.of(path, file), mode, this).apply();
    }

    public void put(String path, byte[] ba, Put.Mode mode) {
        new Put(SObject.of(path, ba), mode, this).apply();
    }

    public boolean rm(String path) {
        return new Delete(path, this).apply();
    }

    public SObject get(String path) {
        return new Get(path, this).apply();
    }

    public boolean mkdir(String path) {
        return new MkDir(path, this).apply();
    }

    public boolean rmdir(String path) {
        return new DeleteDir(path, this).apply();
    }

    public void move(String src, String dst) {
        new Move(src, dst, this).apply();
    }

    private static final ThreadLocal<String> oneTimeCtx = new ThreadLocal<String>();

    static String oneTimeContext() {
        return oneTimeCtx.get();
    }

    static void clearOneTimeContext() {
        oneTimeCtx.remove();
    }

    public Sftp withContext(String ctx) {
        oneTimeCtx.set(SftpConfig.regulateContextPath(ctx));
        return this;
    }

}
