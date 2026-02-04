package com.type404.backend.global.userdetails;

import com.type404.backend.domain.auth.entity.UserInfoEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final UserInfoEntity userInfo;

    public CustomUserDetails(UserInfoEntity userInfo) {
        this.userInfo = userInfo;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return userInfo.getUserPassword();
    }

    @Override
    public String getUsername() { return userInfo.getUserEmail(); }

    public String getNickname() { return userInfo.getUserNickname(); }

    public Long getUserPK() { return userInfo.getUserPK(); }

    public UserInfoEntity getUserInfo() { return this.userInfo; }

    @Override
    public boolean isAccountNonExpired() { return true; }     // 계정 만료 여부: 항상 true

    @Override
    public boolean isAccountNonLocked() { return true; }      // 계정 잠금 여부: 항상 true

    @Override
    public boolean isCredentialsNonExpired() { return true; } // 비밀번호 만료 여부: 항상 true

    @Override
    public boolean isEnabled() { return true; }               // 계정 활성화 여부: 항상 true

}