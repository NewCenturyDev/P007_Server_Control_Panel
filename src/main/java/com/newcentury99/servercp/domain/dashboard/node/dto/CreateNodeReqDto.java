package com.newcentury99.servercp.domain.dashboard.node.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateNodeReqDto {
    @NotBlank(message = "valid.node.name.blank")
    @Size(max = 20, message = "valid.node.name.size")
    private String name;
    @NotBlank(message = "valid.node.host.blank")
    @Size(max = 100, message = "valid.node.host.size")
    private String host;
    @NotBlank(message = "valid.node.sshUsername.blank")
    @Size(max = 20, message = "valid.node.sshUsername.size")
    private String sshUsername;
    @NotBlank(message = "valid.node.sshPassword.blank")
    @Size(max = 20, message = "valid.node.sshPassword.size")
    private String sshPassword;
    @Size(max = 200, message = "valid.node.description.size")
    private String description;
}
