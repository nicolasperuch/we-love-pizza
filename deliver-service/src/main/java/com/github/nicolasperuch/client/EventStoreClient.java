package com.github.nicolasperuch.client;

import feign.Headers;
import feign.RequestLine;

public interface EventStoreClient {
    @RequestLine("POST /streams/pizza")
    @Headers("Content-Type: application/vnd.eventstore.events+json")
    String write(String content);
}