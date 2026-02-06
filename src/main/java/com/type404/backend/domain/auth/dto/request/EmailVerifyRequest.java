package com.type404.backend.domain.auth.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailVerifyRequest {
    private String email;
    private String authCode;
}
