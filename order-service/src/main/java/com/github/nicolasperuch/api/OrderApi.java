package com.github.nicolasperuch.api;

import com.github.nicolasperuch.api.dto.PizzaDto;
import com.github.nicolasperuch.model.OrderModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.github.nicolasperuch.factory.OrderFactory.buildPendingPizza;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class OrderApi {

    @PostMapping("/pizza")
    public ResponseEntity<?> createPizzaOrder(@RequestBody PizzaDto pizzaDto){
        OrderModel pendingOrder = buildPendingPizza(pizzaDto);
        return ok(pendingOrder);
    }
}
