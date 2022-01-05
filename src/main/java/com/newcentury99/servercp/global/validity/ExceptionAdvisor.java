package com.newcentury99.servercp.global.validity;

import com.newcentury99.servercp.global.dtometadata.DtoMataData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@RestController
// 전역 예외 처리 클래스
public class ExceptionAdvisor {
    private static final Logger logger = LogManager.getLogger();

    // Spring DTO validation 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> processValidationError(MethodArgumentNotValidException ex) {
        DtoMataData dtoMetadata;
        /*
        String errMsg;
        try {
            errMsg = msgSrc.getMessage(
                    Objects.requireNonNull(ex.getAllErrors().get(0).getDefaultMessage()),
                    ex.getAllErrors().get(0).getArguments(),
                    Locale.ENGLISH
            );
        } catch (Exception e) {
            errMsg = "Error message localize failure";
        }
        */

        // 예외 처리
        logger.warn(ex.toString());
        dtoMetadata = new DtoMataData(false, ex.getAllErrors().get(0).getDefaultMessage(), ex.getClass().getName());
        return ResponseEntity.status(400).body(new ValidityCheckFailureResDto(dtoMetadata));
    }
}
