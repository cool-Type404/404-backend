package com.type404.backend.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailVerifyRequest {

    @NotBlank(message = "이메일 입력은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Size(max = 50, message = "이메일은 50자 이하로 입력하여야 합니다.")
    private String email;

    @NotBlank(message = "인증 코드 입력은 필수입니다.")
    @Size(min = 6, max = 6, message = "인증 코드는 6자리입니다.")
    @Pattern(regexp = "\\d{6}", message = "인증 코드는 숫자 형태여야 합니다.")
    private String authCode;
}
