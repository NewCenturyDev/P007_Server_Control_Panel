package com.newcentury99.servercp.domain.dashboard.daemon.dto;

import lombok.Data;

import javax.validation.constraints.PositiveOrZero;

@Data
public class RestartDaemonReqDto {
    @PositiveOrZero
    private Long id;
}
