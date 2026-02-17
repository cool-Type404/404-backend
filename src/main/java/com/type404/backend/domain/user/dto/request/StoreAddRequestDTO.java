package com.type404.backend.domain.user.dto.request;

import com.type404.backend.domain.store.entity.enumtype.StoreCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreAddRequestDTO {

    @NotBlank(message = "식당명 입력은 필수입니다.")
    @Size(min = 2, max = 20, message = "식당명은 2자 이상, 20자 이내로 입력해야 합니다.")
    private String storeName;

    private String addressUrl;

    @NotBlank(message = "추천 이유는 필수 입력입니다.")
    @Size(min = 5, max = 500, message = "식당 추천 이유는 5자 이상, 500자 미만으로 입력해야 합니다.")
    private String contents;

    private StoreCategory storeCategory;
}
