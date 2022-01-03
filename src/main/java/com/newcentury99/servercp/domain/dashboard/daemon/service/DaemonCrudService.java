package com.newcentury99.servercp.domain.dashboard.daemon.service;

import com.newcentury99.servercp.domain.dashboard.daemon.dao.Daemon;
import com.newcentury99.servercp.domain.dashboard.daemon.dao.DaemonRepository;
import com.newcentury99.servercp.domain.dashboard.daemon.dto.CreateDaemonReqDto;
import com.newcentury99.servercp.domain.dashboard.daemon.dto.DeleteDaemonReqDto;
import com.newcentury99.servercp.domain.dashboard.daemon.dto.UpdateDaemonReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DaemonCrudService {
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
    public void updateDaemonOnUpgrade(Daemon targetDaemon) {
        daemonRepository.save(targetDaemon);
    }

    // Delete
    public void deleteDaemon(DeleteDaemonReqDto reqDto) throws Exception {
        Daemon targetDaemon = daemonRepository.findById(reqDto.getId())
                .orElseThrow(() -> new Exception("temp: no id"));

        daemonRepository.delete(targetDaemon);
    }
}
