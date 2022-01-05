package com.newcentury99.servercp.domain.member.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterReqDto {
    @NotBlank(message = "valid.member.username.blank")
    @Size(max = 20, message = "valid.member.username.size")
    private String username;

    @NotBlank(message = "valid.member.password.blank")
    @Size(max = 20, message = "valid.member.password.size")
    private String password;

    @NotBlank(message = "valid.member.host.blank")
    @Size(max = 20, message = "valid.member.host.size")
    private String host;
}
