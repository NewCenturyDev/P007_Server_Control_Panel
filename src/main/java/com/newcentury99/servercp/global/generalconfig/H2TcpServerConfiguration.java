package com.newcentury99.servercp.global.generalconfig;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class H2TcpServerConfiguration {

    @Bean
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer().start();
    }
}
