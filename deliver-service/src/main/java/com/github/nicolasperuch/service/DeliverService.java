package com.github.nicolasperuch.service;

import com.github.nicolasperuch.client.EventStoreClient;
import com.github.nicolasperuch.model.OrderModel;
import com.google.gson.Gson;
import eventstore.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

@Service
public class DeliverService {

    @Autowired
    private Gson gson;
    @Autowired
    private ConverterService converterService;
    @Autowired
    private EventStoreClient eventStoreClient;
    private final String READY_TO_DELIVER_STATUS = "ready to deliver";
    private final String DELIVERING_STATUS = "delivering pizza";
    private final String DELIVERED_STATUS = "delivered pizza";

    public void takeOrder (Event event) throws InterruptedException {
        OrderModel orderModel = converterService.eventToModel(event);
        if(isReadyToDeliverStatus(orderModel)) processOrder(orderModel);
    }

    public void processOrder(OrderModel orderModel) throws InterruptedException {
        orderModel = updateStatus(orderModel, DELIVERING_STATUS);
        String orderAsJson = gson.toJson(orderModel);
        String eventToPublish = converterService.buildEventContent(orderAsJson);
        writeIntoEventStore(eventToPublish);
        sleep(1000L);

        orderModel = updateStatus(orderModel, DELIVERED_STATUS);
        orderAsJson = gson.toJson(orderModel);
        eventToPublish = converterService.buildEventContent(orderAsJson);
        writeIntoEventStore(eventToPublish);
    }

    public OrderModel updateStatus(OrderModel orderModel, String newStatus){
        return orderModel.setCurrentStatus(newStatus);
    }

    public void writeIntoEventStore(String content) {
        eventStoreClient.write(content);
    }

    public boolean isReadyToDeliverStatus(OrderModel orderModel) {
        return orderModel.getCurrentStatus().equalsIgnoreCase(READY_TO_DELIVER_STATUS);
    }


}
