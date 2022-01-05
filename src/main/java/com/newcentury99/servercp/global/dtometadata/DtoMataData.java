package com.newcentury99.servercp.global.dtometadata;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoMataData {
    private Boolean status;
    private String message;
    private String exception;
    public DtoMataData(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
