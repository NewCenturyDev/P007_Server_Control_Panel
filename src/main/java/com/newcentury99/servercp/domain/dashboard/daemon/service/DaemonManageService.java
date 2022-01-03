package com.newcentury99.servercp.domain.dashboard.daemon.service;

import com.newcentury99.servercp.domain.dashboard.daemon.dao.Daemon;
import com.newcentury99.servercp.global.ssh.SshService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DaemonManageService {
    private final DaemonCrudService daemonCrudService;
    private final SshService sshService;

    public String getDaemonStatus(Long daemonId) throws Exception {
        Daemon targetDaemon = daemonCrudService.fetchDaemonById(daemonId);
        String sshCommandScript = String.format(
                "docker ps -f name=%s --format \"{{.Status}}\"", targetDaemon.getContainerName()
        );
        String daemonStatus = sshService.executeSshRemoteCommand(sshCommandScript);

        if (daemonStatus.length() == 0) {
            daemonStatus = "Not Running";
        }
        return daemonStatus;
    }

    public void startDaemon(Long daemonId) throws Exception {
        Daemon targetDaemon = daemonCrudService.fetchDaemonById(daemonId);
        String sshCommandScript = String.format("cd %s && docker-compose up -d", targetDaemon.getProjectPath());

        System.out.println(sshService.executeSshRemoteCommand(sshCommandScript));
    }

    public void stopDaemon(Long daemonId) throws Exception {
        Daemon targetDaemon = daemonCrudService.fetchDaemonById(daemonId);
        String sshCommandScript = String.format("cd %s && docker-compose down", targetDaemon.getProjectPath());

        sshService.executeSshRemoteCommand(sshCommandScript);
    }

    public void restartDaemon(Long daemonId) throws Exception {
        Daemon targetDaemon = daemonCrudService.fetchDaemonById(daemonId);
        String sshCommandScript = String.format(
                "cd %s && docker-compose down && docker-compose up -d", targetDaemon.getProjectPath()
        );

        sshService.executeSshRemoteCommand(sshCommandScript);
    }

    public String getDaemonLogById(Long daemonId) throws Exception {
        Daemon targetDaemon = daemonCrudService.fetchDaemonById(daemonId);
        String sshCommandScript = String.format("docker logs %s", targetDaemon.getContainerName());

        return sshService.executeSshRemoteCommand(sshCommandScript);
    }
}
