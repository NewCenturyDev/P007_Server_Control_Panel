package com.newcentury99.servercp.domain.dashboard.daemon;

import com.newcentury99.servercp.domain.dashboard.daemon.dao.Daemon;
import com.newcentury99.servercp.domain.dashboard.daemon.dao.DaemonRepository;
import com.newcentury99.servercp.domain.dashboard.daemon.dto.CreateDaemonReqDto;
import com.newcentury99.servercp.domain.dashboard.daemon.dto.DeleteDaemonReqDto;
import com.newcentury99.servercp.domain.dashboard.daemon.dto.UpdateDaemonReqDto;
import com.newcentury99.servercp.domain.dashboard.node.NodeService;
import com.newcentury99.servercp.domain.dashboard.node.dao.Node;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DaemonService {
    private final DaemonRepository daemonRepository;
    private final NodeService nodeService;

    // Create
    @Transactional
    public void createDaemon(CreateDaemonReqDto reqDto) throws Exception {
        Node distributedNode = nodeService.fetchNodeById(reqDto.getNodeId());

        Daemon newDaemon = Daemon.builder()
                .name(reqDto.getName())
                .node(distributedNode)
                .technology(reqDto.getTechnology())
                .version(reqDto.getVersion())
                .projectPath(reqDto.getProjectPath())
                .binaryPath(reqDto.getBinaryPath())
                .dockerfile(reqDto.getDockerfile())
                .composefile(reqDto.getComposefile())
                .description(reqDto.getDescription())
                .build();

        daemonRepository.save(newDaemon);
    }

    // Read
    public List<Daemon> fetchDaemonByNode(Long nodeId) throws Exception {
        List<Daemon> daemonList = daemonRepository.findByNode_Id(nodeId);
        if (daemonList.isEmpty()) {
            throw new Exception("temp: no id");
        }
        return daemonList;
    }
    public Daemon fetchDaemonById(Long daemonId) throws Exception {
        return daemonRepository.findById(daemonId)
                .orElseThrow(() -> new Exception("temp: no id"));
    }

    // Update
    public void updateDaemon(UpdateDaemonReqDto reqDto) throws Exception {
        Daemon targetDaemon = daemonRepository.findById(reqDto.getId())
                .orElseThrow(() -> new Exception("temp: no id"));

        if (!reqDto.getNodeId().equals(targetDaemon.getNode().getId())) {
            Node newDistributedNode = nodeService.fetchNodeById(reqDto.getNodeId());
            targetDaemon.setNode(newDistributedNode);
        }
        if (!reqDto.getName().equals(targetDaemon.getName())) {
            targetDaemon.setName(reqDto.getName());
        }
        if (!reqDto.getVersion().equals(targetDaemon.getVersion())) {
            targetDaemon.setVersion(reqDto.getVersion());
        }
        if (!reqDto.getTechnology().equals(targetDaemon.getTechnology())) {
            targetDaemon.setTechnology(reqDto.getTechnology());
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

        daemonRepository.save(targetDaemon);
    }

    // Delete
    public void deleteDaemon(DeleteDaemonReqDto reqDto) throws Exception {
        Daemon targetDaemon = daemonRepository.findById(reqDto.getId())
                .orElseThrow(() -> new Exception("temp: no id"));

        daemonRepository.delete(targetDaemon);
    }
}
