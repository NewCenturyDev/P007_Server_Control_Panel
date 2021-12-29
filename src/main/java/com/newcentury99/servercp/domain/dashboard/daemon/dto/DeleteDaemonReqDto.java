package com.newcentury99.servercp.domain.dashboard.daemon.dto;

import lombok.Data;

import javax.validation.constraints.PositiveOrZero;

@Data
public class DeleteDaemonReqDto {
    @PositiveOrZero(message = "valid.daemon.id.positiveOrZero")
    private Long id;
}
