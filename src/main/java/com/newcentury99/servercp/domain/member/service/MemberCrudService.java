package com.newcentury99.servercp.domain.member.service;

import com.newcentury99.servercp.domain.member.dao.Member;
import com.newcentury99.servercp.domain.member.dao.MemberPermission;
import com.newcentury99.servercp.domain.member.dao.MemberRepository;
import com.newcentury99.servercp.domain.member.dto.RegisterReqDto;
import com.newcentury99.servercp.global.ssh.SshService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberCrudService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder pwEncoder;
    private final SshService sshService;

    // Create
    public void createMember(RegisterReqDto reqDto) throws Exception {
        // 중복 확인
        this.checkUsernameDuplication(reqDto.getUsername());

        // SSH 유효성 확인
        this.checkHostAccountSync(reqDto.getUsername());

        Member member = Member.builder()
                .username(reqDto.getUsername())
                .password(pwEncoder.encode(reqDto.getPassword()))
                .role(MemberPermission.ADMIN)
                .host(reqDto.getHost())
                .build();

        memberRepository.save(member);
    }

    // Read
    public Member fetchMemberByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> (new UsernameNotFoundException("temp: entity not exists")));
    }
    public Member fetchMemberById(Long memberId) throws UsernameNotFoundException {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> (new UsernameNotFoundException("temp: entity not exists")));
    }

    // Delete
    public void deleteMember(Long memberId) throws Exception {
        Member targetMember = this.fetchMemberById(memberId);

        memberRepository.delete(targetMember);
    }

    // Utility
    private void checkUsernameDuplication(String username) throws Exception {
        // 닉네임 중복 확인
        if (username != null && memberRepository.existsByUsername(username)) {
            throw new Exception("temp: error.dup.username");
        }
    }

    private void checkHostAccountSync(String username) throws Exception {
        //TODO: SSH Service 계정정보와 연동할것
        String sshCommandScript = "whoami";
        if(!username.equals(sshService.executeSshRemoteCommand(sshCommandScript).replace("\n", ""))) {
            throw new Exception("Unable to connect server node with this cridential");
        }
    }
}
