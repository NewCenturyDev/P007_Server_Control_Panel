package com.newcentury99.servercp.domain.member;

import com.newcentury99.servercp.domain.member.dto.*;
import com.newcentury99.servercp.domain.member.service.MemberCrudService;
import com.newcentury99.servercp.global.dtometadata.DtoMataData;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class MemberController {
    private static final Logger logger = LogManager.getLogger();
    private final MemberCrudService memberCrudService;

    @GetMapping("/login")
    public String loginView() {
        return "auth/login.html";
    }

    @GetMapping("/register")
    public String registerView() {
        return "auth/register.html";
    }

    @PostMapping("/register")
    public ResponseEntity<?> createMember(@Valid @RequestBody RegisterReqDto reqDto) {
        DtoMataData dtoMataData;
        try {
            memberCrudService.createMember(reqDto);
            dtoMataData = new DtoMataData(true, "create member success");
            return ResponseEntity.ok(new RegisterResDto(dtoMataData));
        } catch (Exception e) {
            logger.warn(e.getMessage());
            dtoMataData = new DtoMataData(false, e.getMessage(), e.getClass().toString());
            return ResponseEntity.status(400).body(new RegisterResDto(dtoMataData));
        }
    }

    @DeleteMapping("/member")
    public ResponseEntity<?> deleteMember(@Valid @RequestBody ResignReqDto reqDto) {
        DtoMataData dtoMataData;
        try {
            memberCrudService.deleteMember(reqDto.getId());
            dtoMataData = new DtoMataData(true, "delete member success");
            return ResponseEntity.ok(new ResignResDto(dtoMataData));
        } catch (Exception e) {
            logger.warn(e.getMessage());
            dtoMataData = new DtoMataData(false, e.getMessage());
            return ResponseEntity.status(400).body(new RegisterResDto(dtoMataData));
        }
    }
}
