package com.newcentury99.servercp.domain.dashboard.daemon;

import com.newcentury99.servercp.domain.dashboard.daemon.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class DaemonController {
    private final DaemonService daemonService;

    @PostMapping("/dashboard/daemon")
    public ResponseEntity<?> createDaemon(@Valid @RequestBody CreateDaemonReqDto reqDto) {
        try {
            daemonService.createDaemon(reqDto);
            return ResponseEntity.ok(new CreateDaemonResDto());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new CreateDaemonResDto());
        }
    }
    @PutMapping("/dashboard/daemon")
    public ResponseEntity<?> updateDaemon(@Valid @RequestBody UpdateDaemonReqDto reqDto) {
        try {
            daemonService.updateDaemon(reqDto);
            return ResponseEntity.ok(new UpdateDaemonResDto());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new UpdateDaemonResDto());
        }
    }
    @DeleteMapping("/dashboard/daemon")
    public ResponseEntity<?> deleteDaemon(@Valid @RequestBody DeleteDaemonReqDto reqDto) {
        try {
            daemonService.deleteDaemon(reqDto);
            return ResponseEntity.ok(new DeleteDaemonResDto());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new DeleteDaemonResDto());
        }
    }
}
