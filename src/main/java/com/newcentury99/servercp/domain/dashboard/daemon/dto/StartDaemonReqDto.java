package com.newcentury99.servercp.domain.dashboard.daemon.dto;

import lombok.Data;

import javax.validation.constraints.PositiveOrZero;

@Data
public class StartDaemonReqDto {
    @PositiveOrZero
    private Long id;
}
