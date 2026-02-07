package com.type404.backend.domain.auth.dto.request;

import com.type404.backend.domain.auth.entity.enumtype.Age;
import com.type404.backend.domain.auth.entity.enumtype.EatingLevel;
import com.type404.backend.domain.auth.entity.enumtype.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "이메일 입력은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Size(max = 50, message = "이메일은 50자 이하로 입력하여야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상, 20자 이하의 길이여야 합니다.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$",
            message = "비밀번호는 영어 대소문자, 숫자, 특수문자를 모두 하나 이상 포함해야 합니다."
    )
    private String password;

    @NotBlank(message = "닉네임 입력은 필수입니다.")
    @Size(min = 2, max = 10, message = "닉네임은 2글자 이상, 10글자 이하의 길이여야 합니다.")
    private String nickname;

    private Gender gender;

    private Age age;

    @NotNull(message = "혼밥 레벨 입력은 필수입니다.")
    private EatingLevel eatingLevel;
}
