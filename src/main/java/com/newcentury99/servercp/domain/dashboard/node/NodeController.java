package com.newcentury99.servercp.domain.dashboard.node;

import com.newcentury99.servercp.domain.dashboard.node.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class NodeController {
    private final NodeService nodeService;

    @PostMapping("/dashboard/node")
    public ResponseEntity<?> createNode(@Valid @RequestBody CreateNodeReqDto reqDto) {
        try {
            nodeService.createNode(reqDto);
            return ResponseEntity.ok(new CreateNodeResDto());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new CreateNodeResDto());
        }
    }
    @PutMapping("/dashboard/node")
    public ResponseEntity<?> updateNode(@Valid @RequestBody UpdateNodeReqDto reqDto) {
        try {
            nodeService.updateNode(reqDto);
            return ResponseEntity.ok(new UpdateNodeResDto());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new UpdateNodeResDto());
        }
    }
    @DeleteMapping("/dashboard/node")
    public ResponseEntity<?> deleteNode(@Valid @RequestBody DeleteNodeReqDto reqDto) {
        try {
            nodeService.deleteNode(reqDto);
            return ResponseEntity.ok(new DeleteNodeResDto());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new DeleteNodeResDto());
        }
    }
}
