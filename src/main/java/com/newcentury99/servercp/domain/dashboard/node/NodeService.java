package com.newcentury99.servercp.domain.dashboard.node;

import com.newcentury99.servercp.domain.dashboard.node.dao.Node;
import com.newcentury99.servercp.domain.dashboard.node.dao.NodeRepository;
import com.newcentury99.servercp.domain.dashboard.node.dto.CreateNodeReqDto;
import com.newcentury99.servercp.domain.dashboard.node.dto.DeleteNodeReqDto;
import com.newcentury99.servercp.domain.dashboard.node.dto.UpdateNodeReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NodeService {
    private final NodeRepository nodeRepository;

    // Create
    @Transactional
    public void createNode(CreateNodeReqDto reqDto) {
        Node newNode = Node.builder()
                .name(reqDto.getName())
                .host(reqDto.getHost())
                .sshUsername(reqDto.getSshUsername())
                .sshPassword(reqDto.getSshPassword())
                .description(reqDto.getDescription())
                .build();

        nodeRepository.save(newNode);
    }

    // Read
    public List<Node> fetchNodeList() {
        return nodeRepository.findAllByOrderByIdAsc();
    }
    public Node fetchNodeById(Long nodeId) throws Exception {
        return nodeRepository.findById(nodeId)
                .orElseThrow(() -> new Exception("temp: no id"));
    }

    // Update
    @Transactional
    public void updateNode(UpdateNodeReqDto reqDto) throws Exception {
        Node targetNode = nodeRepository.findById(reqDto.getId())
                .orElseThrow(() -> new Exception("temp: no id"));

        if (!reqDto.getName().equals(targetNode.getName())) {
            targetNode.setName(reqDto.getName());
        }
        if (!reqDto.getHost().equals(targetNode.getHost())) {
            targetNode.setName(reqDto.getHost());
        }
        if (!reqDto.getSshUsername().equals(targetNode.getSshUsername())) {
            targetNode.setName(reqDto.getSshUsername());
        }
        if (!reqDto.getSshPassword().equals(targetNode.getSshPassword())) {
            targetNode.setName(reqDto.getSshPassword());
        }
        if (!reqDto.getDescription().equals(targetNode.getDescription())) {
            targetNode.setName(reqDto.getDescription());
        }

        nodeRepository.save(targetNode);
    }

    // Delete
    @Transactional
    public void deleteNode(DeleteNodeReqDto reqDto) throws Exception {
        Node targetNode = nodeRepository.findById(reqDto.getId())
                .orElseThrow(() -> new Exception("temp: no id"));

        nodeRepository.delete(targetNode);
    }
}
