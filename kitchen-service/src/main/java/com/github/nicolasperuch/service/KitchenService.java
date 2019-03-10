package com.github.nicolasperuch.service;

import com.github.nicolasperuch.client.EventStoreClient;
import com.github.nicolasperuch.model.OrderModel;
import com.google.gson.Gson;
import eventstore.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

@Service
public class KitchenService {

    @Autowired
    private ConverterService converterService;
    @Autowired
    private EventStoreClient eventStoreClient;
    @Autowired
    private Gson gson;
    private final String PENDING_STATUS = "pending";
    private final String PREPARING_STATUS = "preparing";
    private final String COOKING_STATUS = "cooking";
    private final String READY_TO_DELIVER_STATUS = "ready to deliver";

    public void takeOrder(Event event) throws InterruptedException {
        OrderModel orderModel = converterService.eventToModel(event);
        if(isPendingStatus(orderModel)) processOrder(orderModel);
    }

    public void processOrder(OrderModel orderModel) throws InterruptedException {
        orderModel = updateStatus(orderModel, PREPARING_STATUS);
        String orderAsJson = gson.toJson(orderModel);
        String eventToPublish = converterService.buildEventContent(orderAsJson);
        writeIntoEventStore(eventToPublish);
        sleep(1000L);

        orderModel = updateStatus(orderModel, COOKING_STATUS);
        orderAsJson = gson.toJson(orderModel);
        eventToPublish = converterService.buildEventContent(orderAsJson);
        writeIntoEventStore(eventToPublish);
        sleep(1000L);

        orderModel = updateStatus(orderModel, READY_TO_DELIVER_STATUS);
        orderAsJson = gson.toJson(orderModel);
        eventToPublish = converterService.buildEventContent(orderAsJson);
        writeIntoEventStore(eventToPublish);
    }

    public OrderModel updateStatus(OrderModel orderModel, String newStatus){
        return orderModel.setCurrentStatus(newStatus);
    }

    public void writeIntoEventStore(String content){
        eventStoreClient.write(content);
    }

    public boolean isPendingStatus(OrderModel orderModel){
        return orderModel.getCurrentStatus().equalsIgnoreCase(PENDING_STATUS);
    }
}
