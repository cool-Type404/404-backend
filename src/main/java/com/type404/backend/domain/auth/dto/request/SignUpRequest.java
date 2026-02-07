package com.type404.backend.domain.auth.dto.request;

import com.type404.backend.domain.auth.entity.enumtype.Age;
import com.type404.backend.domain.auth.entity.enumtype.EatingLevel;
import com.type404.backend.domain.auth.entity.enumtype.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "이메일 입력은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Size(max = 50)
    private String email;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank(message = "닉네임 입력은 필수입니다.")
    @Size(min = 2, max = 10)
    private String nickname;

    private Gender gender;

    private Age age;

    @NotNull(message = "혼밥 레벨 입력은 필수입니다.")
    private EatingLevel eatingLevel;
}
