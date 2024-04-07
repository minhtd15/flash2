package com.example.flash2.config;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpHeader {
  public HttpHeaders createHttpHeaders() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return httpHeaders;
  }
}
