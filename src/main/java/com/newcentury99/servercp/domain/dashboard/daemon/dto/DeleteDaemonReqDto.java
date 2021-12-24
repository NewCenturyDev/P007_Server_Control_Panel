package com.newcentury99.servercp.domain.dashboard.daemon.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeleteDaemonReqDto {
    @NotBlank(message = "valid.daemon.id.blank")
    private Long id;
}
