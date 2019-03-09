package com.github.nicolasperuch.service;

import com.github.nicolasperuch.service.client.EventStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

@Service
public class RequestService {

    @Autowired
    private EventStoreClient eventStoreClient;

    public void writeIntoEventStore(String event){
        eventStoreClient.write(event);
    }

    public String buildEventContent(String jsonBody){
        return  "[\n" +
                "  {\n" +
                "    \"eventId\": \"" + randomUUID().toString() + "\",\n" +
                "    \"eventType\": \"PIZZA\",\n" +
                "    \"data\": " + jsonBody +
                "  }\n" +
                "]";
    }

}
