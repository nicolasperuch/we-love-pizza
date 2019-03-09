package com.github.nicolasperuch.factory;

import com.github.nicolasperuch.api.dto.PizzaDto;
import com.github.nicolasperuch.api.dto.ResponseDto;
import com.github.nicolasperuch.model.OrderModel;

import static java.util.UUID.randomUUID;

public class OrderFactory {

    private static final String PENDING_STATUS = "pending";

    public static OrderModel buildPendingPizza(PizzaDto pizzaDto){
        return new OrderModel()
                    .setId(randomUUID().toString())
                    .setCurrentStatus(PENDING_STATUS)
                    .setFlavor(pizzaDto.getFlavor())
                    .setSize(pizzaDto.getSize());
    }

    public static ResponseDto buildResponseOrder(OrderModel orderModel){
        return new ResponseDto()
                    .setMessage("Your pizza was succesfully request!" +
                                "Use the following code to track you order: " +
                                orderModel.getId());
    }
}
