package com.newcentury99.servercp.global.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SshConnector {
    private String host;
    private String username;
    private String password;
    private Integer port;

    public Session getSshSession() throws Exception {
        Session session = new JSch().getSession(this.username, this.host, this.port);
        session.setPassword(this.password);
        session.setConfig("StrictHostKeyChecking", "no");
        return session;
    }
}
