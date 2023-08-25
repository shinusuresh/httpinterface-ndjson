package com.example;

import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.mockserver.model.JsonBody;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@SpringBootTest
@MockServerSettings(ports = {8787})
class NdjsonApplicationTests {

    private final MockServerClient mockServerClient;

    @Autowired
    private ApiInterface apiInterface;

    NdjsonApplicationTests(MockServerClient mockServerClient) {
        this.mockServerClient = mockServerClient;
    }

    @SuppressWarnings("unchecked")
    @Test
    void testApi() {
        this.mockServerClient
                .when(request()
                        .withMethod("POST")
                        .withPath("/")
                        .withContentType(MediaType.parse(org.springframework.http.MediaType.APPLICATION_NDJSON_VALUE))
                        .withBody("""
                                {"id": 1, "name": "name"}
                                {"id": 2, "name": "name"}
                                """))
                .respond(response()
                        .withStatusCode(201)
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(new JsonBody("""
                                 {
                                   success: true
                                 }
                                """
                        )));
        assertThat(this.apiInterface.upload(Map.of(
                "1", "name",
                "2", "name"
        ))).isNotNull();
    }

}
