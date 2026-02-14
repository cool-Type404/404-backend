package com.type404.backend.domain.auth.service;

import com.type404.backend.domain.auth.dto.request.LoginRequest;
import com.type404.backend.domain.auth.dto.response.LoginResponse;
import com.type404.backend.domain.auth.entity.RefreshTokenEntity;
import com.type404.backend.domain.auth.entity.UserInfoEntity;
import com.type404.backend.domain.auth.repository.RefreshTokenRepository;
import com.type404.backend.domain.auth.repository.UserInfoRepository;
import com.type404.backend.global.exception.ErrorCode;
import com.type404.backend.global.exception.exceptionType.BadRequestException;
import com.type404.backend.global.security.JwtTokenProvider;
import com.type404.backend.global.security.TokenResponseDTO;
import com.type404.backend.global.userdetails.CustomUserDetailsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserLoginService {
    private final CustomUserDetailsService customUserDetailsService;
    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // 로그인 요청
    public LoginResponse login(LoginRequest request) {
        // 이메일 존재 여부 확인
        UserInfoEntity userEntity = userInfoRepository.findByUserEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException(
                        ErrorCode.INVALID_CREDENTIALS,
                        "아이디 또는 비밀번호가 일치하지 않습니다."
                )
        );
        // 비밀번호 검증
        if(!passwordEncoder.matches(request.getPassword(), userEntity.getUserPassword())) {
            throw new BadRequestException(
                    ErrorCode.INVALID_CREDENTIALS,
                    "아이디 또는 비밀번호가 일치하지 않습니다."
            );
        }

        // JWT 발급
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEntity.getUserEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "", userDetails.getAuthorities());
        TokenResponseDTO tokenResponse = jwtTokenProvider.generateToken(authentication);

        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByUserEmail(userEntity.getUserEmail())
                .map(existingToken -> {
                    existingToken.updateToken(tokenResponse.getRefreshToken());
                    return existingToken;
                })
                .orElseGet(() -> RefreshTokenEntity.builder()
                        .userEmail(userEntity.getUserEmail())
                        .refreshToken(tokenResponse.getRefreshToken())
                        .build());
        refreshTokenRepository.save(refreshTokenEntity);

        return LoginResponse.builder()
                .email(userEntity.getUserEmail())
                .accessToken(tokenResponse.getAccessToken())
                .refreshToken(tokenResponse.getRefreshToken())
                .build();
    }
}
