package com.newcentury99.servercp.domain.dashboard.node.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeleteNodeReqDto {
    @NotBlank(message = "valid.node.id.blank")
    private Long id;
}
