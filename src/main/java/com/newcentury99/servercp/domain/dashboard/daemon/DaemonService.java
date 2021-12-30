package com.newcentury99.servercp.domain.dashboard.daemon;

import com.jcraft.jsch.*;
import com.newcentury99.servercp.domain.dashboard.daemon.dao.Daemon;
import com.newcentury99.servercp.domain.dashboard.daemon.dao.DaemonRepository;
import com.newcentury99.servercp.domain.dashboard.daemon.dto.CreateDaemonReqDto;
import com.newcentury99.servercp.domain.dashboard.daemon.dto.DeleteDaemonReqDto;
import com.newcentury99.servercp.domain.dashboard.daemon.dto.UpdateDaemonReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DaemonService {
    private final DaemonRepository daemonRepository;

    // Create
    @Transactional
    public void createDaemon(CreateDaemonReqDto reqDto) {
        Daemon newDaemon = Daemon.builder()
                .name(reqDto.getName())
                .version(reqDto.getVersion())
                .technology(reqDto.getTechnology())
                .imageName(reqDto.getImageName())
                .containerName(reqDto.getContainerName())
                .port(reqDto.getPort())
                .projectPath(reqDto.getProjectPath())
                .binaryPath(reqDto.getBinaryPath())
                .dockerfile(reqDto.getDockerfile())
                .composefile(reqDto.getComposefile())
                .description(reqDto.getDescription())
                .portfolioUrl(reqDto.getPortfolioUrl())
                .build();

        daemonRepository.save(newDaemon);
    }

    // Read
    public List<Daemon> fetchDaemonList() {
        return daemonRepository.findAllByOrderByIdAsc();
    }
    public Daemon fetchDaemonById(Long daemonId) throws Exception {
        return daemonRepository.findById(daemonId)
                .orElseThrow(() -> new Exception("temp: no id"));
    }
    public String fetchDaemonLogById(Long daemonId) throws Exception {
        Daemon targetDaemon = this.fetchDaemonById(daemonId);
        String sshQueryScript = String.format("docker logs %s", targetDaemon.getContainerName());
        String host = "220.85.251.6";
        int port = 22;
        String username = "";
        String password = "";

        System.out.println("==> Connecting to" + host);
        Session session = null;
        ChannelExec channelExec = null;
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        StringBuilder outputBuffer = new StringBuilder();
        StringBuilder errorBuffer = new StringBuilder();
        String dockerLog = "표시할 로그가 없습니다.";

        try {
            // 채널 생성.
            session = new JSch().getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channelExec = (ChannelExec)session.openChannel("exec");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            channelExec.setOutputStream(baos);
            channelExec.setCommand(sshQueryScript);
            channelExec.connect();

            Thread.sleep(1000);

            dockerLog = baos.toString();
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channelExec != null) {
                channelExec.disconnect();
            }
        }
        return dockerLog;
    }

    // Update
    public void updateDaemon(UpdateDaemonReqDto reqDto) throws Exception {
        Daemon targetDaemon = daemonRepository.findById(reqDto.getId())
                .orElseThrow(() -> new Exception("temp: no id"));

        if (!reqDto.getName().equals(targetDaemon.getName())) {
            targetDaemon.setName(reqDto.getName());
        }
        if (!reqDto.getVersion().equals(targetDaemon.getVersion())) {
            targetDaemon.setVersion(reqDto.getVersion());
        }
        if (!reqDto.getTechnology().equals(targetDaemon.getTechnology())) {
            targetDaemon.setTechnology(reqDto.getTechnology());
        }
        if (!reqDto.getImageName().equals(targetDaemon.getImageName())) {
            targetDaemon.setImageName(reqDto.getImageName());
        }
        if (!reqDto.getContainerName().equals(targetDaemon.getContainerName())) {
            targetDaemon.setContainerName(reqDto.getContainerName());
        }
        if (!reqDto.getPort().equals(targetDaemon.getPort())) {
            targetDaemon.setPort(reqDto.getPort());
        }
        if (!reqDto.getProjectPath().equals(targetDaemon.getProjectPath())) {
            targetDaemon.setProjectPath(reqDto.getProjectPath());
        }
        if (!reqDto.getBinaryPath().equals(targetDaemon.getBinaryPath())) {
            targetDaemon.setBinaryPath(reqDto.getBinaryPath());
        }
        if (!reqDto.getDockerfile().equals(targetDaemon.getDockerfile())) {
            targetDaemon.setDockerfile(reqDto.getDockerfile());
        }
        if (!reqDto.getComposefile().equals(targetDaemon.getComposefile())) {
            targetDaemon.setComposefile(reqDto.getComposefile());
        }
        if (!reqDto.getDescription().equals(targetDaemon.getDescription())) {
            targetDaemon.setDescription(reqDto.getDescription());
        }
        if (!reqDto.getPortfolioUrl().equals(targetDaemon.getPortfolioUrl())) {
            targetDaemon.setPortfolioUrl(reqDto.getPortfolioUrl());
        }

        daemonRepository.save(targetDaemon);
    }

    // Delete
    public void deleteDaemon(DeleteDaemonReqDto reqDto) throws Exception {
        Daemon targetDaemon = daemonRepository.findById(reqDto.getId())
                .orElseThrow(() -> new Exception("temp: no id"));

        daemonRepository.delete(targetDaemon);
    }
}
