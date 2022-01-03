package com.newcentury99.servercp.domain.dashboard.daemon.service;

import com.newcentury99.servercp.domain.dashboard.daemon.dao.Daemon;
import com.newcentury99.servercp.domain.dashboard.daemon.dto.UpdateDaemonReqDto;
import com.newcentury99.servercp.domain.dashboard.daemon.dto.UpgradeDaemonReqDto;
import com.newcentury99.servercp.global.ssh.SshService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    private void uploadBinaryFile(MultipartFile uploadedBinary, String binaryPath) throws Exception {
        String uploadPath = binaryPath;
        if (binaryPath.charAt(0) == '~' && binaryPath.charAt(1) == '/') {
            uploadPath = binaryPath.substring(2);
        }
        sshService.uploadFileBySftp(
                uploadedBinary.getInputStream(),
                uploadPath,
                uploadedBinary.getOriginalFilename()
        );
    }

    private void upgradeDockerfile(Daemon targetDaemon, String dockerfile) throws Exception {
        String sshCommandScript = String.format(
                "echo -e \"%s\" > %s", StringEscapeUtils.escapeJava(dockerfile), targetDaemon.getProjectPath() + "/Dockerfile"
        );
        sshService.executeSshRemoteCommand(sshCommandScript);
    }

    private void upgradeComposefile(Daemon targetDaemon, String composefile) throws Exception {
        String sshCommandScript = String.format(
                "echo -e \"%s\" > %s", StringEscapeUtils.escapeJava(composefile), targetDaemon.getProjectPath() + "/docker-compose.yml"
        );
        sshService.executeSshRemoteCommand(sshCommandScript);
    }

    private void deleteOldDaemonImage(Daemon targetDaemon) throws Exception {
        String sshCommandScript = String.format(
                "docker rmi %s", targetDaemon.getImageName()
        );
        sshService.executeSshRemoteCommand(sshCommandScript);
    }

    public void upgradeDaemon(UpgradeDaemonReqDto reqDto, MultipartFile uploadedBinary) throws Exception{
        Daemon targetDaemon = daemonCrudService.fetchDaemonById(reqDto.getId());
        UpdateDaemonReqDto upgradedDaemonDetailDto = new UpdateDaemonReqDto();

        this.uploadBinaryFile(uploadedBinary, targetDaemon.getBinaryPath());
        this.upgradeDockerfile(targetDaemon, reqDto.getDockerfile());
        this.upgradeComposefile(targetDaemon, reqDto.getComposefile());
        this.stopDaemon(targetDaemon.getId());
        this.deleteOldDaemonImage(targetDaemon);
        this.startDaemon(targetDaemon.getId());

        targetDaemon.setVersion(reqDto.getVersion());
        targetDaemon.setDockerfile(reqDto.getDockerfile());
        targetDaemon.setComposefile(reqDto.getComposefile());
        daemonCrudService.updateDaemonOnUpgrade(targetDaemon);
    }

    public String getDaemonLogById(Long daemonId) throws Exception {
        Daemon targetDaemon = daemonCrudService.fetchDaemonById(daemonId);
        String sshCommandScript = String.format("docker logs %s", targetDaemon.getContainerName());

        return sshService.executeSshRemoteCommand(sshCommandScript);
    }
}
