package com.type404.backend.global.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final TempUser tempUser;

    public CustomUserDetails(TempUser tempUser) {
        this.tempUser = tempUser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return tempUser.getUserPw();
    }

    @Override
    public String getUsername() {
        return tempUser.getUserEmail();
    }

    public String getNickname() {
        return tempUser.getUserNickname();
    }

    public Long getUserId() {
        return tempUser.getUserId();
    }

    public TempUser getTempUser() {
        return this.tempUser;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }     // 계정 만료 여부: 항상 true

    @Override
    public boolean isAccountNonLocked() { return true; }      // 계정 잠금 여부: 항상 true

    @Override
    public boolean isCredentialsNonExpired() { return true; } // 비밀번호 만료 여부: 항상 true

    @Override
    public boolean isEnabled() { return true; }               // 계정 활성화 여부: 항상 true

}