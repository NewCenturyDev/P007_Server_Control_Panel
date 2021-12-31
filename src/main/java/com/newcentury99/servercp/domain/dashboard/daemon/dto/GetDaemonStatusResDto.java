package com.newcentury99.servercp.domain.dashboard.daemon.dto;

import com.newcentury99.servercp.global.dtometadata.DtoMataData;
import lombok.Data;

@Data
public class GetDaemonStatusResDto {
    private DtoMataData dtoMataData;
    private String daemonStatus;

    public GetDaemonStatusResDto(DtoMataData dtoMataData) {
        this.dtoMataData = dtoMataData;
    }
    public GetDaemonStatusResDto(DtoMataData dtoMataData, String daemonStatus) {
        this.dtoMataData = dtoMataData;
        this.daemonStatus = daemonStatus;
    }
}
