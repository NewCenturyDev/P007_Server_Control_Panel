package com.newcentury99.servercp.domain.member.dto;

import lombok.Data;

import javax.validation.constraints.PositiveOrZero;

@Data
public class ResignReqDto {
    @PositiveOrZero(message = "valid.member.id.positiveOrZero")
    private Long id;
}
