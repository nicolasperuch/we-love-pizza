package com.github.nicolasperuch.factory;

import com.github.nicolasperuch.api.dto.PizzaDto;
import com.github.nicolasperuch.model.OrderModel;

public class OrderFactory {

    private static final String PENDING_STATUS = "pending";

    public static OrderModel buildPendingPizza(PizzaDto pizzaDto){
        return new OrderModel()
                    .setCurrentStatus(PENDING_STATUS)
                    .setFlavor(pizzaDto.getFlavor())
                    .setSize(pizzaDto.getSize());
    }
}
