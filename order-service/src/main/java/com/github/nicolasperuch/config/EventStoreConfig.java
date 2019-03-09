package com.github.nicolasperuch.config;

import org.springframework.beans.factory.annotation.Value;

public abstract class EventStoreConfig {
    @Value("${event-store.host}")
    protected String EVENT_STORE_HOST;
    @Value("${event-store.port}")
    protected String EVENT_STORE_PORT;

    protected String buildEventStoreUri(){
        return EVENT_STORE_HOST + ":" + EVENT_STORE_PORT;
    }
}