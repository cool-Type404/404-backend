package com.type404.backend.global.userdetails;

import com.type404.backend.domain.auth.entity.UserInfoEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final UserInfoEntity userInfo;

    public CustomUserDetails(UserInfoEntity userInfo) {
        this.userInfo = userInfo;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Entity에 Role 필드가 없으므로, 모든 사용자에게 USER 권한 부여
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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