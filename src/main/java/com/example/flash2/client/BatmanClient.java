package com.example.flash2.client;//package com.example.flash.client;
//
//import com.example.flash.client.domain.response.BatmanClientResponse;
//import com.example.flash.config.HttpHeader;
//import java.util.Collections;
//import java.util.Map;
//import java.util.Optional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class BatmanClient {
//  private final RestTemplate restTemplate;
//  private final HttpHeader httpHeader;
//
//  @Value("${client.batman}")
//  private String getBatmanClientUrl;
//
//  public Optional<BatmanClientResponse> getBatmanClient(String courseId) {
//    String apiUrl = UriComponentsBuilder.fromHttpUrl(getBatmanClientUrl)
//        .queryParam("courseId", courseId)
//        .toUriString();
//    try {
//      HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeader.createHttpHeaders());
//      ResponseEntity<BatmanClientResponse> response = restTemplate.exchange(apiUrl, HttpMethod.GET, httpEntity, BatmanClientResponse.class);
//      return response.getBody() != null ? Optional.of(response.getBody()) : Optional.empty();
//    } catch (Exception e) {
//      log.error("Unable to call batman service");
//      throw e;
//    }
//  }
//}
