package com.newcentury99.servercp.global.ssh;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Properties;

@AllArgsConstructor
@Service
public class SshService {
    final int CONN_TIMEOUT_10SEC = 10000;
    SshConnector sshConnector;
    Properties sshProperties;

    public String executeSshRemoteCommand(String sshCommandScript) throws Exception {
        Session session = null;
        ChannelExec channelExec = null;
        ByteArrayOutputStream execOutputStream;
        try {
            session = sshConnector.getSshSession();
            System.out.println("==> Open SSH Connection to:" + session.getHost());
            session.connect(CONN_TIMEOUT_10SEC);
            channelExec = (ChannelExec)session.openChannel("exec");
            execOutputStream = new ByteArrayOutputStream();

            channelExec.setOutputStream(execOutputStream);
            channelExec.setCommand(sshCommandScript);
            System.out.println("==> Executing: " + sshCommandScript);
            channelExec.connect();
            synchronized(this) {
                while(!channelExec.isClosed()){
                    this.wait(10);
                }
                this.notify();
            }
        } finally {
            if (channelExec != null) {
                channelExec.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
        return execOutputStream.toString();
    }

    public void uploadFileBySftp(InputStream fileUploadStream, String uploadPath, String filename) throws Exception {
        Session session = null;
        ChannelSftp channelSftp = null;

        try {
            session = sshConnector.getSshSession();
            System.out.println("==> Open SFTP Connection to:" + session.getHost());
            session.connect(CONN_TIMEOUT_10SEC);
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.cd(uploadPath);
            channelSftp.put(fileUploadStream, filename);
            System.out.println("==> Uploaded : " + filename + " at " + session.getHost() + " | " + uploadPath);
        } finally {
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }
}
