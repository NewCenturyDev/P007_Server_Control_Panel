package com.newcentury99.servercp.domain.dashboard.daemon.dto;

import com.newcentury99.servercp.global.dtometadata.DtoMataData;
import lombok.Data;

@Data
public class GetDaemonLogResDto {
    private DtoMataData dtoMataData;
    private String daemonLog;

    public GetDaemonLogResDto(DtoMataData dtoMataData) {
        this.dtoMataData = dtoMataData;
    }
    public GetDaemonLogResDto(DtoMataData dtoMataData, String daemonLog) {
        this.dtoMataData = dtoMataData;
        this.daemonLog = daemonLog;
    }
}
