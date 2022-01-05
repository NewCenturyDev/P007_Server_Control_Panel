package com.newcentury99.servercp.global.ssh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:secret.properties")
public class SshConfig {
    @Value("${ssh.secret.host}")
    private String host;
    @Value("${ssh.secret.username}")
    private String username;
    @Value("${ssh.secret.password}")
    private String password;
    @Value("${ssh.secret.port}")
    private Integer port;

    @Bean
    public SshConnector sshConnector() {
        return new SshConnector(this.host, this.username, this.password, this.port);
    }
}
