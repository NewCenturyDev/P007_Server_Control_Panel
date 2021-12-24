package com.newcentury99.servercp.domain.dashboard.daemon.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UpdateDaemonReqDto {
    @NotBlank(message = "valid.daemon.id.blank")
    private Long id;
    @NotBlank(message = "valid.daemon.nodeId.blank")
    private Long nodeId;
    @NotBlank(message = "valid.daemon.name.blank")
    @Size(max = 20, message = "valid.daemon.name.size")
    private String name;
    @NotBlank(message = "valid.daemon.version.blank")
    @Size(max = 20, message = "valid.daemon.version.size")
    private String version;
    @NotBlank(message = "valid.daemon.technology.blank")
    @Size(max = 200, message = "valid.daemon.technology.size")
    private String technology;
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
}
