package com.github.nicolasperuch.service;

import com.github.nicolasperuch.model.OrderModel;
import com.google.gson.Gson;
import eventstore.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

@Service
public class ConverterService {

    @Autowired
    private Gson gson;

    public OrderModel eventToModel(Event event){
        String jsonBody = eventToJson(event);
        OrderModel orderModel = jsonToModel(jsonBody);
        return orderModel;
    }

    public String eventToJson(Event event) {
        String jsonBody = event.data().data().toString();
        jsonBody = removePrefix(jsonBody);
        return removeSuffix(jsonBody);
    }

    public String removePrefix(String json) {
        return json.replace("Content(","");
    }

    public String removeSuffix(String json) {
        return json.replace(",ContentType.Json)", "");
    }

    public OrderModel jsonToModel(String json) {
        return gson.fromJson(json, OrderModel.class);
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
