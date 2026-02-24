package com.type404.backend.domain.store.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

/**
 * 네이버 지도 API를 사용한 주소 → 위·경도 지오코딩.
 * API 명세: https://api.ncloud-docs.com/docs/ai-naver-mapsgeocoding-geocode
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NaverGeocodingService {

    private static final String HEADER_CLIENT_ID = "X-NCP-APIGW-API-KEY-ID";
    private static final String HEADER_CLIENT_SECRET = "X-NCP-APIGW-API-KEY";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${naver.map.client-id}")
    private String clientId;

    @Value("${naver.map.client-secret}")
    private String clientSecret;

    @Value("${naver.map.geocode-url}")
    private String geocodeUrl;

    /**
     * 주소 문자열로 위·경도를 조회합니다.
     * @param address 주소 (예: "서울특별시 강남구 테헤란로 152")
     * @return [위도, 경도] 또는 결과가 없으면 empty
     */
    public Optional<double[]> geocode(String address) {
        if (address == null || address.isBlank()) {
            return Optional.empty();
        }
        if (clientId == null || clientId.isBlank() || clientSecret == null || clientSecret.isBlank()) {
            log.warn("네이버 지도 API 키가 설정되지 않아 지오코딩을 건너뜁니다.");
            return Optional.empty();
        }

        URI uri = UriComponentsBuilder.fromUriString(geocodeUrl)
                .queryParam("query", address)
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HEADER_CLIENT_ID, clientId);
        headers.set(HEADER_CLIENT_SECRET, clientSecret);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );

            if (response.getBody() == null) {
                return Optional.empty();
            }

            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode addresses = root.path("addresses");
            if (!addresses.isArray() || addresses.isEmpty()) {
                return Optional.empty();
            }

            JsonNode first = addresses.get(0);
            String yStr = first.path("y").asText(null);
            String xStr = first.path("x").asText(null);
            if (yStr == null || xStr == null) {
                return Optional.empty();
            }

            double lat = Double.parseDouble(yStr);
            double lng = Double.parseDouble(xStr);
            return Optional.of(new double[]{lat, lng});
        } catch (Exception e) {
            log.warn("지오코딩 실패: address={}, error={}", address, e.getMessage());
            return Optional.empty();
        }
    }
}
