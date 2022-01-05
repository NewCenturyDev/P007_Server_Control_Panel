package com.newcentury99.servercp.domain.member.service;

import com.newcentury99.servercp.domain.member.dao.Member;
import com.newcentury99.servercp.domain.member.dao.MemberPermission;
import com.newcentury99.servercp.domain.member.dao.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class MemberAuthService implements UserDetailsService {
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> (new UsernameNotFoundException("temp: entity not exists")));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(MemberPermission.ADMIN.getValue()));

        return new User(member.getUsername(), member.getPassword(), grantedAuthorities);
    }
}
