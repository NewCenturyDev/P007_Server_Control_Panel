package com.newcentury99.servercp.domain.dashboard.daemon.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
public class UpdateDaemonReqDto {
    @PositiveOrZero(message = "valid.daemon.id.positiveOrZero")
    private Long id;
    @NotBlank(message = "valid.daemon.name.blank")
    @Size(max = 20, message = "valid.daemon.name.size")
    private String name;
    @NotBlank(message = "valid.daemon.version.blank")
    @Size(max = 20, message = "valid.daemon.version.size")
    private String version;
    @NotBlank(message = "valid.daemon.technology.blank")
    @Size(max = 200, message = "valid.daemon.technology.size")
    private String technology;
    @NotBlank(message = "valid.daemon.imageName.blank")
    @Size(max = 200, message = "valid.daemon.imageName.size")
    private String imageName;
    @NotBlank(message = "valid.daemon.containerName.blank")
    @Size(max = 200, message = "valid.daemon.containerName.size")
    private String containerName;
    @NotBlank(message = "valid.daemon.port.blank")
    @Size(max = 200, message = "valid.daemon.port.size")
    private String port;
    @NotBlank(message = "valid.daemon.projectPath.blank")
    @Size(max = 200, message = "valid.daemon.projectPath.size")
    private String projectPath;
    @NotBlank(message = "valid.daemon.binaryPath.blank")
    @Size(max = 200, message = "valid.daemon.binaryPath.size")
    private String binaryPath;
    @NotBlank(message = "valid.daemon.dockerfile.blank")
    private String dockerfile;
    @NotBlank(message = "valid.daemon.composefile.blank")
    private String composefile;
    @Size(max = 200, message = "valid.daemon.description.size")
    private String description;
    @Size(max = 200, message = "valid.daemon.portfolioUrl.size")
    private String portfolioUrl;
}
