package com.newcentury99.servercp.domain.dashboard.daemon.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
public class UpgradeDaemonReqDto {
    @PositiveOrZero(message = "valid.daemon.id.positiveOrZero")
    private Long id;
    @NotBlank(message = "valid.daemon.version.blank")
    @Size(max = 20, message = "valid.daemon.version.size")
    private String version;
    @NotBlank(message = "valid.daemon.dockerfile.blank")
    private String dockerfile;
    @NotBlank(message = "valid.daemon.composefile.blank")
    private String composefile;
}
