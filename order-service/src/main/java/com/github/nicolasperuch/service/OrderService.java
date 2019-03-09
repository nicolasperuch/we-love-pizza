package com.github.nicolasperuch.service;

import com.github.nicolasperuch.api.dto.PizzaDto;
import com.github.nicolasperuch.client.EventStoreClient;
import com.github.nicolasperuch.model.OrderModel;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.github.nicolasperuch.factory.OrderFactory.buildPendingPizza;
import static com.github.nicolasperuch.factory.OrderFactory.buildResponseOrder;
import static java.util.UUID.randomUUID;
import static org.springframework.http.ResponseEntity.ok;

@Service
public class OrderService {

    @Autowired
    private EventStoreClient eventStoreClient;
    @Autowired
    private Gson gson;

    public ResponseEntity<?> requestOrder(PizzaDto pizzaDto){
        OrderModel pendingOrder = buildPendingPizza(pizzaDto);
        String orderAsJson = gson.toJson(pendingOrder);
        String event = buildEventContent(orderAsJson);
        writeIntoEventStore(event);
        return ok(buildResponseOrder(pendingOrder));
    }

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
