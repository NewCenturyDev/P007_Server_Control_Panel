package com.newcentury99.servercp.domain.dashboard.daemon;

import com.newcentury99.servercp.domain.dashboard.daemon.dto.*;
import com.newcentury99.servercp.global.dtometadata.DtoMataData;
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
        DtoMataData dtoMataData;
        try {
            daemonService.createDaemon(reqDto);
            dtoMataData = new DtoMataData(true, "create daemon success");
            return ResponseEntity.ok(new CreateDaemonResDto(dtoMataData));
        } catch (Exception e) {
            dtoMataData = new DtoMataData(false, e.getMessage());
            return ResponseEntity.status(400).body(new CreateDaemonResDto(dtoMataData));
        }
    }
    @PutMapping("/dashboard/daemon")
    public ResponseEntity<?> updateDaemon(@Valid @RequestBody UpdateDaemonReqDto reqDto) {
        DtoMataData dtoMataData;
        try {
            daemonService.updateDaemon(reqDto);
            dtoMataData = new DtoMataData(true, "update daemon success");
            return ResponseEntity.ok(new UpdateDaemonResDto(dtoMataData));
        } catch (Exception e) {
            dtoMataData = new DtoMataData(false, e.getMessage());
            return ResponseEntity.status(400).body(new UpdateDaemonResDto(dtoMataData));
        }
    }
    @DeleteMapping("/dashboard/daemon")
    public ResponseEntity<?> deleteDaemon(@Valid @RequestBody DeleteDaemonReqDto reqDto) {
        DtoMataData dtoMataData;
        try {
            dtoMataData = new DtoMataData(true, "delete daemon success");
            daemonService.deleteDaemon(reqDto);
            return ResponseEntity.ok(new DeleteDaemonResDto(dtoMataData));
        } catch (Exception e) {
            dtoMataData = new DtoMataData(false, e.getMessage());
            return ResponseEntity.status(400).body(new DeleteDaemonResDto(dtoMataData));
        }
    }
}
