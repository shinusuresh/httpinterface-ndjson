package com.example;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Map;

public interface ApiInterface {

    @SuppressWarnings("unchecked")
    @PostExchange(contentType = MediaType.APPLICATION_NDJSON_VALUE)
    ResponseEntity<String> upload(@RequestBody Map<String, String>... data);
}
